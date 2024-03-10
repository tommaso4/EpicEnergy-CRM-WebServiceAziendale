package com.app.EpicEnergyCRM.model.request;

import com.app.EpicEnergyCRM.enums.TipoCliente;
import com.app.EpicEnergyCRM.model.entities.Indirizzo;
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
    @NotNull(message = "dataUltimo contatto required")
    private LocalDate dataUltimoContatto;
    @NotNull(message = "fatturato annuale required")
    private Double fatturatoAnnuale;
    @NotBlank(message = "pec required")
    private String pec;
    @NotBlank(message = "telefono required")
    private String telefono;
    @NotBlank(message = "email contatto required")
    private String emailContatto;
    @NotBlank(message = "nome contatto required")
    private String nomeContatto;
    @NotBlank(message = "cognome contatto required")
    private String cognomeContatto;
    @NotBlank(message = "telefono contatto required")
    private String telefonoContatto;
    @NotNull(message = "tipo cliente required")
    private TipoCliente tipoCliente;
}
