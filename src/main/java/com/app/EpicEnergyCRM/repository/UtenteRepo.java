package com.app.EpicEnergyCRM.repository;

import com.app.EpicEnergyCRM.model.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UtenteRepo extends JpaRepository<Utente, Integer>, PagingAndSortingRepository<Utente, Integer> {

    public Optional<Utente> findByUsername(String username);
    public Optional<Utente> deleteByUsername(String username);

}
