package com.example;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ClientTest {

    @Test
    void testInterfaceMethod() {

        // 1️⃣ Create mock object of interface
        Service mockService = Mockito.mock(Service.class);

        // 2️⃣ Define behavior
        when(mockService.calculate(5, 3)).thenReturn(8);

        // 3️⃣ Inject mock into client
        Client client = new Client(mockService);

        // 4️⃣ Call method
        int result = client.doCalculation(5, 3);

        // 5️⃣ Assert result
        assertEquals(8, result);
    }
    
    
    @Test
    void testVoidMethod() {

        Service mockService = Mockito.mock(Service.class);

        Client client = new Client(mockService);

        client.processMessage("Hello");

        // Verify that method was called
        verify(mockService).sendMessage("Hello");
    } 
    
    
    @Test
    void testMethodCallCount() {

        Service mockService = Mockito.mock(Service.class);

        Client client = new Client(mockService);

        client.processMessage("Hi");
        client.processMessage("Hi");

        // Check called exactly 2 times
        verify(mockService, times(2)).sendMessage("Hi");
    }
    
    
}