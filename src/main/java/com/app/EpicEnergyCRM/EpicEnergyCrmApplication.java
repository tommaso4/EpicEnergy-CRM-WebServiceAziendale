package com.app.EpicEnergyCRM;

import com.app.EpicEnergyCRM.Import.CSVImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class EpicEnergyCrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpicEnergyCrmApplication.class, args);

//		String comuniCsvFile = "/Users/emanuelebarone/Desktop/comuni__province/comuni-italiani.csv";
//		String provinceCsvFile = "/Users/emanuelebarone/Desktop/comuni__province/province-italiane.csv";
//
//		CSVImporter importer = new CSVImporter();
//
//		Map<String, String> comuniMap = importer.importComuni(comuniCsvFile);
//		Map<String, String> provinceMap = importer.importProvince(provinceCsvFile);
//
//		System.out.println("Comuni Map:");
//		for (Map.Entry<String, String> entry : comuniMap.entrySet()) {
//			System.out.println(entry.getKey() + ": " + entry.getValue());
//		}
//
//		System.out.println("\nProvince Map:");
//		for (Map.Entry<String, String> entry : provinceMap.entrySet()) {
//			System.out.println(entry.getKey() + ": " + entry.getValue());
//		}


	}
}
