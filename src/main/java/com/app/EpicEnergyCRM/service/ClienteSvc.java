package com.app.EpicEnergyCRM.service;

import com.app.EpicEnergyCRM.exception.NotFoundException;
import com.app.EpicEnergyCRM.model.entities.Cliente;
import com.app.EpicEnergyCRM.model.entities.Fattura;
import com.app.EpicEnergyCRM.model.entities.Indirizzo;
import com.app.EpicEnergyCRM.model.request.ClienteReq;
import com.app.EpicEnergyCRM.repository.ClienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClienteSvc {
    @Autowired
    private ClienteRepo clienteRepo;
    public Cliente createClient (ClienteReq clienteReq){
        Cliente cliente = new Cliente();

        cliente.setRagioneSociale(clienteReq.getRagioneSociale());
        cliente.setPartitaIva(clienteReq.getPartitaIva());
        cliente.setEmail(clienteReq.getEmail());
        cliente.setDataInserimento(clienteReq.getDataInserimento());
        cliente.setDataUltimoContatto(clienteReq.getDataUltimoContatto());
        cliente.setFatturatoAnnuale(clienteReq.getFatturatoAnnuale());
        cliente.setPec(clienteReq.getPec());
        cliente.setTelefono(clienteReq.getTelefono());
        cliente.setEmailContatto(clienteReq.getEmailContatto());
        cliente.setNomeContatto(clienteReq.getNomeContatto());
        cliente.setCognomeContatto(clienteReq.getCognomeContatto());
        cliente.setTelefonoContatto(clienteReq.getTelefonoContatto());
        cliente.setTipoCliente(clienteReq.getTipoCliente());

        return clienteRepo.save(cliente);
    }

    public Page<Cliente> getAllClient (Pageable pageable){return clienteRepo.findAll(pageable);}
    public Page<Cliente> getAllByRagioneSociale(Pageable pageable){
        return clienteRepo.findAllByOrderByRagioneSociale(pageable);
    }


    public Cliente findClientById (int id) throws NotFoundException {
        return clienteRepo.findById(id).orElseThrow(() -> new NotFoundException("Client not found!"));
    }

    public Cliente upDateClient (int id, ClienteReq clienteReq) throws NotFoundException {
        Cliente cliente = findClientById(id);
        cliente.setRagioneSociale(clienteReq.getRagioneSociale());
        cliente.setPartitaIva(clienteReq.getPartitaIva());
        cliente.setEmail(clienteReq.getEmail());
        cliente.setDataInserimento(clienteReq.getDataInserimento());
        cliente.setDataUltimoContatto(clienteReq.getDataUltimoContatto());
        cliente.setFatturatoAnnuale(clienteReq.getFatturatoAnnuale());
        cliente.setPec(clienteReq.getPec());
        cliente.setTelefono(clienteReq.getTelefono());
        cliente.setNomeContatto(clienteReq.getNomeContatto());
        cliente.setCognomeContatto(cliente.getCognomeContatto());
        cliente.setTelefonoContatto(clienteReq.getTelefonoContatto());
        cliente.setTipoCliente(clienteReq.getTipoCliente());

        return clienteRepo.save(cliente);
    }

    public void deleteClient (int id) throws NotFoundException {
        Cliente cliente = findClientById(id);
        clienteRepo.delete(cliente);
    }

    public Cliente uploadLogoAziendale(int id, String url) throws NotFoundException {
        Cliente c = findClientById(id);
        c.setLogoAziendale(url);

        return clienteRepo.save(c);
    }

    public Page<Cliente> getClientiSortedByNome(Pageable pageable) {
        return clienteRepo.findAllByOrderByRagioneSociale(pageable);
    }

    public Page<Cliente> getClientiSortedByFatturatoAnnuale(Pageable pageable) {
        return clienteRepo.findAllByOrderByFatturatoAnnualeDesc(pageable);
    }

    public Page<Cliente> getClientiSortedByDataInserimento(Pageable pageable) {
        return clienteRepo.findAllByOrderByDataInserimentoDesc(pageable);
    }

    public Page<Cliente> getClientiSortedByDataUltimoContatto(Pageable pageable) {
        return clienteRepo.findAllByOrderByDataUltimoContattoDesc(pageable);
    }

    public Page<Cliente> findAllByOrderByProvinciaSedeLegale(Pageable pageable){
        return clienteRepo.findAllByOrderByProvinciaSedeLegale(pageable);
    }

//    public Page<Cliente> getClientiSortedByProvinciaSedeLegale(Pageable pageable) {
//        return clienteRepo.findAllByOrderByIndirizziProvinciaSedeLegale(pageable);
//    }

    public List<Cliente> findByFatturatoAnnualeGreaterThanEqual(double fatturatoMinimo){
        return clienteRepo.findByFatturatoAnnualeGreaterThanEqual(fatturatoMinimo);
    }

    public List<Cliente> findByDataInserimentoBetween(LocalDate startDate, LocalDate endDate){
        return clienteRepo.findByDataInserimentoBetween(startDate, endDate);
    }

    public List<Cliente> findByDataUltimoContattoBetween(LocalDate startDate, LocalDate endDate){
        return clienteRepo.findByDataUltimoContattoBetween(startDate, endDate);
    }

    public List<Cliente> findByNomeContainingIgnoreCase(String parteDelNome){
        return clienteRepo.findByRagioneSocialeContainingIgnoreCase(parteDelNome);
    }
}
