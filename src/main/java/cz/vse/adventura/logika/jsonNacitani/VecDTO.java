package cz.vse.adventura.logika.jsonNacitani;

/**
 * Tato třída slouží jako mezičlánek mezi JSON a třídou, do které se daný JSON  serializuje
 *
 * @author Alex Milanů
 * @version pro školní rok 2024/2025
 */
public record VecDTO(String nazev, boolean prenositelna, int hmotnost, boolean pouzitelna)
{}

