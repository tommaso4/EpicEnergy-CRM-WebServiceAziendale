package com.app.EpicEnergyCRM.Import;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CSVImporter {

    public Map<String, String> importComuni(String csvFile) {
        Map<String, String> comuniMap = new HashMap<>();
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(csvFile))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {
            String[] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                String codiceProvincia = nextLine[0];
                String progressivoComune = nextLine[1];
                String denominazione = nextLine[2];
                String provincia = nextLine[3];
                String key = codiceProvincia + progressivoComune;
                String provComune = denominazione + " " + provincia;
                comuniMap.put(key, provComune);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        System.out.println(comuniMap.size());
        return comuniMap;

    }

    public Map<String, String> importProvince(String csvFile) {
        Map<String, String> comuniMap = new HashMap<>();
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(csvFile))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {
            String[] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                String sigla = nextLine[0];
                String provincia = nextLine[1];
                String regione = nextLine[2];
                String key = sigla + " " + provincia;
                comuniMap.put(key, regione);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        System.out.println(comuniMap.size());
        return comuniMap;

    }

    public static Map<String, String> collegaComuniProvince(Map<String, String> comuniMap, Map<String, String> provinceMap){

        Map<String, String> comuniProvinceMap = new HashMap<>();

        for(Map.Entry<String, String> entry : comuniMap.entrySet()){

            String comune = entry.getKey();
            String provincia = entry.getValue();
            String regione = provinceMap.get(provincia);
            String provRegione = provincia + " " + regione;
            comuniProvinceMap.put(comune, provRegione);

        }
        System.out.println(comuniProvinceMap);
        return comuniProvinceMap;
    }

}
