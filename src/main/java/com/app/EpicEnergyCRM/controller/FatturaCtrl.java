package com.app.EpicEnergyCRM.controller;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

}
