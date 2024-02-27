package com.app.EpicEnergyCRM.controller;

import com.app.EpicEnergyCRM.exception.CustomResponse;
import com.app.EpicEnergyCRM.model.entities.Fattura;
import com.app.EpicEnergyCRM.model.entities.Indirizzo;
import com.app.EpicEnergyCRM.model.request.FatturaReq;
import com.app.EpicEnergyCRM.model.request.IndirizzoReq;
import com.app.EpicEnergyCRM.service.IndirizzoSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class IndirizzoCtrl {

    @Autowired
    private IndirizzoSvc indirizzoSvc;

    @PostMapping("/indirizzo")
    public ResponseEntity<CustomResponse> createIndirizzo(@RequestBody @Validated IndirizzoReq indirizzoReq, BindingResult result) {
        if (result.hasErrors()) {
            String resultErr = result.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).toList().toString();
            return CustomResponse.error(resultErr, HttpStatus.BAD_REQUEST);
        }
        Indirizzo indirizzo = indirizzoSvc.createIndirizzo(indirizzoReq);
        return CustomResponse.success(HttpStatus.CREATED.toString(), indirizzo, HttpStatus.CREATED);
    }
    @GetMapping("/indirizzo")
    public ResponseEntity<CustomResponse> getAll(Pageable pageable) {
        Page<Indirizzo> indirizzi = indirizzoSvc.getAllIndirizzi(pageable);
        return CustomResponse.success(HttpStatus.OK.toString(), indirizzi, HttpStatus.OK);
    }

    @GetMapping("/indirizzo/{id}")
    public ResponseEntity<CustomResponse> findById(@PathVariable int id) {
        Indirizzo indirizzo = indirizzoSvc.findIndirizzoById(id);
        return CustomResponse.success(HttpStatus.OK.toString(), indirizzo, HttpStatus.OK);
    }

    @PutMapping("/indirizzo/{id}")
    public ResponseEntity<CustomResponse> upDateIndirizzo(@PathVariable int id, @RequestBody @Validated IndirizzoReq indirizzoReq, BindingResult result) {
        if (result.hasErrors()) {
            String resultErr = result.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).toList().toString();
            return CustomResponse.error(resultErr, HttpStatus.BAD_REQUEST);
        }
        Indirizzo indirizzo = indirizzoSvc.updateIndirizzo(id, indirizzoReq);
        return CustomResponse.success(HttpStatus.OK.toString(), indirizzo, HttpStatus.OK);
    }

    @DeleteMapping("/indirizzo/{id}")
    public ResponseEntity<CustomResponse> deleteIndirizzo(@PathVariable int id) {
        indirizzoSvc.deleteIndirizzo(id);
        return CustomResponse.emptyResponse("Indirizzo with id: " + id + " deleted", HttpStatus.OK);
    }
}
