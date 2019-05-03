/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PotionAppTests;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import rohtoappi.dao.IngredientsHandler;

/**
 *
 * @author xilxilx
 */
public class HandlerTest {
    
    @Rule
    public TemporaryFolder tf = new TemporaryFolder();
    
    File ingredientsFile;
    File potionsFile;
    
    public HandlerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        ingredientsFile = tf.newFile("testfile_ingredients.txt");
        potionsFile = tf.newFile("testfile_potions.txt");
        
//        IngredientsHandler ih = new IngredientsHandler(ingredientsFile);
    }
    
    @After
    public void tearDown() {
    }

    
}
