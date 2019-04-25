
package rohtoappi.domain.components;

import java.util.ArrayList;

/**
 * Luokka kuvastaa rohtoa.
 * 
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
    
    /**
     * 
     * @param name haetun
     * @return 
     */
    public Ingredient getIngredientByName(String name) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equals(name)) {
                return ingredient;
            }
        }
        return null;
    }
    
    /**
     * Lisää annetun aineksen rohtoon.
     * @param ingredient Annettu ingredient-olio.
     * @return Palauttaa ingredientPresent jos rohdossa on jo annettu aines, muutoin palauttaa clear.
     */
    public String addToPotion(Ingredient ingredient) {                
        String retVal = "ingredientPresent";
        
        if (!ingredients.contains(ingredient)) {            
            ingredients.add(ingredient);            
            retVal = "clear";            
        }           
        return retVal;
    }
    /**
     * Poistaa rohdosta aineksen nimen perusteella.
     * @param name Aineksen nimi
     * @return Palauttaa notInPotion jos ainetta ei ole rohdossa, clear jos aines poistetaan onnistuneesti ja invalidValue jos annettu nimi on tyhjä.
     */
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
    
    /**
     * Pyytää magic-oliolta taulukon, jossa on nimi, tyyppi ja efekti.
     * Asettaa nämä pyydetyt arvot rohdon tyypiksi, efektiksi ja nimeksi.
     */
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
