package com.example;

public class Client {

    private Service service;

    public Client(Service service) {
        this.service = service;
    }

    public int doCalculation(int a, int b) {
        return service.calculate(a, b);
    }

    public void processMessage(String msg) {
        service.sendMessage(msg);
    }
}