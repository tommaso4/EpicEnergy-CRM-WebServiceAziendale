package com.app.EpicEnergyCRM.model.entities;

import com.app.EpicEnergyCRM.enums.TipoIndirizzo;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Enumerated(EnumType.STRING)
    private TipoIndirizzo tipoIndirizzo;

    @ManyToOne
    @JoinColumn(name = "comune_id")
    private Comune comune;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private Cliente cliente;
}


