package com.example.demo.entities;

import com.example.demo.entities.enums.CarShape;
import com.example.demo.entities.enums.GearType;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Car extends Vehicle{
    private int topSpeed;
    private GearType gearType;
    private CarShape carShape;

    public Car() {
    }

    public Car(String brand, String model, int price, int topSpeed, GearType gearType, CarShape carShape) {
        super(brand, model, price);
        this.topSpeed = topSpeed;
        this.gearType = gearType;
        this.carShape = carShape;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
    }

    public GearType getGearType() {
        return gearType;
    }

    public void setGearType(GearType gearType) {
        this.gearType = gearType;
    }

    public CarShape getCarShape() {
        return carShape;
    }

    public void setCarShape(CarShape carShape) {
        this.carShape = carShape;
    }
}
