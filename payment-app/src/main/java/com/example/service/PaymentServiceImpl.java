package com.example.service;

public class PaymentServiceImpl implements PaymentService {

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of: " + amount);
    }

    @Override
    public String getStatus() {
        return "SUCCESS";
    }
}