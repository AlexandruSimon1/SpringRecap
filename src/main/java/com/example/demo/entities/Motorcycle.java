package com.example.demo.entities;

public class Motorcycle extends Vehicle {
    private int topSpeed;
    private MotorcycleShape motorcycleShape;

    public Motorcycle() {
    }

    public Motorcycle(String brand, String model, int price, int topSpeed, MotorcycleShape motorcycleShape) {
        super(brand, model, price);
        this.topSpeed = topSpeed;
        this.motorcycleShape = motorcycleShape;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
    }

    public MotorcycleShape getMotorcycleShape() {
        return motorcycleShape;
    }

    public void setMotorcycleShape(MotorcycleShape motorcycleShape) {
        this.motorcycleShape = motorcycleShape;
    }
}
