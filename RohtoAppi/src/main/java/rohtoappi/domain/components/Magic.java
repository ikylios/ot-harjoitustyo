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
        types.add("Perfume");
        types.add("Spray");
        types.add("Serum");
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
        effects.add("Muteness");
        effects.add("Deafness");
        effects.add("Silencing");
        effects.add("Joy");
        effects.add("Sneak");
        effects.add("Duplicity");
        effects.add("Telepathy");
        effects.add("Teleportation");
        effects.add("Endurance");
        effects.add("Unending Willpower");
        effects.add("Weakened Bladder");
        effects.add("Delusion");
        effects.add("Feline Behavior");
        effects.add("Frost");
        effects.add("Vigor");
        effects.add("Fire");
        effects.add("Flame");
        effects.add("Lightning");
        effects.add("Sparking");
        effects.add("Slipping");
        effects.add("Temporary Friendship");
        effects.add("Deep Doubt");
        effects.add("Reconsideration");
        effects.add("Instant Death");
    }

    /**
     * Metodi valitsee satunnaisesti tyyppilistasta tyypin.
     *
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
     *
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
     * Metodi generoi rohdolle tyypin ja efektin, minkä jälkeen generoidaan nimi
     * rohdolle. Nimi koostuu efektistä ja tyypistä. Nimellä on 50/50
     * mahdollisuus olla muotoa [tyyppi] of [efekti] tai [efekti] [tyyppi].
     *
     * @return Palauttaa taulukon, jossa ensimmäisessä indeksissä on tyyppi,
     * toisessa efekti, ja kolmannessa nimi.
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
