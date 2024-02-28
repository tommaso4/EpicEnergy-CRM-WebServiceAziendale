package com.app.EpicEnergyCRM.repository;

import com.app.EpicEnergyCRM.enums.TipoFattura;
import com.app.EpicEnergyCRM.model.entities.Cliente;
import com.app.EpicEnergyCRM.model.entities.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.util.List;


public interface FattureRepo extends JpaRepository<Fattura,Integer>, PagingAndSortingRepository<Fattura,Integer> {
    List<Fattura> findByCliente(Cliente cliente);

    List<Fattura> findByTipoFattura(TipoFattura tipoFattura);

    List<Fattura> findByData(LocalDate data);

    @Query(value = "SELECT * FROM Fattura f WHERE EXTRACT(YEAR FROM f.data) = :annoData", nativeQuery = true)
    List<Fattura> findByYear(@Param("annoData") int anno);

    List<Fattura> findByImportoBetween(double minImporto, double maxImporto);
}
