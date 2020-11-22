package com.example.demo.repository;

import com.example.demo.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByOrderByPriceDesc();

    List<Car> findByOrderByPriceAsc();
}
