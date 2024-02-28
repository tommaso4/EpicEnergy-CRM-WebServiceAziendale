package com.app.EpicEnergyCRM.service;

import com.app.EpicEnergyCRM.enums.TipoFattura;
import com.app.EpicEnergyCRM.exception.NotFoundException;
import com.app.EpicEnergyCRM.model.entities.Cliente;
import com.app.EpicEnergyCRM.model.entities.Fattura;
import com.app.EpicEnergyCRM.model.request.FatturaReq;
import com.app.EpicEnergyCRM.repository.FattureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;

@Service
public class FatturaSvc {
    @Autowired
    private FattureRepo fattureRepo;

    @Autowired
    private ClienteSvc clienteSvc;

    public Fattura createFattura(FatturaReq fatturaReq){
        Fattura fattura = new Fattura();
        Cliente cliente = clienteSvc.findClientById(fatturaReq.getClienteId());

        fattura.setData(fatturaReq.getData());
        fattura.setImporto(fatturaReq.getImporto());
        fattura.setTipoFattura(fatturaReq.getTipoFattura());
        fattura.setCliente(cliente);

        return fattureRepo.save(fattura);
    }

    public Page<Fattura> getAllFatture (Pageable pageable){return fattureRepo.findAll(pageable);}

    public Fattura findFatturaById(int id) throws NotFoundException {
        return fattureRepo.findById(id).orElseThrow(() -> new NotFoundException("Fattura do not found"));
    }

    public Fattura updateFattura (int id, FatturaReq fatturaReq) throws NotFoundException {
        Fattura fattura = findFatturaById(id);
        Cliente cliente = clienteSvc.findClientById(fatturaReq.getClienteId());

        fattura.setData(fatturaReq.getData());
        fattura.setImporto(fatturaReq.getImporto());
        fattura.setTipoFattura(fatturaReq.getTipoFattura());
        fattura.setCliente(cliente);
        return fattureRepo.save(fattura);
    }

    public void deleteFattura (int id) throws NotFoundException {
        Fattura fattura = findFatturaById(id);
        fattureRepo.delete(fattura);
    }


    public List<Fattura> getFattureByCliente(Cliente cliente) {
        return fattureRepo.findByCliente(cliente);
    }

    public List<Fattura> getFattureByTipoFattura(TipoFattura tipoFattura) {
        return fattureRepo.findByTipoFattura(tipoFattura);
    }

    public List<Fattura> getFattureByData(LocalDate data) {
        return fattureRepo.findByData(data);
    }


    public List<Fattura> findByYear(int anno) {
        return fattureRepo.findByYear(anno);
    }

    public List<Fattura> getFattureByImportoRange(double minImporto, double maxImporto) {
        return fattureRepo.findByImportoBetween(minImporto, maxImporto);
    }
}
