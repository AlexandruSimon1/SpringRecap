package com.example.demo.repository;

import com.example.demo.entities.Motorcycle;
import com.example.demo.entities.enums.MotorcycleShape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotorcycleRepository extends JpaRepository<Motorcycle, Integer> {
    List<Motorcycle> findByMotorcycleShapeLikeOrderByTopSpeedDesc(MotorcycleShape motorcycleShape);

    List<Motorcycle> findByMotorcycleShapeLikeOrderByTopSpeedAsc(MotorcycleShape motorcycleShape);
}
