package com.app.EpicEnergyCRM.repository;

import com.app.EpicEnergyCRM.model.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClienteRepo extends JpaRepository<Cliente,Integer>, PagingAndSortingRepository<Cliente,Integer> {

    public Page<Cliente> findAllByOrderRagioneSociale(Pageable pageable);
}
