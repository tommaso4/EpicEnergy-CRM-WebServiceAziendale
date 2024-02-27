package com.app.EpicEnergyCRM.controller;

import com.app.EpicEnergyCRM.exception.CustomResponse;
import com.app.EpicEnergyCRM.model.entities.Cliente;
import com.app.EpicEnergyCRM.model.entities.Utente;
import com.app.EpicEnergyCRM.model.request.ClienteReq;
import com.app.EpicEnergyCRM.service.ClienteSvc;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
public class ClienteCtrl {
    @Autowired
    private ClienteSvc clienteSvc;
    @Autowired
    private Cloudinary cloudinary;

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
    public ResponseEntity<CustomResponse> getAllByRagioneSoc() {
        List<Cliente> clienti = clienteSvc.getAllByRagioneSociale();
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

    @PatchMapping("/cliente/{id}/upload")
    public ResponseEntity<CustomResponse> uploadAvatar(@PathVariable int id,@RequestParam("upload") MultipartFile file){
        try {
            Cliente c = clienteSvc.uploadLogoAziendale(id, (String)cloudinary.uploader().upload(file.getBytes(), new HashMap()).get("url"));
            return CustomResponse.success(HttpStatus.OK.toString(), c, HttpStatus.OK);
        }
        catch (IOException e){
            return CustomResponse.error(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
