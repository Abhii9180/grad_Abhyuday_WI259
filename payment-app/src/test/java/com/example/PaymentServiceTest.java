package com.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import com.example.client.PaymentClient;
import com.example.service.PaymentService;

public class PaymentServiceTest {

    // Q1: Test interface method without implementation
    @Test
    void testGetStatus() {

        PaymentService mockService = mock(PaymentService.class);

        when(mockService.getStatus()).thenReturn("SUCCESS");

        PaymentClient client = new PaymentClient(mockService);

        String result = client.checkStatus();

        assertEquals("SUCCESS", result);
    }

    // Q2: Test void method
    @Test
    void testProcessPayment() {

        PaymentService mockService = mock(PaymentService.class);

        PaymentClient client = new PaymentClient(mockService);

        client.makePayment(500);

        // verify void method was called
        verify(mockService).processPayment(500);
    }

    // Q3: Verify method called specific number of times
    @Test
    void testProcessPaymentCalledMultipleTimes() {

        PaymentService mockService = mock(PaymentService.class);

        PaymentClient client = new PaymentClient(mockService);

        client.makePayment(100);
        client.makePayment(100);

        verify(mockService, times(2)).processPayment(100);
    }
}