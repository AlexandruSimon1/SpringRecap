package com.example.demo.entities;

import javax.persistence.*;

@Entity
//@SequenceGenerator(name="seq", initialValue=0, allocationSize=100)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private Integer id;
    private String brand;
    private String model;
    private int price;

    public Vehicle() {
    }

    public Vehicle(String brand, String model, int price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
