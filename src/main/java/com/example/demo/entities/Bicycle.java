package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Bicycle extends Vehicle {
    public Bicycle() {
    }

    public Bicycle(String brand, String model, int price) {
        super(brand, model, price);
    }
}
