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
    private int amount;

    public Ingredient(String name, String measuringUnit) {
        this.name = name;
        this.measuringUnit = measuringUnit;        
        this.amount = 0;
    }

    public String getName() {
        return name;
    }

    public String getMeasuringUnit() {
        return measuringUnit;
    }

    public int getAmount() {
        return amount;
    }                
    
    public boolean setAmount(int amount) {
        if (amount > 0) {
            this.amount = amount;
            return true;
        }        
        return false;
    }

    @Override
    public boolean equals(Object o) {
        Ingredient i = (Ingredient) o;
        if (this.name.equals(i.name) && this.measuringUnit.equals(i.measuringUnit)) {
            return true;
        }
        return false;        
    }

    
    
    @Override
    public int hashCode() {        
        return this.name.hashCode(); 
    }
    
    

    
    
    
    
    
}
