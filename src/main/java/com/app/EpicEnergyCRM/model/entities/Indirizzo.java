package com.app.EpicEnergyCRM.model.entities;

import jakarta.persistence.*;
import lombok.Data;

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
    private String comune;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
