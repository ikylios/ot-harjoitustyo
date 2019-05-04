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

/**
 *
 * @author xilxilx
 */
public class AppLogicTest {

    AppLogic logic;

    public AppLogicTest() {
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
    public void randomiseTest() {
        logic.ingredientLibrary.addIngredient("chuck norris", "invincibles");
        logic.addToTempPotion("chuck norris", "40");
        assertEquals("chuck norris", logic.ingredientLibrary.ingredientsNames.get(0));
        logic.ingredientLibrary.removeIngredient("chuck norris");
        logic.ingredientLibrary.addIngredient("jackie chan", "masters");
        logic.randomisePotion();
        assertEquals(false, logic.ingredientLibrary.ingredientsNames.get(0).equals("chuck norris"));
    }

}
