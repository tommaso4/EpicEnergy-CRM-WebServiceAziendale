package com.app.EpicEnergyCRM.model.request;

import com.app.EpicEnergyCRM.enums.TipoFattura;
import com.app.EpicEnergyCRM.model.entities.Cliente;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FatturaReq {

    @NotNull(message = "data required")
    private LocalDate data;
    @NotNull(message = "importo required")
    private Double importo;
    @NotNull(message = "tipo fattura required")
    private TipoFattura tipoFattura;
    @NotNull(message = "cliente required")
    private Integer clienteId;

}
