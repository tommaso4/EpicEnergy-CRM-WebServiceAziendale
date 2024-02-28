package com.app.EpicEnergyCRM.model.request;

import com.app.EpicEnergyCRM.enums.TipoIndirizzo;
import com.app.EpicEnergyCRM.model.entities.Cliente;
import com.app.EpicEnergyCRM.model.entities.Comune;
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
    @NotNull(message = "idComune required")
    private Integer idComune;
    @NotNull(message = "cliente required")
    private Integer clienteId;
    @NotNull(message = "tipoIndirizzo required")
    private TipoIndirizzo tipoIndirizzo;
}
