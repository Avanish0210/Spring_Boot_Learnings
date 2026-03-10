package com.example.AopApp.services;

public interface ShipmentService {
    String orderPackage(Long orderId);

    String trackPackage(Long orderId);
}
