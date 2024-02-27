package com.app.EpicEnergyCRM.model.request;

import com.app.EpicEnergyCRM.model.entities.Cliente;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IndirizzoReq {

    @NotBlank(message = "via required")
    private String via;
    @NotNull(message = "civico required")
    private Integer civico;
    @NotBlank(message = "località required")
    private String localita;
    @NotNull(message = "cap required")
    private Integer cap;
    @NotBlank(message = "comune required")
    private String comune;
    @NotNull(message = "cliente required")
    private Integer clienteId;
}
