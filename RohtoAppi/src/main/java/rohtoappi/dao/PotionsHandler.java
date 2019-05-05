package rohtoappi.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Käsittelee potions.txt-tiedostosta lukemisen ja tiedostoon kirjoittamisen.
 *
 */
public class PotionsHandler implements FileHandler {

    final String file;

    public PotionsHandler(String file) {
        this.file = file;
    }

    /**
     * Lukee oliomuuttujassa määritellyn tiedoston ja lisää rivit listaan.
     *
     * @return Palauttaa ArrayListin, joka voi olla tyhjä tai sisältää
     * merkkijonoja.
     * @throws Exception Jos ei löydä tiedostoa, eli heittää keskeytyksen, luo
     * uuden tiedoston oliomuuttujan mukaan.
     */
    @Override
    public List readFile() throws Exception {
        List<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(file))) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();

        }
        return lines;
    }

    /**
     * Kirjoittaa saadut rivit tiedostoon.
     *
     * @param potions Lista rohdoista.
     * @return Palauttaa true jos kirjoittaminen onnistui, muuten false.
     */
    @Override
    public boolean writeFile(List<String> potions) {
        try (FileWriter writer = new FileWriter(new File(file), false)) {
            for (String line : potions) {
                writer.write(line);
            }
            writer.close();
            return true;
        } catch (Exception e) {

        }
        return false;
    }

}
