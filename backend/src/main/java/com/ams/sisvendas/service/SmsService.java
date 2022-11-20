package com.ams.sisvendas.service;

import com.ams.sisvendas.entities.Sale;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${twilio.sid}")
    private String twilioSid;

    @Value("${twilio.key}")
    private String twilioKey;

    @Value("${twilio.phone.from}")
    private String twilioPhoneFrom;

    @Value("${twilio.phone.to}")
    private String twilioPhoneTo;

    private final SaleService saleService;

    public SmsService(SaleService saleService) {
        this.saleService = saleService;
    }

    public void sendSms(Long idSale) {

        Twilio.init(twilioSid, twilioKey);

        PhoneNumber to = new PhoneNumber(twilioPhoneTo);
        PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

        Message message = Message.creator(to, from, getTemplateSMS(idSale)).create();

        System.out.println(message.getSid());
    }

    private String getTemplateSMS(Long idSale) {
        Sale sale = this.saleService.findById(idSale);
        return "O Vendendo: " +
                sale.getSellerName() +
                "foi destaque em " +
                sale.getDate().getMonthValue() +
                "/" +
                sale.getDate().getYear() +
                " com um total de R$: " +
                String.format("%.2f", sale.getAmount());
    }
}
