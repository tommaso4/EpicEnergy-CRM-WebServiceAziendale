package com.app.EpicEnergyCRM.model.entities;

import com.app.EpicEnergyCRM.enums.TipoFattura;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Fattura {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fattura_seq")
    @SequenceGenerator(name = "fattura_seq", sequenceName = "fattura_sequence", allocationSize = 1)
    private int id;

    private LocalDate data;
    private double importo;
    private TipoFattura tipoFattura;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


}
