package com.app.EpicEnergyCRM.model.request;

import com.app.EpicEnergyCRM.enums.TipoCliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClienteReq {

    @NotBlank(message = "ragioneSociale required")
    private String ragioneSociale;
    @NotNull(message = "partitaIva required")
    private Integer partitaIva;
    @NotBlank(message = "email required")
    private String email;
    @NotNull(message = "dataInserimento required")
    private LocalDate dataInserimento;
    @NotNull(message = "dataUltimoContatto required")
    private LocalDate dataUltimoContatto;
    @NotNull(message = "fatturatoAnnuale required")
    private Double fatturatoAnnuale;
    @NotBlank(message = "pec required")
    private String pec;
    @NotBlank(message = "telefono required")
    private String telefono;
    @NotBlank(message = "emailContatto required")
    private String emailContatto;
    @NotBlank(message = "nomeContatto required")
    private String nomeContatto;
    @NotBlank(message = "cognomeContatto required")
    private String cognomeContatto;
    @NotBlank(message = "telefonoContatto required")
    private String telefonoContatto;
    @NotNull(message = "tipoCliente required")
    private TipoCliente tipoCliente;
}
