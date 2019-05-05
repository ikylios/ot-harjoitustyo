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

    public PotionLibrary(PotionsHandler potionsHandler) throws Exception {
        this.potionsNames = new ArrayList<>();
        this.potions = new HashMap<>();
        this.potionsHandler = potionsHandler;
        readPotionsFile();
    }

    /**
     * Vastaanottaa potionsHandlerilta listan rohdoista ja sijoittaa ne
     * potions-tauluun ja potionsNames-listaan.
     *
     */
    final void readPotionsFile() throws Exception {
        List<String> lines = potionsHandler.readFile();

        if (!lines.isEmpty()) {

            for (String line : lines) {
                Potion potion = new Potion();

                String[] linePieces = line.split(";");
                potion.setName(linePieces[0]);
                potion.setType(linePieces[1]);
                potion.setEffect(linePieces[2]);

                int i = 3;
                while (i < linePieces.length) {
                    Ingredient ingredient = new Ingredient(linePieces[i], Integer.valueOf(linePieces[i + 1]), linePieces[i + 2]);
                    i += 3;
                    potion.addToPotion(ingredient);
                }
                addPotion(potion);
            }
        }
    }

    /**
     * Muuntaa rohdot ja niiden ainekset merkkijonoiksi, jotka tallennetaan
     * ArrayListiin. ArrayList annetaan eteenpäin potionsHandlerille, joka tekee
     * varsinaisen tiedostoonkirjoittamisen.
     *
     */
    public void writeToFile() {
        List<String> potionsList = new ArrayList<>();
        for (Potion potion : potions.values()) {
            String line = potion.getName() + ";" + potion.getType() + ";" + potion.getEffect() + ";";

            int i = 0;
            while (i < potion.getIngredients().size()) {
                Ingredient ingredient = potion.getIngredients().get(i);
                line += ingredient.getName() + ";" + ingredient.getAmount() + ";" + ingredient.getMeasuringUnit();
                i++;
                if (i < potion.getIngredients().size()) {
                    line += ";";
                } else {
                    line += "\n";
                }
            }
            potionsList.add(line);
        }

        potionsHandler.writeFile(potionsList);
    }

    public HashMap getPotions() {
        return potions;
    }

    public ArrayList getPotionsNames() {
        return potionsNames;
    }

    /**
     * Lisää rohdon potions-tauluun ja rohdon nimen potionsNames-listaan.
     *
     * @param potion Annettu rohto
     * @return Palauttaa noSpace jos potionsLibrary on täynnä ja enempää rohtoja
     * ei voida tallentaa, palauttaa sameName jos potionLibraryssa on rohto,
     * jolla on sama nimi, muutoin palauttaa clear.
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
     *
     * @param name Rohdon nimi
     * @return Palauttaa etsityn rohdon jos sellainen löytyy, muutoin palauttaa
     * null.
     */
    public Potion getPotionByName(String name) {
        if (potionsNames.contains(name)) {
            return this.potions.get(name.trim().toLowerCase());
        }
        return null;
    }

    /**
     * Poistaa rohdon rohtokirjastosta.
     *
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
