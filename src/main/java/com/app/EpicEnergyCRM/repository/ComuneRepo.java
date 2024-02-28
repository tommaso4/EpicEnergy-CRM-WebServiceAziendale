package com.app.EpicEnergyCRM.repository;

import com.app.EpicEnergyCRM.model.entities.Comune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComuneRepo extends JpaRepository<Comune,Integer> {
}
