package com.app.EpicEnergyCRM.repository;

import com.app.EpicEnergyCRM.model.entities.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FattureRepo extends JpaRepository<Fattura,Integer>, PagingAndSortingRepository<Fattura,Integer> {
}
