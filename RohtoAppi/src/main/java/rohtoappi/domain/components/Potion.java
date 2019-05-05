package rohtoappi.domain.components;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Luokka kuvastaa rohtoa.
 *
 */
public class Potion {

    public ArrayList<Ingredient> ingredients;
    public ArrayList<String> ingredientsString;
    public String name;
    public String effect;
    public String type;
    public Magic magic;

    public Potion() {
        this.ingredients = new ArrayList<>();
        this.ingredientsString = new ArrayList<>();
        magic = new Magic();
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<String> getIngredientsString() {
        return ingredientsString;
    }

    /**
     * Hakee aineksen nimen perusteella ingredients-listasta.
     *
     * @param name Haetun aineksen nimi
     * @return Palauttaa null jos rohdossa ei ole haettua ainesta, muutoin
     * palauttaa etsityn aines-olion.
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
     *
     * @param ingredient Annettu ingredient-olio.
     * @return Palauttaa ingredientPresent jos rohdossa on jo annettu aines,
     * muutoin palauttaa clear.
     */
    public String addToPotion(Ingredient ingredient) {
        String retVal = "ingredientPresent";

        if (!ingredients.contains(ingredient)) {
            ingredients.add(ingredient);
            ingredientsString.add(ingredient.toString());
            retVal = "clear";
        }
        sort();
        return retVal;
    }

    /**
     * Poistaa rohdosta aineksen nimen perusteella.
     *
     * @param name Aineksen nimi
     * @return Palauttaa notInPotion jos ainetta ei ole rohdossa, clear jos
     * aines poistetaan onnistuneesti ja invalidValue jos annettu nimi on tyhjä.
     */
    public String removeFromPotion(String name) {
        String retVal = "invalidValue";
        if (!name.isEmpty()) {
            Ingredient ingredient = getIngredientByName(name);
            ingredientsString.remove(ingredient.toString());
            ingredients.remove(ingredient);
            sort();
            retVal = "clear";
        }
        return retVal;
    }

    /**
     * Muokkaa annetun aineksen nimen perusteella aineksen määrää. Korvaa
     * ingredients- ja ingredientsString-listoista vanhat ainekset uusilla.
     *
     * @param name Aineksen nimi
     * @param amount Uusi aineksen määrä
     * @return Palauttaa clear jos toimitus suoritetaan onnistuneesti, NotValid
     * jos annettu arvo ei ole sallittu luku.
     */
    public String editAmount(String name, String amount) {
        String retVal = "NotValid";
        if (checkDigits(amount)) {
            int valueAmount = Integer.valueOf(amount);
            Ingredient ingredient = getIngredientByName(name);
            ingredientsString.remove(ingredient.toString());
            ingredient.setAmount(valueAmount);
            ingredientsString.add(ingredient.toString());
            sort();
            retVal = "clear";
        }
        return retVal;
    }

    /**
     * Apumetodi tarkistamaan, että annettu arvo on sallittu eli arvo on luku
     * sekä positiivinen luku.
     *
     * @param amount Annettu arvo
     * @return Palauttaa true jos arvo on luku ja positiivinen luku, muutoin
     * false.
     */
    private boolean checkDigits(String amount) {
        if (amount.matches("\\d+")) {
            if (Integer.valueOf(amount) > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Apumetodi, joka järjestää ingredients-taulun ja ingredientsString-listan
     * aakkosjärjestykseen.
     */
    private void sort() {
        Collections.sort(ingredients);
        Collections.sort(ingredientsString);
    }

    /**
     * Pyytää magic-oliolta taulukon, jossa on nimi, tyyppi ja efekti. Asettaa
     * nämä pyydetyt arvot rohdon tyypiksi, efektiksi ja nimeksi.
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
