/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rohtoappi.rohtoappi;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xilxilx
 */
public class AppLogic {
    
    public ArrayList<Ingredient> tempPotion;
    public IngredientLibrary ingredientLibrary;
    public PotionLibrary potionLibrary;

    public AppLogic() {
        this.ingredientLibrary = new IngredientLibrary();
        this.potionLibrary = new PotionLibrary();
        this.tempPotion = new ArrayList<>();        
    }    
    
    public boolean addPotion() {
        return true;
    }
    
    public boolean newPotion() {        
        return true;
    }
    
    public ArrayList<Ingredient> getTempPotion() {
        return tempPotion;
    }
    
    public String addToTempPotion(String name, String amount) {
        String retVal = "ingredientPresent";
        
        Ingredient fromLibrary = ingredientLibrary.ingredients.get(name.trim().toLowerCase());
        Ingredient ingredient = new Ingredient(fromLibrary.getName(), fromLibrary.getMeasuringUnit());
            
        if (!tempPotion.contains(ingredient)) {
            System.out.println(tempPotion.contains(ingredient));
            amount.trim();
            if (amount.matches("\\d+")) {                                    
                ingredient.setAmount(Integer.valueOf(amount));
                tempPotion.add(ingredient);            
                retVal = "clear";
            } else {
                retVal = "amountIsNotInteger";
            }
        }
           
        return retVal;
    }
    
}
