/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rohtoappi.domain;

import rohtoappi.domain.components.Ingredient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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
        
        addIngredient("fairy dust", "g");
        addIngredient("sparrow feather", "pieces");
        addIngredient("crocodile tooth", "pieces");
        addIngredient("lily petals", "g");                
    }
    
    public String addIngredient(String name, String measuringUnit) {
        if (measuringUnit.isEmpty() || name.isEmpty()) {
            return "fields";
        }
        
        String editedName = name.toLowerCase().trim();        
        if (!ingredients.containsKey(editedName)){
            Ingredient ingredient = new Ingredient(name, measuringUnit);
            ingredients.put(editedName, ingredient);
            ingredientsNames.add(name);            
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
