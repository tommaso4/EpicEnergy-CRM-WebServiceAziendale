package com.app.EpicEnergyCRM.service;

import com.app.EpicEnergyCRM.model.entities.Cliente;
import com.app.EpicEnergyCRM.model.entities.Fattura;
import com.app.EpicEnergyCRM.model.entities.Indirizzo;
import com.app.EpicEnergyCRM.model.request.FatturaReq;
import com.app.EpicEnergyCRM.model.request.IndirizzoReq;
import com.app.EpicEnergyCRM.repository.FattureRepo;
import com.app.EpicEnergyCRM.repository.IndirizzoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndirizzoSvc {

    @Autowired
    private IndirizzoRepo indirizzoRepo;

    @Autowired
    private ClienteSvc clienteSvc;

    public Indirizzo createIndirizzo(IndirizzoReq indirizzoReq){
        Indirizzo indirizzo = new Indirizzo();
        Cliente cliente = clienteSvc.findClientById(indirizzoReq.getClienteId());

        indirizzo.setVia(indirizzoReq.getVia());
        indirizzo.setCivico(indirizzoReq.getCivico());
        indirizzo.setLocalita(indirizzoReq.getLocalita());
        indirizzo.setCap(indirizzoReq.getCap());
        indirizzo.setComune(indirizzoReq.getComune());
        indirizzo.setCliente(cliente);

        return indirizzoRepo.save(indirizzo);
    }


}
