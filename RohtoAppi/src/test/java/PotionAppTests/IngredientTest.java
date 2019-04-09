/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PotionAppTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rohtoappi.domain.components.Ingredient;

/**
 *
 * @author xilxilx
 */
public class IngredientTest {
    
    public IngredientTest() {
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
    }
    
    
    
}
