package PotionAppTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.HashMap;
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
        ingredient = logic.ingredientLibrary.getRandomIngredient();
        map = logic.ingredientLibrary.getIngredients();
    }
    
    @After
    public void tearDown() {
        logic.ingredientLibrary.writeToFile(map);
    }

    @Test
    public void addsNewIngredientToLibrary() {                        
        assertEquals("clear", logic.ingredientLibrary.addIngredient("chuck norris", "kg"));
        logic.ingredientLibrary.removeIngredient("chuck norris");
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
