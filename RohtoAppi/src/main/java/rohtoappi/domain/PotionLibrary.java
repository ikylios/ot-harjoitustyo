
package rohtoappi.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import rohtoappi.dao.PotionsHandler;
import rohtoappi.domain.components.Ingredient;
import rohtoappi.domain.components.Potion;

/**
 * Luokka hallinnoi kaikkia rohtokokoelmaan liittyviä toimintoja.
 * 
 */
public class PotionLibrary {
    
    public ArrayList potionsNames;
    public HashMap<String, Potion> potions;
    private PotionsHandler potionsHandler;

    public PotionLibrary() {
        this.potionsNames = new ArrayList<>();
        this.potions = new HashMap<>();                
    }

    public PotionLibrary(PotionsHandler potionsHandler) {
        this.potionsNames = new ArrayList<>();
        this.potions = new HashMap<>(); 
        this.potionsHandler = potionsHandler;
        readPotionsFile();
    }
    
    /**
     * Vastaanottaa potionsHandlerilta listan rohdoista ja sijoittaa ne potions-tauluun ja potionsNames-listaan.
     * @return Palauttaa true jos lukeminen onnistuu, muuten false.
     */
    public boolean readPotionsFile() {
        List<String> lines = potionsHandler.readFile();
        Potion potion = new Potion();
        Ingredient ingredient = new Ingredient();
        
        int i = 0;
        while (i < lines.size()) {
            String line = lines.get(i);
            if (i == 0) {
                potion.setName(lines.get(i));
                potion.setType(lines.get(i+1));
                potion.setEffect(lines.get(i+2));
                i +=3;
            }
            if (line.isEmpty()) {
                addPotion(potion);
                potion = new Potion();
                i++;
                if (i < lines.size()-1) {
                    potion.setName(lines.get(i));
                    potion.setType(lines.get(i+1));
                    potion.setEffect(lines.get(i+2));
                    i +=3;
                } else {
                    return true;
                }
            }            
            ingredient.setName(lines.get(i));
            ingredient.setAmount(Integer.valueOf(lines.get(i+1)));
            ingredient.setMeasuringUnit(lines.get(i+2));
            potion.addToPotion(ingredient);
            i +=3;
            ingredient = new Ingredient();
        }                
            
        
//        for (String line : lines) {
//            Potion potion = new Potion();
//            
//            String[] linePieces = line.split(";");            
//            potion.setName(linePieces[0]);
//            potion.setType(linePieces[1]);
//            potion.setEffect(linePieces[2]);
//            
//            for (int i = 3; i < linePieces.length; i++) {
//                String[] ingredientPieces = linePieces[i].split("|");
//                Ingredient ingredient = new Ingredient(ingredientPieces[0], ingredientPieces[1]);
//                ingredient.setAmount(Integer.valueOf(ingredientPieces[2]));
//                potion.addToPotion(ingredient);
//            }
//                            
//            addPotion(potion);
//        }
//        return true;
    return false;
    }
    
    public boolean writeToFile() {
        List<String> potionsList = new ArrayList<>();        
        for (Potion potion : potions.values()) {
            potionsList.add(potion.getName()+"\n");
            potionsList.add(potion.getType()+"\n");
            potionsList.add(potion.getEffect()+"\n");
            for (Ingredient ingredient : potion.getIngredients()) {
                potionsList.add(ingredient.getName()+"\n");
                potionsList.add(""+ingredient.getAmount()+"\n");
                potionsList.add(ingredient.getMeasuringUnit()+"\n");
            }
            potionsList.add("\n");
        }
//            String line = potion.getName() + ";" + potion.getType() + ";" + potion.getEffect() + ";";
//            for (Ingredient ingredient : potion.getIngredients()) {
//                line += ingredient.getName() + "|" + ingredient.getMeasuringUnit() + "|" + ingredient.getAmount() +";";
//            }
//            line += "\n";
//            potionsList.add(line);
//        }
        potionsHandler.writeFile(potionsList);
        return true;
    }

    public HashMap getPotions() {
        return potions;
    }

    public ArrayList getPotionsNames() {
        return potionsNames;
    }
    
    /**
     * Lisää rohdon potions-tauluun ja rohdon nimen potionsNames-listaan.
     * @param potion Annettu rohto
     * @return Palauttaa noSpace jos potionsLibrary on täynnä ja enempää rohtoja ei voida tallentaa, 
     * palauttaa sameName jos potionLibraryssa on rohto, jolla on sama nimi, 
     * muutoin palauttaa clear.
     */
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
    
    /**
     * Palauttaa rohdon kirjastosta nimen perusteella.
     * @param name Rohdon nimi
     * @return Palauttaa etsityn rohdon jos sellainen löytyy, muutoin palauttaa null.
     */
    public Potion getPotionByName(String name) {
        if (potionsNames.contains(name)) {
            return this.potions.get(name.trim().toLowerCase());
        }
        return null;
    }
    
    /**
     * Poistaa rohdon rohtokirjastosta.
     * @param name Poistettavan rohdon nimi
     * @return Palauttaa onnistuneesta poistosta clear, muutoin error.
     */
    public String deletePotion(String name) {
        if (potionsNames.contains(name)) {
            potions.remove(name.trim().toLowerCase());
            potionsNames.remove(name);            
            return "clear";
        }
        return "error";
    }
        
    
    
    
    
    
}
