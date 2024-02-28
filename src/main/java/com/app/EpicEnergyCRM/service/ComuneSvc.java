package com.app.EpicEnergyCRM.service;

import com.app.EpicEnergyCRM.exception.NotFoundException;
import com.app.EpicEnergyCRM.model.entities.Comune;
import com.app.EpicEnergyCRM.repository.ComuneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComuneSvc {
    @Autowired
    private ComuneRepo comuneRepo;
    @Autowired
    private ProvSvc provSvc;

    public void createComune(String cod,String prog, String dem,String prov){
        Comune comune = new Comune(cod,prog,dem,provSvc.findByprovincia(prov));
        comuneRepo.save(comune);
    }

   }



