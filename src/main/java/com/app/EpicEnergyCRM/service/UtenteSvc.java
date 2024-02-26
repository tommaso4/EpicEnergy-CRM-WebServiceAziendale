package com.app.EpicEnergyCRM.service;

import com.app.EpicEnergyCRM.enums.Ruolo;
import com.app.EpicEnergyCRM.exception.NotFoundException;
import com.app.EpicEnergyCRM.model.entities.Utente;
import com.app.EpicEnergyCRM.model.request.UtenteReq;
import com.app.EpicEnergyCRM.repository.UtenteRepo;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtenteSvc {

    @Autowired
    private UtenteRepo utenteRepo;
    @Autowired
    private PasswordEncoder encoder;

//    @Autowired
//    private JavaMailSenderImpl javaMailSender;

    public Page<Utente> getAll(Pageable pageable){

        return utenteRepo.findAll(pageable);

    }

    public Utente getUtenteById(int id) throws NotFoundException {
        return utenteRepo.findById(id).orElseThrow(()->new NotFoundException("Utente con id = " + id + " non trovato"));
    }

    public Utente getUtenteByUsername(String username) throws NotFoundException{

        return utenteRepo.findByUsername(username).orElseThrow(()->new NotFoundException("Username non trovato"));

    }

    public Utente saveUtente(UtenteReq utenteRequest){
        Utente u = new Utente();
        u.setNome(utenteRequest.getNome());
        u.setCognome(utenteRequest.getCognome());
        u.setUsername(utenteRequest.getUsername());
        u.setPassword(encoder.encode(utenteRequest.getPassword()));
        u.setEmail(utenteRequest.getEmail());
//        sendEmail(utenteRequest.getEmail());
        u.setRuolo(Ruolo.UTENTE);

        return utenteRepo.save(u);
    }

    public Utente updateUtente(int id, UtenteReq utenteRequest) throws NotFoundException {
        Utente u = getUtenteById(id);
        u.setNome(utenteRequest.getNome());
        u.setCognome(utenteRequest.getCognome());
        u.setUsername(utenteRequest.getUsername());
        u.setEmail(utenteRequest.getEmail());
        u.setPassword(encoder.encode(utenteRequest.getPassword()));


        return utenteRepo.save(u);
    }

    public void deleteUtenteById(int id) throws NotFoundException {
        Utente u = getUtenteById(id);
        utenteRepo.delete(u);
    }

    public void deleteUtenteByUsername(String username) throws NotFoundException {
        utenteRepo.deleteByUsername(username);
    }

    public Utente updateRoleUtente(String username, String ruolo) throws NotFoundException {
        Utente u = getUtenteByUsername(username);
        u.setRuolo(Ruolo.valueOf(ruolo));
        return utenteRepo.save(u);

    }

//    public void sendEmail(String email){
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(email);
//        message.setSubject("Registrazione al servizio EpicEnergy");
//        message.setText("Benvenuto sulla nostra applicazione, registrazioen avvenuta con successo.");
//        javaMailSender.send(message);
//    }

    public Utente uploadAvatar(int id, String url) throws NotFoundException {
        Utente u = getUtenteById(id);
        u.setAvatar(url);

        return utenteRepo.save(u);

    }


}
