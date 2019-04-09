package PotionAppTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rohtoappi.domain.AppLogic;

/**
 *
 * @author xilxilx
 */
public class IngredientLibraryTest {
    
    AppLogic logic;
    
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
        logic = new AppLogic();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void addsNewIngredientToLibrary() {                        
        assertEquals("clear", logic.ingredientLibrary.addIngredient("dragon scale", "mg"));        
    }
    
    @Test
    public void doesntAddDuplicatesToIngredientLibrary() {
        assertEquals("duplicate", logic.ingredientLibrary.addIngredient("fairy dust", "g"));
    }
    
    @Test
    public void doesntAcceptEmptyFieldsNewIngredient() {
        assertEquals("fields", logic.ingredientLibrary.addIngredient("", ""));
    }
    
    @Test
    public void deletesExistingIngredientFromLibrary() {
        assertEquals(true, logic.ingredientLibrary.removeIngredient("fairy dust"));
    }
    
    @Test
    public void doesNotDeleteUnexistingIngredientFromLibrary() {
        assertEquals(false, logic.ingredientLibrary.removeIngredient("troll ear"));
    }
    
    @Test
    public void addsIngredientTriesToDuplicateAndDeletesIt() {
        assertEquals("clear", logic.ingredientLibrary.addIngredient("dragon scale", "mg"));
        assertEquals("duplicate", logic.ingredientLibrary.addIngredient("dragon scale", "mg"));
        assertEquals(true, logic.ingredientLibrary.removeIngredient("dragon scale"));
    }
    
}
