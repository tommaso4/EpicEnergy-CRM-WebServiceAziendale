package com.app.EpicEnergyCRM.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Indirizzo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "indirizzo_seq")
    @SequenceGenerator(name = "indirizzo_seq", sequenceName = "indirizzo_sequence", allocationSize = 1)
    private int id;

    private String via;

    private int civico;

    private String localita;

    private int cap;

    @ManyToOne
    @JoinColumn(name = "comune_id")
    private Comune comune;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}


