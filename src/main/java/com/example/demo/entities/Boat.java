package com.example.demo.entities;

public class Boat extends Vehicle{
    private String horsePower;

    public Boat() {
    }

    public Boat(String brand, String model, int price, String horsePower) {
        super(brand, model, price);
        this.horsePower = horsePower;
    }

    public String getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(String horsePower) {
        this.horsePower = horsePower;
    }
}
