
package rohtoappi.domain.components;

import java.util.ArrayList;
import java.util.Random;

/**
 * Luokka vastaa rohtojen tyypistä ja efektistä.
 * 
 */
public class Magic {
    
    ArrayList<String> types;
    ArrayList<String> effects;
    Random random;

    public Magic() {
        random = new Random();
        types = new ArrayList<>();
        types.add("Tea");
        types.add("Broth");
        types.add("Lotion");
        types.add("Potion");
        types.add("Salve");
        types.add("Paste");
        effects = new ArrayList<>();
        effects.add("Healing");
        effects.add("Youth");
        effects.add("Poison");
        effects.add("Vitality");
        effects.add("???");
        effects.add("Rampage");
        effects.add("Beauty");
        effects.add("Curses");
        effects.add("Confusion");
        effects.add("Restoration");
        effects.add("Charm");
        effects.add("Deafness");
        effects.add("Silencing");
        effects.add("Joy");
        effects.add("Sneak");
        effects.add("Duplicity");
        effects.add("Telepathy");
    }            
    
    /**
     * Metodi valitsee satunnaisesti tyyppilistasta tyypin.
     * @return Palauttaa merkkijonon, jossa on tyyppi.
     */
    public String getAType() {                
        int randIndex = random.nextInt(types.size());
        if (randIndex == types.size()) {
            randIndex--;
        }
        return types.get(randIndex);        
    }                
    
    /**
     * Metodi valitsee satunnaisesti efektilistasta efektin.
     * @return Palauttaa merkkijonon, jossa on efekti.
     */
    public String getAnEffect() {                
        int randIndex = random.nextInt(effects.size());
        if (randIndex == effects.size()) {
            randIndex--;
        }
        return effects.get(randIndex);        
    }
    
    /**
     * Metodi generoi rohdolle tyypin ja efektin, minkä jälkeen generoidaan nimi rohdolle. 
     * Nimi koostuu efektistä ja tyypistä. Nimellä on 50/50 mahdollisuus olla muotoa [tyyppi] of [efekti] tai [efekti] [tyyppi].
     * @return Palauttaa taulukon, jossa ensimmäisessä indeksissä on tyyppi, toisessa efekti, ja kolmannessa nimi.
     */
    public String[] generate() {
        String[] array = new String[3];        
        array[0] = getAType();
        array[1] = getAnEffect();
        
        int rand = random.nextInt(2);
        
        if (rand == 0) {
            array[2] = array[0] + " of " + array[1]; 
        } else {
            array[2] = array[1] + " " + array[0];
        }
        
        return array;
    }
}
