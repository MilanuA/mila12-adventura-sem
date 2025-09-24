package cz.vse.adventura.logika.jsonNacitani;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.vse.adventura.logika.veci.Vec;
import cz.vse.adventura.logika.Postava;
import cz.vse.adventura.logika.veci.VecFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Třída {@code JsonLoader} slouží pro načítání herních dat ze souborů ve formátu JSON
 * Načítá seznamy předmětů (věcí), postav a dat o prostorech pro hru
 *
 * @author Alex Milanů
 * @version pro školní rok 2024/2025
 */
public class JsonLoader {

    /**
     * Načítá seznam věcí ze souboru ve formátu JSON
     * Používá {@code VecDTO} a tovární metodu pro vytvoření instancí třídy {@code Vec} nebo jejích podtříd
     *
     * @return seznam instancí třídy {@code Vec}
     */
    private static List<Vec> nactiVeci(String cesta) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        InputStream inputStream = JsonLoader.class.getResourceAsStream(cesta);
        if (inputStream == null) {
            throw new FileNotFoundException("Soubor " + cesta + " nebyl nalezen v resources.");
        }

        VecDTO[] dataPole = mapper.readValue(inputStream, VecDTO[].class);

        List<Vec> veci = new ArrayList<>();
        for (VecDTO data : dataPole) {
            veci.add(VecFactory.vytvorVec(data));
        }

        return veci;
    }


    /**
     * Načítá věci ze souboru a vrací je jako mapu, kde klíčem je název věci
     *
     * @return mapa názvů a odpovídajících instancí třídy {@code Vec}
     */
    public static Map<String, Vec> nactiVeciDoMapy() throws IOException {
        List<Vec> veciList = nactiVeci("/cz/vse/adventura/json/veci.json");

        Map<String, Vec> veciMapa = new HashMap<>();

        for (Vec vec : veciList) {
            veciMapa.put(vec.getNazev(), vec);
        }
        return veciMapa;
    }

    /**
     * Načítá seznam postav ze souboru ve formátu JSON
     *
     * @return seznam instancí třídy {@code Postava}
     */
    public static List<Postava> nactiPostavy() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        InputStream inputStream = JsonLoader.class.getResourceAsStream("/cz/vse/adventura/json/postavy.json");

        return mapper.readValue(inputStream, new TypeReference<List<Postava>>() {});
    }

    /**
     * Načítá seznam DTO objektů představujících prostory z JSONu
     *
     * @return seznam instancí třídy {@code ProstorDTO}
     */
    public static List<ProstorDTO> nactiProstoryDTO() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        InputStream inputStream = JsonLoader.class.getResourceAsStream("/cz/vse/adventura/json/prostory.json");

        return mapper.readValue(inputStream, new TypeReference<List<ProstorDTO>>() {});
    }
}

