package com.example.demo.repository;

import com.example.demo.entities.Bicycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BicycleRepository extends JpaRepository <Bicycle,Integer> {
}
