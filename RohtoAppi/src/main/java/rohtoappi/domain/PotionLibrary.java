/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rohtoappi.domain;

import java.util.ArrayList;
import java.util.HashMap;
import rohtoappi.domain.components.Potion;

/**
 *
 * @author xilxilx
 */
public class PotionLibrary {
    
    ArrayList potionsNames;
    HashMap<String, Potion> potions;

    public PotionLibrary() {
        this.potionsNames = new ArrayList<>();
        potions = new HashMap<>();
                
    }

    public ArrayList getPotions() {
        return potionsNames;
    }

    public ArrayList getPotionsNames() {
        return potionsNames;
    }
    
    public String addPotion(Potion potion) {
        String retVal = "noSpace";
        
        if (potions.size() <= 50) {
            retVal = "sameName";
            String editedName = potion.getName().trim().toLowerCase();
            if (!potions.keySet().contains(editedName)) {
                potions.put(editedName, potion);
                potionsNames.add(potion.getName());
                retVal = "clear";
            }
        }

        return retVal;
    }
    
    public Potion getPotionByName(String name) {
        if (potionsNames.contains(name)) {
            return this.potions.get(name.trim().toLowerCase());
        }
        return null;
    }
        
    
    
    
    
    
}
