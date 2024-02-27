package com.app.EpicEnergyCRM;

import com.app.EpicEnergyCRM.Import.CSVImporter;
import com.app.EpicEnergyCRM.model.entities.Provincia;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class EpicEnergyCrmApplication {

	public static void main(String[] args) throws IOException {
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
		try{
			List<Provincia> a1 =readFile();

		}catch (IOException e){
			System.out.println(e.getMessage());;
		}
	}
	public static List<Provincia> readFile() throws IOException{
		File file = new File("Province-Comuni/provincie.txt");
		String stringFile = FileUtils.readFileToString(file, Charset.defaultCharset());
		String[] arrString = stringFile.split("# ");
		ArrayList<Provincia> elenco = Arrays.stream(arrString)
				.map(s->{
					String[] stringDetails = s.split(";");
					System.out.println(Arrays.toString(stringDetails));
					Provincia p = new Provincia(
							(stringDetails[0]),
							(stringDetails[1]),
							stringDetails[2]
					);
					System.out.println(p);
					return p;
				}).collect(Collectors.toCollection(ArrayList::new));

		return elenco;

	}
}
