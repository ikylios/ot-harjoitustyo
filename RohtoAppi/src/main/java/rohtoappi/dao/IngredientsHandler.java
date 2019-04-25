
package rohtoappi.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import rohtoappi.domain.components.Ingredient;

/**
 * Käsittelee ingredient.txt-tiedostosta lukemisen ja tiedostoon kirjoittamisen.
 * 
 */
public class IngredientsHandler implements FileHandler {

    private String file;

    public IngredientsHandler(String file) {
        this.file = file;
    }        
    
    @Override
    public List readFile() {
        List lines = new ArrayList<>();
        
        try (Scanner scanner = new Scanner(new File(file))) {
            while (scanner.hasNextLine()) {                
                lines.add(scanner.nextLine());
            }
            return lines;
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public boolean writeFile(List<String> ingredients) {        
    try (FileWriter writer = new FileWriter(new File(file), false)) {
            for (String line : ingredients) {                            
                writer.write(line);
            }            
            writer.close();
            return true;
        } catch (Exception e) {  
            
        }
    return false;
    }
    
}
