package com.app.EpicEnergyCRM.controller;

import com.app.EpicEnergyCRM.exception.BadRequestException;
import com.app.EpicEnergyCRM.exception.CustomResponse;
import com.app.EpicEnergyCRM.exception.LoginFaultException;
import com.app.EpicEnergyCRM.model.entities.Utente;
import com.app.EpicEnergyCRM.model.request.LogInReq;
import com.app.EpicEnergyCRM.model.request.UtenteReq;
import com.app.EpicEnergyCRM.security.JwtTools;
import com.app.EpicEnergyCRM.service.UtenteSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthCtrl {

    @Autowired
    private UtenteSvc utenteService;
    @Autowired
    private JwtTools jwtTools;
    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity<CustomResponse> register(@RequestBody @Validated UtenteReq utenteRequest, BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            throw new BadRequestException(bindingResult.getAllErrors().toString());

        }
        Utente user = utenteService.saveUtente(utenteRequest);
        return CustomResponse.success(HttpStatus.CREATED.toString(),user,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<CustomResponse> login(@RequestBody @Validated LogInReq loginRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        Utente utente = utenteService.getUtenteByUsername(loginRequest.getUsername());
        if(encoder.matches(loginRequest.getPassword(), utente.getPassword())){
            String token = jwtTools.createToken(utente);
            return CustomResponse.emptyResponse(token, HttpStatus.ACCEPTED);
        }
        else{
            throw new LoginFaultException("Username/Password errate");
        }
    }

}

