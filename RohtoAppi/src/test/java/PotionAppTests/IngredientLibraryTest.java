package PotionAppTests;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rohtoappi.domain.AppLogic;
import rohtoappi.domain.components.Ingredient;

public class IngredientLibraryTest {

    AppLogic logic = new AppLogic();
    Ingredient ingredient;
    HashMap<String, Ingredient> map;

    public IngredientLibraryTest() {
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
    public void addsNewIngredientToLibrary() {
        assertEquals("clear", logic.ingredientLibrary.addIngredient("chuck norris", "kg"));
    }

    @Test
    public void ingredientsCorrectSize() {
        assertEquals(4, logic.ingredientLibrary.getIngredientsNames().size());
    }

    @Test
    public void doesntAddDuplicatesToIngredientLibrary() {
        assertEquals("duplicate", logic.ingredientLibrary.addIngredient(ingredient.getName(), ingredient.getMeasuringUnit()));
    }

    @Test
    public void doesntAcceptEmptyFieldsNewIngredient() {
        assertEquals("fields", logic.ingredientLibrary.addIngredient("", ""));
    }

    @Test
    public void doesntAcceptTooBigAmount() {
        logic.ingredientLibrary.addIngredient("fairy dust", "g");
        assertEquals("limit", logic.addToTempPotion("fairy dust", "1000000000"));
    }

    @Test
    public void deletesExistingIngredientFromLibrary() {
        assertEquals(true, logic.ingredientLibrary.removeIngredient(ingredient.getName()));
        logic.ingredientLibrary.addIngredient(ingredient.getName(), ingredient.getMeasuringUnit());
    }

    @Test
    public void doesNotDeleteUnexistingIngredientFromLibrary() {
        assertEquals(false, logic.ingredientLibrary.removeIngredient("chuck norris"));
    }

    @Test
    public void addsIngredientTriesToDuplicateAndDeletesIt() {
        assertEquals("duplicate", logic.ingredientLibrary.addIngredient(ingredient.getName(), ingredient.getMeasuringUnit()));
    }

}
