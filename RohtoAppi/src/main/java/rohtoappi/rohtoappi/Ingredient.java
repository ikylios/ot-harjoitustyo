/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rohtoappi.rohtoappi;

/**
 *
 * @author xilxilx
 */
public class Ingredient {
    
    private String name;    
    private String measuringUnit;

    public Ingredient(String name, String measuringUnit) {
        this.name = name;
        this.measuringUnit = measuringUnit;        
    }

    public String getName() {
        return name;
    }

    public String getMeasuringUnit() {
        return measuringUnit;
    }        
    
}
