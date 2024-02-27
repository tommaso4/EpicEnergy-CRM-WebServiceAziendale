package com.app.EpicEnergyCRM.model.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Provincia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequenza_provincia", initialValue = 1, allocationSize = 1)
    private int id;

    private String sigla;

    private String provincia;

    private String regione;

    @OneToMany(mappedBy = "provincia", fetch = FetchType.EAGER)
    private List<Comune> comuni;

    public Provincia(String sigla, String provincia, String regione) {
        this.sigla = sigla;
        this.provincia = provincia;
        this.regione = regione;
    }
}
