/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rohtoappi.rohtoappi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author xilxilx
 */
public class IngredientLibrary {
    
    public HashMap<String, Ingredient> ingredients;
    public ArrayList<String> ingredientsNames;

    public IngredientLibrary() {
        this.ingredients = new HashMap<>();
        this.ingredientsNames = new ArrayList<>();
        
        this.addIngredient("fairy dust", "g");
        this.addIngredient("sparrow feather", "pieces");
        this.addIngredient("crocodile tooth", "pieces");
        this.addIngredient("lily petals", "g");                
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

    
    public String getSingleIngredientName(int i) {
        return ingredientsNames.get(i);
    }

    public ArrayList getIngredientsNames() {        
        return ingredientsNames;
    }                
    
}
