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
import rohtoappi.rohtoappi.*;
import rohtoappi.ui.*;

/**
 *
 * @author xilxilx
 */
public class PotionAppTest {
    
    AppLogic logic;
//    AppUI ui;
    
    public PotionAppTest() {
        logic = new AppLogic();
//        ui = new AppUI();
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
    
}
