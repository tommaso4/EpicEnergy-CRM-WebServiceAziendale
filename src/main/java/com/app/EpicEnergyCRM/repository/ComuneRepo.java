package com.app.EpicEnergyCRM.repository;

import com.app.EpicEnergyCRM.model.entities.Comune;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComuneRepo extends JpaRepository<Comune,Integer> {

    public Optional<Comune> findByDenominazione(String denominazione);
}
