package com.app.EpicEnergyCRM.repository;

import com.app.EpicEnergyCRM.model.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProvinciaRepo extends JpaRepository<Provincia,Integer>, PagingAndSortingRepository<Provincia,Integer> {
}
