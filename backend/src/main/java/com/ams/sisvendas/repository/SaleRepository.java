package com.ams.sisvendas.repository;

import com.ams.sisvendas.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT s FROM Sale s WHERE s.date BETWEEN :minData AND :maxData ORDER BY s.amount DESC")
    Page<Sale> getAllSale(@Param("minData") LocalDate minData,
                          @Param("maxData") LocalDate maxData,
                          Pageable pageable);
}
