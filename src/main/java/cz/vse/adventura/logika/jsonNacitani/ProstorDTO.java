package cz.vse.adventura.logika.jsonNacitani;
import java.util.List;

/**
 * Tato třída slouží jako mezičlánek mezi JSON a třídou, do které se daný JSON serializuje
 *
 * @author Alex Milanů
 * @version pro školní rok 2024/2025
 */
public record ProstorDTO(
        String nazev,
        String popis,
        List<String> vychody,
        List<String> veci,
        List<String> postavy
) {}
