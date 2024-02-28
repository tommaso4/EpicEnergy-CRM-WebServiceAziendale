package com.app.EpicEnergyCRM;

import com.app.EpicEnergyCRM.service.ComuneSvc;
import com.app.EpicEnergyCRM.service.ProvSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class EpicEnergyCrmApplication implements CommandLineRunner {

    @Autowired
    private ComuneSvc comSvc;

    @Autowired
    private ProvSvc provSvc;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(EpicEnergyCrmApplication.class, args);


    }
//    @Override
//    public void run(String... args) throws  Exception{
//      // popolaProvincia();
//        //popolaComuni();
//    }

    @Override
    public void run(String... args) throws Exception {

        String pathFileName = "Provincie-Comuni/comuni.txt";
        File inputFile = new File(pathFileName);
        Scanner scannerDaFile = null;
        try {
            scannerDaFile = new Scanner(inputFile);
            while (scannerDaFile.hasNextLine()) {
                String line = scannerDaFile.nextLine();
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    comSvc.createComune(parts[0], parts[1], parts[2], parts[3]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scannerDaFile != null) {
                scannerDaFile.close();
            }
        }
    }

    //
//    public  void popolaProvincia(){
//
//        String pathFileName = "Provincie-Comuni/provincie.txt";
//        File inputFile = new File(pathFileName);
//        Scanner scannerDaFile = null;
//        try {
//            scannerDaFile = new Scanner(inputFile);
//            while (scannerDaFile.hasNextLine()) {
//                String line = scannerDaFile.nextLine();
//                String[] parts = line.split(";");
//                if (parts.length == 3) {
//                    provSvc.createProv(parts[0],parts[1],parts[2]);
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            if (scannerDaFile != null) {
//                scannerDaFile.close();
//            }
//        }
//    }
//
//    public void popolaComuni() {
////
//        String pathFileName = "Provincie-Comuni/comuni.txt";
//        File inputFile = new File(pathFileName);
//        Scanner scannerDaFile = null;
//        try {
//            scannerDaFile = new Scanner(inputFile);
//            while (scannerDaFile.hasNextLine()) {
//                String line = scannerDaFile.nextLine();
//                String[] parts = line.split(";");
//                if (parts.length == 4) {
//                    comSvc.createComune(parts[0], parts[1], parts[2], parts[3]);
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            if (scannerDaFile != null) {
//                scannerDaFile.close();
//            }
//        }
//    }
}







