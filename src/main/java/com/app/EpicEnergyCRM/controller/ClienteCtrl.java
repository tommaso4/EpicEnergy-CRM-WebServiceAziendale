package com.app.EpicEnergyCRM.controller;

import com.app.EpicEnergyCRM.exception.CustomResponse;
import com.app.EpicEnergyCRM.model.entities.Cliente;
import com.app.EpicEnergyCRM.model.entities.Fattura;
import com.app.EpicEnergyCRM.model.entities.Indirizzo;
import com.app.EpicEnergyCRM.model.request.ClienteReq;
import com.app.EpicEnergyCRM.service.ClienteSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ClienteCtrl {
    @Autowired
    private ClienteSvc clienteSvc;

    @PostMapping("/cliente")
    public ResponseEntity<CustomResponse> createClient(@RequestBody @Validated ClienteReq clienteReq, BindingResult result) {
        if (result.hasErrors()) {
            String resultErr = result.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).toList().toString();
            return CustomResponse.error(resultErr, HttpStatus.BAD_REQUEST);
        }
        Cliente cliente = clienteSvc.createClient(clienteReq);
        return CustomResponse.success(HttpStatus.CREATED.toString(), cliente, HttpStatus.CREATED);
    }

    @GetMapping("/cliente")
    public ResponseEntity<CustomResponse> getAll(Pageable pageable) {
        Page<Cliente> clienti = clienteSvc.getAllClient(pageable);
        return CustomResponse.success(HttpStatus.OK.toString(), clienti, HttpStatus.OK);
    }


    @GetMapping("/cliente/ragioneSoc")
    public ResponseEntity<CustomResponse> getAllByRagioneSoc(Pageable pageable) {
        Page<Cliente> clienti = clienteSvc.getAllByRagioneSociale(pageable);
        return CustomResponse.success(HttpStatus.OK.toString(), clienti, HttpStatus.OK);
    }

    @GetMapping("/cliente/provincia")
    public ResponseEntity<CustomResponse> sortByProvincia(){
        List<Cliente> clienti = clienteSvc.sortByProvincia();
        return CustomResponse.success(HttpStatus.OK.toString(),clienti,HttpStatus.OK);
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<CustomResponse> findById(@PathVariable int id) {
        Cliente cliente = clienteSvc.findClientById(id);
        return CustomResponse.success(HttpStatus.OK.toString(), cliente, HttpStatus.OK);
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<CustomResponse> upDateClient(@PathVariable int id, @RequestBody @Validated ClienteReq clienteReq, BindingResult result) {
        if (result.hasErrors()) {
            String resultErr = result.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).toList().toString();
            return CustomResponse.error(resultErr, HttpStatus.BAD_REQUEST);
        }
        Cliente cliente = clienteSvc.upDateClient(id, clienteReq);
        return CustomResponse.success(HttpStatus.OK.toString(), cliente, HttpStatus.OK);
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<CustomResponse> deleteClient(@PathVariable int id) {
        clienteSvc.deleteClient(id);
        return CustomResponse.emptyResponse("Cliente with id: " + id + "deleted", HttpStatus.OK);
    }

    @GetMapping("/cliente/sortedByNome")
    public Page<Cliente> getClientiSortedByNome(Pageable pageable) {
        return clienteSvc.getClientiSortedByNome(pageable);
    }

    @GetMapping("/cliente/sortedByFatturatoAnnuale")
    public Page<Cliente> getClientiSortedByFatturatoAnnuale(Pageable pageable) {
        return clienteSvc.getClientiSortedByFatturatoAnnuale(pageable);
    }

    @GetMapping("/cliente/sortedByDataInserimento")
    public Page<Cliente> getClientiSortedByDataInserimento(Pageable pageable) {
        return clienteSvc.getClientiSortedByDataInserimento(pageable);
    }

    @GetMapping("/cliente/sortedByDataUltimoContatto")
    public Page<Cliente> getClientiSortedByDataUltimoContatto(Pageable pageable) {
        return clienteSvc.getClientiSortedByDataUltimoContatto(pageable);
    }

//    @GetMapping("/cliente/sortedByProvinciaSedeLegale")
//    public Page<Cliente> getClientiSortedByProvinciaSedeLegale(Pageable pageable) {
//        return clienteSvc.getClientiSortedByProvinciaSedeLegale(pageable);
//    }

    @GetMapping("/cliente/filterByFatturatoAnnuale")
    public ResponseEntity<CustomResponse> findByFatturatoAnnualeGreaterThanEqual(@RequestParam double fatturatoMinimo){
        List<Cliente> clienti = clienteSvc.findByFatturatoAnnualeGreaterThanEqual(fatturatoMinimo);
        return CustomResponse.success(HttpStatus.OK.toString(), clienti, HttpStatus.OK);
    }
    @GetMapping("/cliente/filterByDataInserimento")
    public ResponseEntity<CustomResponse> findByDataInserimentoBetween(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
        List<Cliente> clienti = clienteSvc.findByDataInserimentoBetween(startDate, endDate);
        return CustomResponse.success(HttpStatus.OK.toString(), clienti, HttpStatus.OK);
    }
    @GetMapping("/cliente/findByDataUltimoContattoBetween")
    public ResponseEntity<CustomResponse> findByDataUltimoContattoBetween(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
        List<Cliente> clienti = clienteSvc.findByDataUltimoContattoBetween(startDate, endDate);
        return CustomResponse.success(HttpStatus.OK.toString(), clienti, HttpStatus.OK);
    }

    @GetMapping("/cliente/findByNomeContaining")
    public ResponseEntity<CustomResponse> findByNomeContainingIgnoreCase(@RequestParam String parteDelNome){
        List<Cliente> clienti = clienteSvc.findByNomeContainingIgnoreCase(parteDelNome);
        return CustomResponse.success(HttpStatus.OK.toString(), clienti, HttpStatus.OK);
    }
}
