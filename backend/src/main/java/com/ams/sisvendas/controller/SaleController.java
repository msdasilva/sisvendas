package com.ams.sisvendas.controller;

import com.ams.sisvendas.entities.Sale;
import com.ams.sisvendas.service.SaleService;
import com.ams.sisvendas.service.SmsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;
    private final SmsService smsService;

    public SaleController(SaleService saleService, SmsService smsService) {
        this.saleService = saleService;
        this.smsService = smsService;
    }

    @GetMapping
    public Page<Sale> getAllSale(@RequestParam(required = false, value = "minDate", defaultValue = "") String minDate,
                                 @RequestParam(required = false, value = "maxDate", defaultValue = "") String maxDate,
                                 Pageable pageable) {
        return this.saleService.getAllSale(minDate,
                maxDate,
                pageable);
    }

    @GetMapping("/{id}/notification")
    public void notificationSms(@PathVariable Long id) {
        this.smsService.sendSms(id);
    }
}
