package com.app.EpicEnergyCRM.controller;

import com.app.EpicEnergyCRM.exception.BadRequestException;
import com.app.EpicEnergyCRM.exception.CustomResponse;
import com.app.EpicEnergyCRM.model.entities.Utente;
import com.app.EpicEnergyCRM.model.request.UtenteReq;
import com.app.EpicEnergyCRM.service.UtenteSvc;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/utente")
public class UtenteCtrl {

    @Autowired
    private UtenteSvc utenteSvc;
    @Autowired
    private Cloudinary cloudinary;

    @GetMapping("")
    public ResponseEntity<CustomResponse> getAll(Pageable pageable){

        try{

            return CustomResponse.success(HttpStatus.OK.toString(), utenteSvc.getAll(pageable), HttpStatus.OK);

        }catch(Exception e){

            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getUtenteById(@PathVariable int id){

        return CustomResponse.success(HttpStatus.OK.toString(), utenteSvc.getUtenteById(id), HttpStatus.OK);

    }

    @PostMapping("")
    public ResponseEntity<CustomResponse> saveUtente(@RequestBody @Validated UtenteReq utenteRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()) throw new BadRequestException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return CustomResponse.success(HttpStatus.OK.toString(), utenteSvc.saveUtente(utenteRequest), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> updateUtente(@PathVariable int id, @RequestBody @Validated UtenteReq utenteRequest, BindingResult bindingResult ){
        if(bindingResult.hasErrors()) throw new BadRequestException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return CustomResponse.success(HttpStatus.OK.toString(), utenteSvc.updateUtente(id, utenteRequest), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteUtente(@PathVariable int id){
        utenteSvc.deleteUtenteById(id);
        return CustomResponse.emptyResponse("L'utente con id = " + id + " è stato cancellato", HttpStatus.OK);
    }

    @PatchMapping("/{id}/upload")
    public ResponseEntity<CustomResponse> uploadAvatar(@PathVariable int id,@RequestParam("upload") MultipartFile file){
        try {
            Utente d = utenteSvc.uploadAvatar(id, (String)cloudinary.uploader().upload(file.getBytes(), new HashMap()).get("url"));
            return CustomResponse.success(HttpStatus.OK.toString(), d, HttpStatus.OK);
        }
        catch (IOException e){
            return CustomResponse.error(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PatchMapping("/{username}")
    public Utente changeRole(@PathVariable String username, @RequestBody String role){

        return utenteSvc.updateRoleUtente(username, role);

    }


}
