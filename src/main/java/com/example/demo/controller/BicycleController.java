package com.example.demo.controller;

import com.example.demo.entities.Bicycle;
import com.example.demo.repository.BicycleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/bicycle")
public class BicycleController {
    private final BicycleRepository bicycleRepository;

    public BicycleController(BicycleRepository bicycleRepository) {
        this.bicycleRepository = bicycleRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Bicycle> getAllBicycles() {
        return bicycleRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Bicycle getBicycleById(@PathVariable("id") int id) {
        return bicycleRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bicycle createBicycle(@RequestBody Bicycle bicycle) {
        return bicycleRepository.save(bicycle);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") int id, @RequestBody Bicycle bicycle) throws Exception {
        Optional<Bicycle> updateBicycle = bicycleRepository.findById(id);
        if (updateBicycle.isPresent()) {
            updateBicycle.get().setBrand(bicycle.getBrand());
            updateBicycle.get().setModel(bicycle.getModel());
            updateBicycle.get().setPrice(bicycle.getPrice());
            bicycleRepository.save(updateBicycle.get());
        } else {
            throw new Exception("Bicycle not found");
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteBicycle(@PathVariable("id") int id) {
        bicycleRepository.deleteById(id);
    }
}
