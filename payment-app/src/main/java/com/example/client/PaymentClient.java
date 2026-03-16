package com.example.client;

import com.example.service.PaymentService;

public class PaymentClient {

    private PaymentService paymentService;

    public PaymentClient(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void makePayment(double amount) {
        paymentService.processPayment(amount);
    }

    public String checkStatus() {
        return paymentService.getStatus();
    }
}