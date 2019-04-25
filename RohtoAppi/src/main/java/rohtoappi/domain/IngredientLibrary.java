
package rohtoappi.domain;

import rohtoappi.domain.components.Ingredient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import rohtoappi.dao.IngredientsHandler;

/**
 * Luokka hallinnoi kaikkia aineksiin liittyviä toimintoja.
 * 
 */
public class IngredientLibrary {
            
    public HashMap<String, Ingredient> ingredients;
    public ArrayList<String> ingredientsNames;
    private Random random;
    private IngredientsHandler ingredientsHandler;  

    public IngredientLibrary(IngredientsHandler ingredientsHandler) {
        this.ingredients = new HashMap<>();
        this.ingredientsNames = new ArrayList<>();
        this.random = new Random();
        this.ingredientsHandler = ingredientsHandler;
        readIngredientsFile();
    }

    public IngredientLibrary() {
        this.ingredients = new HashMap<>();
        this.ingredientsNames = new ArrayList<>();
        this.random = new Random();
    }
    
    /**
     * Vastaanottaa IngredientsHandlerilta listan aineksista ja sijoittaa ne ingredients-tauluun ja nimilistaan hyödyntäen addIngredient-metodia.
     * @return 
     */
    public boolean readIngredientsFile() {       
        List<String> ingredients = ingredientsHandler.readFile();
        for (String line : ingredients) {
            String[] pieces = line.split(";");
            addIngredient(pieces[0], pieces[1]);
        }
        return true;
    }
    
    public boolean writeToFile() {
        List<String> ingredientsList = new ArrayList<>();
        for (Ingredient ingredient : ingredients.values()) {
            String line = ingredient.getName() + ";" + ingredient.getMeasuringUnit() + "\n";
            ingredientsList.add(line);
        }

        ingredientsHandler.writeFile(ingredientsList);
        return true;
    }
    
    /**
     * Lisää ingredientLibraryyn uuden aineksen.
     * Uusi aines-olio luodaan nimen ja mittayksikön perusteella.
     * Aines lisätään ingredients-hajautustauluun, jonka avaimena on aineksen nimi pienellä kirjoitettuna ja ilman välejä. Arvona on aines-olio.
     * Aineksen nimi lisätään ingredientsNames-listaan, ja ingredientsNames järjestetään uudestaan aakkosjärjestykseen.
     * 
     * @param name Aineksen nimi
     * @param measuringUnit Aineksen mittayksikkö
     * 
     * @return Palauttaa fields jos name tai measuringUnit on tyhjä, 
     * duplicate jos ingredientLibraryssa on jo aines samalla nimellä 
     * ja clear jos aines lisättiin onnistuneesti.
     */
    public String addIngredient(String name, String measuringUnit) {
        if (measuringUnit.isEmpty() || name.isEmpty()) {
            return "fields";
        }
        
        String editedName = name.toLowerCase().trim();        
        if (!ingredients.containsKey(editedName)) {
            Ingredient ingredient = new Ingredient(name, measuringUnit);
            ingredients.put(editedName, ingredient);
            ingredientsNames.add(name);
            Collections.sort(ingredientsNames);
            return "clear";
        }
        return "duplicate";
    }
    
    /**
     * Poistaa aineksen aineskirjastosta.
     * @param name Poistettavan aineksen nimi
     * @return Palauttaa onnistuneesta poistosta true, muutoin false.
     */
    public boolean removeIngredient(String name) {
        String editedName = name.toLowerCase().trim();
        if (ingredients.containsKey(editedName)) {
            ingredients.remove(editedName, ingredients.get(editedName));
            ingredientsNames.remove(name);
            return true;
        }
        return false;
    }

    public HashMap<String, Ingredient> getIngredients() {
        return ingredients;
    }        
    
    public Ingredient getIngredientByName(String name) {        
        return ingredients.get(name.trim().toLowerCase());
    }

    public ArrayList getIngredientsNames() {        
        return ingredientsNames;
    }
    
    /** 
     * Valitsee satunnaisen aineksen aineskirjastosta.
     * @return Palauttaa aines-olion.
     */
    public Ingredient getRandomIngredient() {
        int randomIndex = random.nextInt(ingredientsNames.size());
        if (randomIndex == ingredientsNames.size()) {
            randomIndex--;
        }
        
        String ingredientName = ingredientsNames.get(randomIndex);
        Ingredient ingredient = getIngredientByName(ingredientName);
        
        return ingredient;
    }
    
}
