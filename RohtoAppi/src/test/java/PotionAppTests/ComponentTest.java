
package PotionAppTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rohtoappi.domain.components.Ingredient;
import rohtoappi.domain.components.Potion;

/**
 *
 * @author xilxilx
 */
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
    
}
