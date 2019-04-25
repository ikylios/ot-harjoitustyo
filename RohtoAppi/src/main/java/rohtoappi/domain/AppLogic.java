
package rohtoappi.domain;


import rohtoappi.domain.components.Ingredient;
import rohtoappi.dao.IngredientsHandler;
import rohtoappi.dao.PotionsHandler;
import rohtoappi.domain.components.Potion;

/**
 * Sovelluslogiikka.
 * 
 */
public class AppLogic {
        
    public IngredientLibrary ingredientLibrary;
    public PotionLibrary potionLibrary;
    public Potion tempPotion;

    public AppLogic(IngredientsHandler ingredientsHandler, PotionsHandler potionsHandler) {        
        this.ingredientLibrary = new IngredientLibrary(ingredientsHandler);
        this.potionLibrary = new PotionLibrary(potionsHandler);
        this.tempPotion = new Potion();
    }

    public AppLogic() {
        this.ingredientLibrary = new IngredientLibrary();
        this.potionLibrary = new PotionLibrary();
        this.tempPotion = new Potion();
    }
    
    /**
     * Metodi hakee ingredientLibrarysta aineksen nimen perusteella ja asettaa aineksen määräksi arvon amount.
     * Tätä varten metodi tarkistaa myös, että amount-arvo on luku ja vähemmän kuin yhdeksän merkkiä.
     * Kutsuu sitten työn alla olevan rohdon eli tempPotionin addToPotion-metodia.
     * @param name aineksen nimi
     * @param amount aineksen määrä
     * @return Palauttaa amountIsNotInteger jos amount ei ole luku, muuten palauttaa tempPotion.addToPotionin palautusarvon.
     */     
    public String addToTempPotion(String name, String amount) {
        String retVal = "amountIsNotInteger";
        
        Ingredient fromLibrary = ingredientLibrary.ingredients.get(name.trim().toLowerCase());
        Ingredient ingredient = new Ingredient(fromLibrary.getName(), fromLibrary.getMeasuringUnit());                                                 
                    
        if (amount.matches("\\d+")) {  
            if (amount.length() > 9) {
                retVal = "limit";
            } else {
                ingredient.setAmount(Integer.valueOf(amount));
                if (ingredient.getAmount() > 0) {
                    retVal = tempPotion.addToPotion(ingredient);   
                }  
            }
        }                 
        return retVal;
    }
    
    /**
     * Tyhjentää nykyisen rohdon aineksista.
     */
    public void clearTempPotion() {
        tempPotion = new Potion();        
    }
    
    /**
     * Lisää työn alla olevan rohdon potionLibraryyn. 
     * Kutsuu potionLibraryn addPotion-metodia ja laittaa parametriksi työn alla olevan rohdon.
     * @return 
     */
    public String addPotionToLibrary() {
        return potionLibrary.addPotion(tempPotion);
    }
    
}
