package com.app.EpicEnergyCRM.service;

import com.app.EpicEnergyCRM.model.entities.Provincia;
import com.app.EpicEnergyCRM.repository.ProvRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvSvc {
    @Autowired
    private ProvRepo provRepo;

    public void createProv (String sig,String prov, String reg){
        Provincia provincia = new Provincia(sig,prov,reg);
        provRepo.save(provincia);
    }

    public Provincia findByprovincia(String prov){
        return provRepo.getByprovincia(prov);
    }
}
