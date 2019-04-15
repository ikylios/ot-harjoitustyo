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
import rohtoappi.domain.AppLogic;
import rohtoappi.domain.components.Ingredient;

/**
 *
 * @author xilxilx
 */
public class PotionLibraryTest {
    
    AppLogic logic = new AppLogic();
    
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
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void addsPotion() {
        Ingredient ingredient = logic.ingredientLibrary.getRandomIngredient();
        logic.tempPotion.addToPotion(ingredient);
        logic.tempPotion.generateMagic();
        logic.addPotionToLibrary();
        assertEquals(1, logic.potionLibrary.getPotions().size());
//        assertEquals(true, logic.potionLibrary.getPotionsNames().contains(ingredient.getName()));
    }
    
    @Test
    public void addsMultiplePotions() {
        Ingredient ingredient = logic.ingredientLibrary.getRandomIngredient();
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
        Ingredient ingredient = logic.ingredientLibrary.getRandomIngredient();
        logic.tempPotion.addToPotion(ingredient);
        logic.tempPotion.setName("kryptonite");
        logic.addPotionToLibrary();
        logic.addPotionToLibrary();
        
        assertEquals(1, logic.potionLibrary.getPotions().size());
    }
    
    
    
}
