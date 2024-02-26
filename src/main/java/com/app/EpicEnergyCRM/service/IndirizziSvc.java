package com.app.EpicEnergyCRM.service;

import com.app.EpicEnergyCRM.exception.NotFoundException;
import com.app.EpicEnergyCRM.model.entities.Indirizzo;
import com.app.EpicEnergyCRM.repository.IndirizzoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndirizziSvc {
    @Autowired
    private IndirizzoRepo indirizzoRepo;

    public Indirizzo findById(int id) throws NotFoundException {
        return indirizzoRepo.findById(id).orElseThrow(() -> new NotFoundException("Adress not found!"));
    }
}
