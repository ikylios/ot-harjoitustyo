/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PotionAppTests;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rohtoappi.domain.AppLogic;
import rohtoappi.domain.components.Ingredient;

/**
 *
 * @author xilxilx
 */
public class PotionViewTest {
        
    AppLogic logic;
    
    public PotionViewTest() {        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        logic = new AppLogic();        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void ingredientIsAddedToPotion() {
        Ingredient ingredient = logic.ingredientLibrary.getRandomIngredient();
        logic.addToTempPotion(ingredient.getName(), "5");
        assertEquals(true, logic.tempPotion.ingredients.contains(ingredient));
        ingredient = logic.ingredientLibrary.getRandomIngredient();
        logic.addToTempPotion(ingredient.getName(), "10");
        assertEquals(true, logic.tempPotion.ingredients.contains(ingredient));
        assertEquals(2, logic.tempPotion.ingredients.size());
    }
    
    @Test
    public void noDuplicatesAdded() {
        Ingredient ingredient = logic.ingredientLibrary.getRandomIngredient();
        logic.addToTempPotion(ingredient.getName(), "5");
        logic.addToTempPotion(ingredient.getName(), "5");
        assertEquals(1, logic.tempPotion.getIngredients().size());
    }
    
    @Test
    public void noEmptyFieldsAccepted() {
        Ingredient ingredient = logic.ingredientLibrary.getRandomIngredient();
        logic.addToTempPotion(ingredient.getName(), "");        
        assertEquals(0, logic.tempPotion.getIngredients().size());
    }
    
    @Test
    public void removesAddedIngredient() {
        Ingredient ingredient = logic.ingredientLibrary.getRandomIngredient();
        logic.addToTempPotion(ingredient.getName(), "5");
        
        assertEquals(true, logic.tempPotion.getIngredients().contains(ingredient));
        logic.tempPotion.removeFromPotion(ingredient.getName());
        assertEquals(false, logic.tempPotion.getIngredients().contains(ingredient));
    }
    
    @Test
    public void doesntRemoveUnexistingIngredient() {    
        assertEquals("notInPotion", logic.tempPotion.removeFromPotion("troll ear"));        
    }
    
}
