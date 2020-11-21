package com.example.demo.entities;

public class Tractor extends Vehicle{
    private int cylindricalCapacity;

    public Tractor() {
    }

    public Tractor(String brand, String model, int price, int cylindricalCapacity) {
        super(brand, model, price);
        this.cylindricalCapacity = cylindricalCapacity;
    }

    public int getCylindricalCapacity() {
        return cylindricalCapacity;
    }

    public void setCylindricalCapacity(int cylindricalCapacity) {
        this.cylindricalCapacity = cylindricalCapacity;
    }
}
