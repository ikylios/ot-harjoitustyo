/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rohtoappi.domain.components;

import java.util.ArrayList;

/**
 *
 * @author xilxilx
 */
public class Potion {
    
    public ArrayList<Ingredient> ingredients;    
    public String name;
    public String effect;
    public Type type;

    public Potion() {
        this.ingredients = new ArrayList<>();
    }
    
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
    
    public Ingredient getIngredientByName(String name) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(name)) {
                return ingredient;
            }
        }
        return null;
    }
    
    public String addToPotion(Ingredient ingredient) {                
        String retVal = "ingredientPresent";
        
        if (!ingredients.contains(ingredient)) {            
            ingredients.add(ingredient);            
            retVal = "clear";            
        }           
        return retVal;
    }
    
    public String removeFromPotion(String name) {
        if (!name.isEmpty()) {            
            Ingredient ingredient = getIngredientByName(name);
            if (ingredient == null) {
                return "notInPotion";
            }
            ingredients.remove(ingredient);            
            return "clear";
        }        
        return "invalidValue";                
    }
    
    public boolean emptyPotion() {
        ingredients.clear();
        if (ingredients.isEmpty()) {
            return true;
        }
        return false;
    }
    
    
}
