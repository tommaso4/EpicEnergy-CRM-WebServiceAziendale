package com.app.EpicEnergyCRM.model.entities;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Indirizzo {

    private String via;
    private String civico;
}
