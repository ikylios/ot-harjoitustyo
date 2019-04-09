/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rohtoappi.domain.components;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author xilxilx
 */
public class Magic {
    
    ArrayList<String> types;
    ArrayList<String> effects;
    Random random;
//    AAAAAAAA UUSI

    public Magic() {
        random = new Random();
        types = new ArrayList<>();
        types.add("Tea");
        types.add("Broth");
        types.add("Lotion");
        types.add("Potion");
        types.add("Salve");
        types.add("Paste");
        effects = new ArrayList<>();
        effects.add("Healing");
        effects.add("Youth");
        effects.add("Poison");
        effects.add("Vitality");
        effects.add("???");
        effects.add("Rampage");
    }            
    
    public String getAType() {                
        int randIndex = random.nextInt(types.size());
        if (randIndex == types.size()) {
            randIndex--;
        }
        return types.get(randIndex);        
    }                
    
    public String getAnEffect() {                
        int randIndex = random.nextInt(effects.size());
        if (randIndex == effects.size()) {
            randIndex--;
        }
        return effects.get(randIndex);        
    }
    
    public String[] generate() {
        String[] array = new String[3];        
        array[0] = getAType();
        array[1] = getAnEffect();
        
        int rand = random.nextInt(2);
        
        if (rand == 0) {
            array[2] = array[0] + " of " + array[1]; 
        } else {
            array[2] = array[1] + " " + array[0];
        }
        
        return array;
    }
}
