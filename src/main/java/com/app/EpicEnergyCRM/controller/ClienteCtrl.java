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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/cliente")
    public ResponseEntity<CustomResponse> getAllByRagioneSoc(Pageable pageable) {
        Page<Cliente> clienti = clienteSvc.getAllByRagioneSociale(pageable);
        return CustomResponse.success(HttpStatus.OK.toString(), clienti, HttpStatus.OK);
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

    @PatchMapping("cliente/addFattura/{idFattura}/{idCliente}")
    public ResponseEntity<CustomResponse> addFattura (@PathVariable int idFattura,@PathVariable int idCliente ){
        Fattura fattura = clienteSvc.addFatturaInClient(idFattura,idCliente);
        return CustomResponse.success(HttpStatus.OK.toString(),fattura,HttpStatus.OK);
    }
    @PatchMapping("cliente/addIndirizzo/{idIndirizzo}/{idCliente}")
    public ResponseEntity<CustomResponse> addIndirizzo (@PathVariable int idIndirizzo,@PathVariable int idCliente ){
        Indirizzo indirizzo = clienteSvc.addIndirizzoInClient(idIndirizzo, idCliente);
        return CustomResponse.success(HttpStatus.OK.toString(),indirizzo,HttpStatus.OK);
    }
}
