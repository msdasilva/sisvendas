package com.ams.sisvendas.controller;

import java.util.List;

import com.ams.sisvendas.entities.Sale;
import com.ams.sisvendas.service.SaleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public Page<Sale> getAllSale(@RequestParam(required = false, value = "minDate", defaultValue = "") String minDate,
                                 @RequestParam(required = false, value = "maxDate", defaultValue = "") String maxDate,
                                 Pageable pageable) {
        return this.saleService.getAllSale(minDate,
                maxDate,
                pageable);
    }
}
