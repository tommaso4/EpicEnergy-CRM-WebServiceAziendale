package com.app.EpicEnergyCRM.repository;

import com.app.EpicEnergyCRM.model.entities.Comune;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComuneRepo extends JpaRepository<Comune,Integer> {
    public List<Comune> findBydenominazione(String denominazione);
}
