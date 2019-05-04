/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PotionAppTests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import rohtoappi.dao.IngredientsHandler;
import rohtoappi.dao.PotionsHandler;
import rohtoappi.domain.IngredientLibrary;
import rohtoappi.domain.PotionLibrary;

public class HandlerTest {

    @Rule
    public TemporaryFolder tf = new TemporaryFolder();

    File ingredientsFile;
    File potionsFile;

    IngredientsHandler ih;
    PotionsHandler ph;

    IngredientLibrary ingLib;
    PotionLibrary poLib;

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

        try (FileWriter file = new FileWriter(potionsFile.getAbsolutePath())) {
            file.write("Frost Perfume;Perfume;Frost;sparrow feather;37;pieces;octopus ink;23;ml;ivy leaf;9;pieces;fairy dust;15;g\n");
            file.write("Healing Potion;Potion;Healing;sparrow feather;17;pieces;fairy dust;36;g\n");
        }

        try (FileWriter file = new FileWriter(ingredientsFile.getAbsolutePath())) {
            file.write("holy water;mg\n");
            file.write("frog leg;pieces\n");
            file.write("octopus ink;ml\n");
        }

        ih = new IngredientsHandler(ingredientsFile.getAbsolutePath());
        ph = new PotionsHandler(potionsFile.toString());

        ingLib = new IngredientLibrary(ih);
        poLib = new PotionLibrary(ph);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void readsIngredients() {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(ingredientsFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }

        } catch (Exception e) {
        }
        assertEquals(3, lines.size());
    }

    @Test
    public void readsPotions() {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(potionsFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }

        } catch (Exception e) {
        }
        assertEquals(2, lines.size());
    }

    @Test
    public void handlerGivesIngLibLines() {
        assertEquals(3, ingLib.getIngredients().size());
    }

    @Test
    public void handlerGivesPoLibLines() {
        assertEquals(2, poLib.getPotions().size());
    }

    @Test
    public void writesToIngredientFile() throws Exception {
        List<String> lines = new ArrayList<>();
        lines.add("carrot;g\n");
        lines.add("ink;mg\n");
        lines.add("chuckian norrian;ng\n");
        assertEquals(true, ih.writeFile(lines));
        lines = ih.readFile();
        assertEquals(3, lines.size());
        assertEquals("carrot;g", lines.get(0));
    }

    @Test
    public void writesToPotionFile() throws Exception {
        List<String> lines = new ArrayList<>();
        lines.add("Sneak Broth;Broth;Sneak;ivy leaf;9;pieces;crocodile tooth;9;pieces\n");
        lines.add("Broth of Endurance;Broth;Endurance;dragon scale;19;mg;bay leaf;24;pieces\n");
        assertEquals(true, ph.writeFile(lines));
        lines = ph.readFile();
        assertEquals(2, lines.size());
    }
}
