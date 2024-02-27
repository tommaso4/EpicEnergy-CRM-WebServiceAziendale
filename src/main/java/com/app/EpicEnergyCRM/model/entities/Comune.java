package com.app.EpicEnergyCRM.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class Comune {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequenza_comune", initialValue = 1, allocationSize = 1)
    private int id;

    private String codiceProvincia;
    private  String progressivoComune;

    private String denominazione;

    @ManyToOne
    @JoinColumn(name="comune_id")
    private  Provincia provincia;

    @OneToMany(mappedBy = "comune", fetch = FetchType.EAGER)
    List<Indirizzo> indirizzi;

    public Comune(String codiceProvincia, String progressivoComune, String denominazione, Provincia provincia) {
        this.codiceProvincia = codiceProvincia;
        this.progressivoComune = progressivoComune;
        this.denominazione = denominazione;
        this.provincia = provincia;
    }

    public Comune() {
    }
}
