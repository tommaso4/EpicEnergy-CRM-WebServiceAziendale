package com.app.EpicEnergyCRM.service;

import com.app.EpicEnergyCRM.enums.TipoIndirizzo;
import com.app.EpicEnergyCRM.exception.NotFoundException;
import com.app.EpicEnergyCRM.model.entities.Cliente;
import com.app.EpicEnergyCRM.model.entities.Comune;
import com.app.EpicEnergyCRM.model.entities.Indirizzo;
import com.app.EpicEnergyCRM.model.request.IndirizzoReq;
import com.app.EpicEnergyCRM.repository.IndirizzoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndirizzoSvc {
    @Autowired
    private IndirizzoRepo indirizzoRepo;
    @Autowired
    private ClienteSvc clienteSvc;
    @Autowired
    private ComuneSvc comuneSvc;

    public Indirizzo createIndirizzo(IndirizzoReq indirizzoReq){
        Indirizzo indirizzo = new Indirizzo();
        Cliente cliente = clienteSvc.findClientById(indirizzoReq.getClienteId());
        Comune comune = comuneSvc.findById(indirizzoReq.getIdComune());
        checkSedeLegale(cliente,indirizzoReq);

        indirizzo.setVia(indirizzoReq.getVia());
        indirizzo.setCivico(indirizzoReq.getCivico());
        indirizzo.setLocalita(indirizzoReq.getLocalita());
        indirizzo.setCap(indirizzoReq.getCap());
        indirizzo.setComune(comune);
        indirizzo.setTipoIndirizzo(indirizzoReq.getTipoIndirizzo());
        indirizzo.setCliente(cliente);
        return indirizzoRepo.save(indirizzo);
    }

    public void checkSedeLegale(Cliente cliente,IndirizzoReq indirizzoReq){
        List<Indirizzo> indirizzi = cliente.getIndirizziAzienda();
        for (Indirizzo indirizzo : indirizzi){
            if (indirizzo.getTipoIndirizzo().equals(indirizzoReq.getTipoIndirizzo()) ){
               throw new RuntimeException("Client has yet this type of Sede");
            }
        }
    }



    public Page<Indirizzo> getAllIndirizzi (Pageable pageable){return indirizzoRepo.findAll(pageable);}
    public Indirizzo findIndirizzoById(int id) throws NotFoundException {
        return indirizzoRepo.findById(id).orElseThrow(() -> new NotFoundException("Adress not found!"));
    }

    public Indirizzo updateIndirizzo (int id, IndirizzoReq indirizzoReq) throws NotFoundException {
        Indirizzo indirizzo = findIndirizzoById(id);
        Cliente cliente = clienteSvc.findClientById(indirizzoReq.getClienteId());
        Comune comune = comuneSvc.findById(indirizzoReq.getIdComune());

        indirizzo.setVia(indirizzoReq.getVia());
        indirizzo.setCivico(indirizzoReq.getCivico());
        indirizzo.setLocalita(indirizzoReq.getLocalita());
        indirizzo.setCap(indirizzoReq.getCap());
        indirizzo.setComune(comune);
        indirizzo.setTipoIndirizzo(indirizzoReq.getTipoIndirizzo());
        indirizzo.setCliente(cliente);

        return indirizzoRepo.save(indirizzo);
    }

    public void deleteIndirizzo (int id) throws NotFoundException {
        Indirizzo indirizzo = findIndirizzoById(id);
        indirizzoRepo.delete(indirizzo);
    }

}
