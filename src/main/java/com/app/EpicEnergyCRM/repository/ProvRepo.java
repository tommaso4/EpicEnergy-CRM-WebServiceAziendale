package com.app.EpicEnergyCRM.repository;

import com.app.EpicEnergyCRM.model.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvRepo extends JpaRepository<Provincia,Integer> {
    public Provincia getByprovincia(String prov);
}
