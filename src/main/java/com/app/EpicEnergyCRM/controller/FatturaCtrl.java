package com.app.EpicEnergyCRM.controller;

import com.app.EpicEnergyCRM.enums.TipoFattura;
import com.app.EpicEnergyCRM.exception.CustomResponse;
import com.app.EpicEnergyCRM.model.entities.Cliente;
import com.app.EpicEnergyCRM.model.entities.Fattura;
import com.app.EpicEnergyCRM.model.request.ClienteReq;
import com.app.EpicEnergyCRM.model.request.FatturaReq;
import com.app.EpicEnergyCRM.service.ClienteSvc;
import com.app.EpicEnergyCRM.service.FatturaSvc;
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
public class FatturaCtrl {

    @Autowired
    private FatturaSvc fatturaSvc;

    @PostMapping("/fattura")
    public ResponseEntity<CustomResponse> createFattura(@RequestBody @Validated FatturaReq fatturaReq, BindingResult result) {
        if (result.hasErrors()) {
            String resultErr = result.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).toList().toString();
            return CustomResponse.error(resultErr, HttpStatus.BAD_REQUEST);
        }
        Fattura fattura = fatturaSvc.createFattura(fatturaReq);
        return CustomResponse.success(HttpStatus.CREATED.toString(), fattura, HttpStatus.CREATED);
    }

    @GetMapping("/fattura")
    public ResponseEntity<CustomResponse> getAll(Pageable pageable) {
        Page<Fattura> fatture = fatturaSvc.getAllFatture(pageable);
        return CustomResponse.success(HttpStatus.OK.toString(), fatture, HttpStatus.OK);
    }
    @GetMapping("/fattura/{id}")
    public ResponseEntity<CustomResponse> findById(@PathVariable int id) {
        Fattura fattura = fatturaSvc.findFatturaById(id);
        return CustomResponse.success(HttpStatus.OK.toString(), fattura, HttpStatus.OK);
    }

    @PutMapping("/fattura/{id}")
    public ResponseEntity<CustomResponse> upDateFattura(@PathVariable int id, @RequestBody @Validated FatturaReq fatturaReq, BindingResult result) {
        if (result.hasErrors()) {
            String resultErr = result.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).toList().toString();
            return CustomResponse.error(resultErr, HttpStatus.BAD_REQUEST);
        }
        Fattura fattura = fatturaSvc.updateFattura(id, fatturaReq);
        return CustomResponse.success(HttpStatus.OK.toString(), fattura, HttpStatus.OK);
    }

    @DeleteMapping("/fattura/{id}")
    public ResponseEntity<CustomResponse> deleteClient(@PathVariable int id) {
        fatturaSvc.deleteFattura(id);
        return CustomResponse.emptyResponse("Fattura with id: " + id + " deleted", HttpStatus.OK);
    }


//    @GetMapping("/fattura/byCliente")
//    public ResponseEntity<CustomResponse> getFattureByCliente(@RequestParam Long clienteId) {
//        Cliente cliente = // ottieni il cliente dal repository o da un altro modo
//                List<Fattura> fatture = fatturaSvc.getFattureByCliente(cliente);
//        return CustomResponse.success(HttpStatus.OK.toString(), fatture, HttpStatus.OK);
//    }

    @GetMapping("/fattura/byTipoFattura")
    public ResponseEntity<CustomResponse> getFattureByTipoFattura(@RequestParam TipoFattura tipoFattura) {
        List<Fattura> fatture = fatturaSvc.getFattureByTipoFattura(tipoFattura);
        return CustomResponse.success(HttpStatus.OK.toString(), fatture, HttpStatus.OK);
    }

    @GetMapping("/fattura/byData")
    public ResponseEntity<CustomResponse> getFattureByData(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        List<Fattura> fatture = fatturaSvc.getFattureByData(data);
        return CustomResponse.success(HttpStatus.OK.toString(), fatture, HttpStatus.OK);
    }

    @GetMapping("/fattura/byYear")
    public ResponseEntity<CustomResponse> findByYear(@RequestParam int anno) {
        List<Fattura> fatture = fatturaSvc.findByYear(anno);
        return CustomResponse.success(HttpStatus.OK.toString(), fatture, HttpStatus.OK);
    }

    @GetMapping("/fattura/byImportoRange")
    public ResponseEntity<CustomResponse> getFattureByImportoRange(
            @RequestParam double minImporto,
            @RequestParam double maxImporto) {
        List<Fattura> fatture = fatturaSvc.getFattureByImportoRange(minImporto, maxImporto);
        return CustomResponse.success(HttpStatus.OK.toString(), fatture, HttpStatus.OK);
    }

}
