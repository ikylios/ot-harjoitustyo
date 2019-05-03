package PotionAppTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rohtoappi.domain.AppLogic;
import rohtoappi.domain.components.Ingredient;
import rohtoappi.domain.components.Potion;

/**
 *
 * @author xilxilx
 */
public class PotionLibraryTest {

    AppLogic logic = new AppLogic();
    Ingredient ingredient;

    public PotionLibraryTest() {

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
    public void addsPotion() {
        logic.tempPotion.addToPotion(ingredient);
        logic.tempPotion.generateMagic();
        logic.addPotionToLibrary();
        assertEquals(1, logic.potionLibrary.getPotions().size());
    }

    @Test
    public void deletesPotion() {
        logic.tempPotion.addToPotion(ingredient);
        logic.tempPotion.generateMagic();
        String name = logic.tempPotion.getName();
        logic.addPotionToLibrary();
        assertEquals(1, logic.potionLibrary.getPotions().size());
        logic.potionLibrary.deletePotion(name);
        assertEquals(true, logic.potionLibrary.getPotions().isEmpty());
    }

    @Test
    public void doesntDeleteUnexistingPotion() {
        assertEquals("error", logic.potionLibrary.deletePotion("power juice"));
    }

    @Test
    public void addsMultiplePotions() {
        ingredient = logic.ingredientLibrary.getRandomIngredient();
        logic.tempPotion.addToPotion(ingredient);
        logic.tempPotion.generateMagic();
        logic.addPotionToLibrary();
        assertEquals(1, logic.potionLibrary.getPotions().size());

        logic.clearTempPotion();
        logic.tempPotion.addToPotion(ingredient);
        logic.tempPotion.generateMagic();
        while (logic.potionLibrary.getPotionsNames().contains(logic.tempPotion.getName())) {
            logic.tempPotion.generateMagic();
        }
        logic.addPotionToLibrary();
        assertEquals(2, logic.potionLibrary.getPotions().size());
    }

    @Test
    public void noDuplicates() {
        ingredient = logic.ingredientLibrary.getRandomIngredient();
        logic.tempPotion.addToPotion(ingredient);
        logic.tempPotion.setName("kryptonite");
        logic.addPotionToLibrary();
        logic.addPotionToLibrary();
        assertEquals(1, logic.potionLibrary.getPotions().size());
    }

    @Test
    public void fetchesByName() {
        ingredient = logic.ingredientLibrary.getRandomIngredient();
        logic.tempPotion.addToPotion(ingredient);
        logic.tempPotion.setName("kryptonite");
        Potion potion = logic.tempPotion;
        logic.addPotionToLibrary();
        assertEquals(potion, logic.potionLibrary.getPotionByName("kryptonite"));
    }

}
