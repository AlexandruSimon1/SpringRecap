package com.example.demo.controller;

import com.example.demo.entities.Motorcycle;
import com.example.demo.entities.enums.MotorcycleShape;
import com.example.demo.repository.MotorcycleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/motorcycle")
public class MotorcycleController {
    private final MotorcycleRepository motorcycleRepository;

    public MotorcycleController(MotorcycleRepository motorcycleRepository) {
        this.motorcycleRepository = motorcycleRepository;
    }

    @GetMapping(value = "/sort{order}/{motorcycleshape}")
    @ResponseStatus(HttpStatus.OK)
    public List<Motorcycle> orderByTopSpeed(@PathVariable String motorcycleshape, @PathVariable String order) {
        MotorcycleShape motorcycleShape = MotorcycleShape.valueOf(motorcycleshape.toUpperCase());
        if (order.equals("desc")) {
            return motorcycleRepository.findByMotorcycleShapeLikeOrderByTopSpeedDesc(motorcycleShape);
        }
        return motorcycleRepository.findByMotorcycleShapeLikeOrderByTopSpeedAsc(motorcycleShape);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Motorcycle> getAllMotorcycles() {
        return motorcycleRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Motorcycle getMotorcycleById(@PathVariable("id") int id) {
        return motorcycleRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Motorcycle createMotorcycle(@RequestBody Motorcycle motorcycle) {
        return motorcycleRepository.save(motorcycle);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") int id, @RequestBody Motorcycle motorcycle) throws Exception {
        Optional<Motorcycle> updateMotorcycle = motorcycleRepository.findById(id);
        if (updateMotorcycle.isPresent()) {
            updateMotorcycle.get().setBrand(motorcycle.getBrand());
            updateMotorcycle.get().setModel(motorcycle.getModel());
            updateMotorcycle.get().setPrice(motorcycle.getPrice());
            motorcycleRepository.save(updateMotorcycle.get());
        } else {
            throw new Exception("Motorcycle not found");
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteMotorcycle(@PathVariable("id") int id) {
        motorcycleRepository.deleteById(id);
    }
}
