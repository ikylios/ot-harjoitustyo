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
public class Type {
    
    ArrayList<String> types;

    public Type() {
        types = new ArrayList<>();
        types.add("Tea");
        types.add("Broth");
        types.add("Lotion");
        types.add("Potion");
        types.add("Salve");
    }
        
    public ArrayList getTypes() {
        return types;
    }
    
    public String getAType() {        
        Random random = new Random();        
        return types.get(random.nextInt(types.size()-1));        
    }        
    
}
