package rohtoappi.dao;

import java.util.List;

/**
 * Rajapinta, jossa on metodit tiedostosta lukemiseen ja tiedostoon
 * kirjoittamiseen.
 */
public interface FileHandler {

    public List readFile() throws Exception;

    public boolean writeFile(List<String> lines);

}
