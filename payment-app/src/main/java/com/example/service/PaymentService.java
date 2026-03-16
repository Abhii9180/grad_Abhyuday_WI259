package com.example.service;

public interface PaymentService {

    void processPayment(double amount);

    String getStatus();
}