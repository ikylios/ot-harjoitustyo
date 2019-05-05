package PotionAppTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rohtoappi.domain.AppLogic;
import rohtoappi.domain.components.Ingredient;

public class CreateAPotionTest {

    AppLogic logic = new AppLogic();
    Ingredient ingredient;

    public CreateAPotionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        logic.ingredientLibrary.addIngredient("fairy dust", "g");
        logic.ingredientLibrary.addIngredient("sparrow feather", "pieces");
        logic.ingredientLibrary.addIngredient("octopus ink", "ml");
        logic.ingredientLibrary.addIngredient("troll ear", "pieces");
        ingredient = logic.ingredientLibrary.getRandomIngredient();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void ingredientIsAdded() {
        logic.addToTempPotion(ingredient.getName(), "5");
        assertEquals(true, logic.tempPotion.ingredients.contains(ingredient));
        assertEquals(1, logic.tempPotion.ingredients.size());
    }

    @Test
    public void multipleIngredientsAreAdded() {
        logic.addToTempPotion(ingredient.getName(), "5");
        assertEquals(true, logic.tempPotion.ingredients.contains(ingredient));
        Ingredient secondIngredient = logic.ingredientLibrary.getRandomIngredient();
        while (ingredient.equals(secondIngredient)) {
            secondIngredient = logic.ingredientLibrary.getRandomIngredient();
        }
        logic.addToTempPotion(secondIngredient.getName(), "10");
        assertEquals(true, logic.tempPotion.ingredients.contains(ingredient));
        assertEquals(true, logic.tempPotion.ingredients.contains(secondIngredient));
        assertEquals(2, logic.tempPotion.ingredients.size());
    }

    @Test
    public void noDuplicatesAdded() {
        logic.addToTempPotion(ingredient.getName(), "5");
        System.out.println(ingredient.getAmount());
        logic.addToTempPotion(ingredient.getName(), "10");
        assertEquals(1, logic.tempPotion.getIngredients().size());
    }

    @Test
    public void noEmptyFieldsAccepted() {
        logic.addToTempPotion(ingredient.getName(), "");
        assertEquals(0, logic.tempPotion.getIngredients().size());
    }

    @Test
    public void removesAddedIngredient() {
        logic.addToTempPotion(ingredient.getName(), "5");
        assertEquals(true, logic.tempPotion.getIngredients().contains(ingredient));
        logic.tempPotion.removeFromPotion(ingredient.getName());
        assertEquals(false, logic.tempPotion.getIngredients().contains(ingredient));
    }

//    @Test
//    public void doesntRemoveUnexistingIngredient() {
//        assertEquals("notInPotion", logic.tempPotion.removeFromPotion("chuck norris"));
//    }
}
