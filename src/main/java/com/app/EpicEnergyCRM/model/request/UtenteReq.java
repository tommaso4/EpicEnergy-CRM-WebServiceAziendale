package com.app.EpicEnergyCRM.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UtenteReq {

    @NotBlank(message = "Campo obbligatorio")
    public String nome;
    @NotBlank(message = "Campo obbligatorio")
    public String cognome;
    @NotBlank(message = "Campo obbligatorio")
    private String username;
    @NotBlank(message = "Campo obbligatorio")
    private String password;
    @NotBlank(message = "Campo obbligatorio")
    private String email;
    @NotBlank(message = "Campo obbligatorio")
    private String avatar;

}
