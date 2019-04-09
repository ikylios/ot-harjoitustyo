/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rohtoappi.domain;


import rohtoappi.domain.components.Ingredient;
import java.util.ArrayList;
import java.util.List;
import rohtoappi.domain.components.Potion;

/**
 *
 * @author xilxilx
 */
public class AppLogic {
        
    public IngredientLibrary ingredientLibrary;
    public PotionLibrary potionLibrary;
    public Potion tempPotion;

    public AppLogic() {
        this.ingredientLibrary = new IngredientLibrary();
        this.potionLibrary = new PotionLibrary();
        this.tempPotion = new Potion();
    }        
    
 
    public String addToTempPotion(String name, String amount) {
        String retVal = "amountIsNotInteger";
        
        Ingredient fromLibrary = ingredientLibrary.ingredients.get(name.trim().toLowerCase());
        Ingredient ingredient = new Ingredient(fromLibrary.getName(), fromLibrary.getMeasuringUnit());                                                 
                    
        if (amount.matches("\\d+")) {                                    
                ingredient.setAmount(Integer.valueOf(amount));
                retVal = tempPotion.addToPotion(ingredient);                            
            }
                 
        return retVal;
    }
    
}
