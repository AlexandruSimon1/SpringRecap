package com.example.demo.controller;

import com.example.demo.entities.Vehicle;
import com.example.demo.repository.VehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/vehicle")
public class VehicleController {
    private final VehicleRepository vehicleRepository;

    public VehicleController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Vehicle> getAllVehicle() {
        return vehicleRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Vehicle getVehicleById(@PathVariable("id") int id) {
        return vehicleRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicle createVehicle(@RequestBody Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") int id, @RequestBody Vehicle vehicle) throws Exception {
        Optional<Vehicle> updateVehicle = vehicleRepository.findById(id);
        if (updateVehicle.isPresent()) {
            updateVehicle.get().setBrand(vehicle.getBrand());
            updateVehicle.get().setModel(vehicle.getModel());
            updateVehicle.get().setPrice(vehicle.getPrice());
            vehicleRepository.save(updateVehicle.get());
        }
        else {
            throw new Exception("Vehicle not found");
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteVehicle(@PathVariable("id") int id){
        vehicleRepository.deleteById(id);
    }
}
