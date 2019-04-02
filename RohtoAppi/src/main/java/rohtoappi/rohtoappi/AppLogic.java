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
    
    public IngredientLibrary ingredientLibrary;
    public PotionLibrary potionLibrary;

    public AppLogic() {
        this.ingredientLibrary = new IngredientLibrary();
        this.potionLibrary = new PotionLibrary();
    }    
    
    public boolean addPotion() {
        return true;
    }
    
    public boolean newPotion() {
        return true;
    }
    
}
