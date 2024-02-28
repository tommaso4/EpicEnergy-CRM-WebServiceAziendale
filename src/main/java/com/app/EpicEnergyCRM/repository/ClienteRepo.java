package com.app.EpicEnergyCRM.repository;

import com.app.EpicEnergyCRM.model.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;


public interface ClienteRepo extends JpaRepository<Cliente,Integer>, PagingAndSortingRepository<Cliente,Integer> {

    Page<Cliente> findAllByOrderByRagioneSociale(Pageable pageable);
    Page<Cliente> findAllByOrderByFatturatoAnnualeDesc(Pageable pageable);
    Page<Cliente> findAllByOrderByDataInserimentoDesc(Pageable pageable);
    Page<Cliente> findAllByOrderByDataUltimoContattoDesc(Pageable pageable);

//    Page<Cliente> findAllByOrderByIndirizziProvinciaSedeLegale(Pageable pageable);

    List<Cliente> findByFatturatoAnnualeGreaterThanEqual(double fatturatoMinimo);

    List<Cliente> findByDataInserimentoBetween(LocalDate startDate, LocalDate endDate);

    List<Cliente> findByDataUltimoContattoBetween(LocalDate startDate, LocalDate endDate);

    List<Cliente> findByRagioneSocialeContainingIgnoreCase(String parteDelNome);

    @Query(value = "select c from Cliente c JOIN c.indirizzo i JOIN i.comune com order by com.provincia ASC", nativeQuery = true)
    List<Cliente> orderByProvincia ();

}
