package com.app.EpicEnergyCRM.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LogInReq {

    @NotBlank(message = "Username obbligatorio")
    private String username;
    @NotBlank(message = "Password obbligatoria")
    private String password;

}
