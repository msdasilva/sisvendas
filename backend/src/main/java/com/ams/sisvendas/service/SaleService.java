package com.ams.sisvendas.service;

import com.ams.sisvendas.entities.Sale;
import com.ams.sisvendas.repository.SaleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public Page<Sale> getAllSale(String minDate,
                                 String maxDate,
                                 Pageable pageable) {

        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        LocalDate minData = minDate.equals("") ? today.minusDays(365) : LocalDate.parse(minDate);
        LocalDate maxData = maxDate.equals("") ? today : LocalDate.parse(maxDate);

        return this.saleRepository.getAllSale(minData, maxData, pageable);
    }

    public Sale findById(Long idSale) {
        return this.saleRepository.findById(idSale).get();
    }
}
