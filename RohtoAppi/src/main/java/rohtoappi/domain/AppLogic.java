
package rohtoappi.domain;


import java.util.Random;
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
        String retVal = "invalidValue";
        
        Ingredient fromLibrary = ingredientLibrary.ingredients.get(name.trim().toLowerCase());
        Ingredient ingredient = new Ingredient(fromLibrary.getName(), fromLibrary.getMeasuringUnit());                                                 
                    
        if (amount.matches("\\d+") && Integer.valueOf(amount) > 0) {  
            if (amount.length() > 9) {
                retVal = "limit";            
            } else {
                ingredient.setAmount(Integer.valueOf(amount));                
                retVal = tempPotion.addToPotion(ingredient);   
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
     * Arpoo rohdolle satunnaisen sisällön. Tyhjentää nykyisen rohdon. Arpoo enintään niin monta ainesta kun ingredientLibraryssa on. Aineksien määrät ovat väliltä 1-40.
     */
    public void randomisePotion() {
        Random random = new Random();
        tempPotion = new Potion();
        
        int amountOfIngredients = random.nextInt(8)+1;     
        
        if (amountOfIngredients > ingredientLibrary.ingredients.size()) {
            amountOfIngredients = ingredientLibrary.ingredients.size()-1;
        }
        
        int i = 0;
        while (i < amountOfIngredients) {
            Ingredient ingredient = ingredientLibrary.getRandomIngredient();
            if (!tempPotion.ingredients.contains(ingredient)) {
                ingredient.setAmount(random.nextInt(39)+1);
                tempPotion.addToPotion(ingredient);
                i++;
            }               
        }
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
