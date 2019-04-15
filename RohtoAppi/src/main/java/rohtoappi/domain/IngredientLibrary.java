/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rohtoappi.domain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import rohtoappi.domain.components.Ingredient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author xilxilx
 */
public class IngredientLibrary {
        
    public HashMap<String, Ingredient> ingredients;
    public ArrayList<String> ingredientsNames;
    private Random random;

    public IngredientLibrary() {
        this.ingredients = new HashMap<>();
        this.ingredientsNames = new ArrayList<>();
        this.random = new Random();
        readIngredientFile();        
    }

//    FILE JA REMOVE FROM INGREDIENTLIBRARY YHTEENSOPIVUUS (TESTIT PUUTTUU VIELÄ)
//    TEE ERILLINEN LUOKKA FILEESEENKIRJOITTAMISELLE ????
//    CHUCK NORRIS ON FILEESSÄ! KORJAA.
//    POTION LIBRARY ON LISTVIEW
    
    public boolean readIngredientFile() {
        try (Scanner scanner = new Scanner(new File("ingredients.txt"))) {
            while (scanner.hasNextLine()) {                
                String[] pieces = scanner.nextLine().split(";");
                addIngredient(pieces[0], pieces[1]);
            }
            return true;
        } catch (Exception e) {

        }
        return false;
    }
    
    public boolean writeToFile(HashMap<String, Ingredient> ingredientsMap) {        
        try (FileWriter writer = new FileWriter(new File("ingredients.txt"), false)) {
            for (Ingredient ingredient : ingredientsMap.values()) {
                String line = ingredient.getName() + ";" + ingredient.getMeasuringUnit() + "\n";                
                writer.write(line);
            }            
            writer.close();
            return true;
        } catch (Exception e) {

        }
        return false;
    }
    
    public String addIngredient(String name, String measuringUnit) {
        if (measuringUnit.isEmpty() || name.isEmpty()) {
            return "fields";
        }
        
        String editedName = name.toLowerCase().trim();        
        if (!ingredients.containsKey(editedName)) {
            Ingredient ingredient = new Ingredient(name, measuringUnit);
            ingredients.put(editedName, ingredient);
            ingredientsNames.add(name);
            Collections.sort(ingredientsNames);
            return "clear";
        }
        return "duplicate";
    }
    
    public boolean removeIngredient(String name) {
        String editedName = name.toLowerCase().trim();
        if (ingredients.containsKey(editedName)) {
            ingredients.remove(editedName, ingredients.get(editedName));
            ingredientsNames.remove(name);
            return true;
        }
        return false;
    }

    public HashMap<String, Ingredient> getIngredients() {
        return ingredients;
    }        
    
    public Ingredient getIngredientByName(String name) {        
        return ingredients.get(name.trim().toLowerCase());
    }

    public ArrayList getIngredientsNames() {        
        return ingredientsNames;
    }
    
    public Ingredient getRandomIngredient() {
        int randomIndex = random.nextInt(ingredientsNames.size());
        if (randomIndex == ingredientsNames.size()) {
            randomIndex--;
        }
        
        String ingredientName = ingredientsNames.get(randomIndex);
        Ingredient ingredient = getIngredientByName(ingredientName);
        
        return ingredient;
    }
    
}
