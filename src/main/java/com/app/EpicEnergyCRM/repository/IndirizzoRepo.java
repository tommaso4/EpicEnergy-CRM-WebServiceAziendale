package com.app.EpicEnergyCRM.repository;

import com.app.EpicEnergyCRM.model.entities.Indirizzo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IndirizzoRepo extends JpaRepository<Indirizzo,Integer>, PagingAndSortingRepository<Indirizzo,Integer> {


}
