package com.app.EpicEnergyCRM.service;

import com.app.EpicEnergyCRM.exception.NotFoundException;
import com.app.EpicEnergyCRM.model.entities.Fattura;
import com.app.EpicEnergyCRM.repository.FattureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FatturaSvc {
    @Autowired
    private FattureRepo fattureRepo;

    public Fattura findFatturaById(int id) throws NotFoundException {
        return fattureRepo.findById(id).orElseThrow(() -> new NotFoundException("Fattura do not found"));
    }
}
