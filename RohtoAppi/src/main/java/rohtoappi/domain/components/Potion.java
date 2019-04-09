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
    public String type;
    public Magic magic;

    public Potion() {
        this.ingredients = new ArrayList<>();
        magic = new Magic();
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
    
    public void generateMagic() {
        String[] array = magic.generate();        
        setType(array[0]);
        setEffect(array[1]);        
        setName(array[2]);                
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getEffect() {
        return effect;
    }        

    public void setName(String name) {
        this.name = name;
    }        
    
    public void setEffect(String value) {        
        effect = value;
    }
    
    public void setType(String value) {
        type = value;
    }
    
}
