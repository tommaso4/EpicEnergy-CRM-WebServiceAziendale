package com.app.EpicEnergyCRM.Import;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVImporter {

    public Map<String, String> importComuni(String csvFile) {
        Map<String, String> comuniMap = new HashMap<>();
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(csvFile))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                String codiceProvincia = nextLine[0];
                String progressivoComune = nextLine[1];
                String denominazione = nextLine[2];
                String key = codiceProvincia + progressivoComune;
                comuniMap.put(key, denominazione);
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
            while ((nextLine = reader.readNext()) != null) {
                String codiceProvincia = nextLine[0];
                String progressivoComune = nextLine[1];
                String denominazione = nextLine[2];
                String key = codiceProvincia + progressivoComune;
                comuniMap.put(key, denominazione);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        System.out.println(comuniMap.size());
        return comuniMap;

    }

}
