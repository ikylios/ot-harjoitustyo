package PotionAppTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rohtoappi.domain.components.Ingredient;
import rohtoappi.domain.components.Magic;
import rohtoappi.domain.components.Potion;

public class ComponentTest {

    public ComponentTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void ingredientInitCorrectly() {
        Ingredient ingredient = new Ingredient("carrot", "g");
        assertEquals("carrot", ingredient.getName());
        assertEquals("g", ingredient.getMeasuringUnit());
        assertEquals(0, ingredient.getAmount());
        assertEquals(ingredient.getName() + "\t\t" + " " + ingredient.getMeasuringUnit(), ingredient.toString());
    }

    @Test
    public void ingredientInitCorrectlyWithSetters() {
        Ingredient ingredient = new Ingredient("", "");
        ingredient.setName("carrot");
        ingredient.setMeasuringUnit("g");
        ingredient.setAmount(10);
        assertEquals("carrot", ingredient.getName());
        assertEquals("g", ingredient.getMeasuringUnit());
        assertEquals(10, ingredient.getAmount());
        assertEquals(ingredient.getName() + "\t\t" + ingredient.getAmount() + " " + ingredient.getMeasuringUnit(), ingredient.toString());
    }

    @Test
    public void modifiedToString() {
        Ingredient ingredient = new Ingredient("carrot", "g");
        assertEquals(ingredient.getName() + "\t\t" + " " + ingredient.getMeasuringUnit(), ingredient.toString());
        ingredient.setAmount(10);
        assertEquals(ingredient.getName() + "\t\t" + ingredient.getAmount() + " " + ingredient.getMeasuringUnit(), ingredient.toString());
    }

    @Test
    public void potionInitCorrectly() {
        Potion potion = new Potion();
        potion.setEffect("Hair growth");
        assertEquals("Hair growth", potion.getEffect());
        potion.setType("Salve");
        assertEquals("Salve", potion.getType());
    }

    @Test
    public void getIngredientsAsString() {
        Ingredient i1 = new Ingredient("honey", 40, "ml");
        Ingredient i2 = new Ingredient("snot", 30, "ml");
        Potion potion = new Potion();
        potion.addToPotion(i1);
        potion.addToPotion(i2);
        assertEquals(2, potion.getIngredientsString().size());
    }

    @Test
    public void editsAmount() {
        Ingredient ingredient = new Ingredient("knife", 14, "pieces");
        Potion potion = new Potion();
        potion.addToPotion(ingredient);
        assertEquals("clear", potion.editAmount("knife", "4000"));
    }

    @Test
    public void doesntAcceptZeroAsEdit() {
        Ingredient ingredient = new Ingredient("knife", 14, "pieces");
        Potion potion = new Potion();
        potion.addToPotion(ingredient);
        potion.editAmount("knife", "0");
        assertEquals("NotValid", potion.editAmount("knife", "0"));
    }

    @Test
    public void doesntAcceptNegativeAsEdit() {
        Ingredient ingredient = new Ingredient("knife", 14, "pieces");
        Potion potion = new Potion();
        potion.addToPotion(ingredient);
        assertEquals("NotValid", potion.editAmount("knife", "-20"));
    }

    @Test
    public void doesntAcceptLetterAsEdit() {
        Ingredient ingredient = new Ingredient("knife", 14, "pieces");
        Potion potion = new Potion();
        potion.addToPotion(ingredient);
        potion.editAmount("knife", "-4");
        assertEquals("NotValid", potion.editAmount("knife", "yksi"));
    }

    @Test
    public void removesFromPotion() {
        Potion potion = new Potion();
        potion.addToPotion(new Ingredient("carrot", "g"));
        potion.addToPotion(new Ingredient("umbrella handle", "pieces"));
        assertEquals("clear", potion.removeFromPotion("carrot"));
    }

    @Test
    public void doesntRemoveUnexisting() {
        Potion potion = new Potion();
        potion.addToPotion(new Ingredient("carrot", "g"));
        assertEquals("invalidValue", potion.removeFromPotion(""));
    }

    @Test
    public void magicInitCorrectly() {
        Magic magic = new Magic();
        String[] pieces = magic.generate();
        assertEquals(false, pieces[0].isEmpty());
        assertEquals(false, pieces[1].isEmpty());
        assertEquals(false, pieces[2].isEmpty());
    }

    @Test
    public void magicGeneratesCorrectly() {
        Magic magic = new Magic();
        String[] pieces = magic.generate();
        String string = pieces[0];
        assertEquals(false, string.isEmpty());
        pieces = magic.generate();
        pieces = magic.generate();
        string = pieces[0];
        assertEquals(false, string.isEmpty());
    }

}
