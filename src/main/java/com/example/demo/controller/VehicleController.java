package com.example.demo.controller;

import com.example.demo.entities.Vehicle;
import com.example.demo.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/vehicle")
public class VehicleController {
    private final BoatRepository boatRepository;
    private final CarRepository carRepository;
    private final MotorcycleRepository motorcycleRepository;
    private final TractorRepository tractorRepository;
    private final BicycleRepository bicycleRepository;
    private final VehicleRepository vehicleRepository;

    public VehicleController(BoatRepository boatRepository, CarRepository carRepository,
                             MotorcycleRepository motorcycleRepository, TractorRepository tractorRepository,
                             BicycleRepository bicycleRepository, VehicleRepository vehicleRepository) {
        this.boatRepository = boatRepository;
        this.carRepository = carRepository;
        this.motorcycleRepository = motorcycleRepository;
        this.tractorRepository = tractorRepository;
        this.bicycleRepository = bicycleRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping(value = "/count")
    @ResponseStatus(HttpStatus.OK)
    public HashMap<String, Long> countAll() {
        HashMap<String, Long> count = new HashMap<>();
        count.put("Car", carRepository.count());
        count.put("Tractor", tractorRepository.count());
        count.put("Motorcycle", motorcycleRepository.count());
        count.put("Bicycle", bicycleRepository.count());
        count.put("Boat", boatRepository.count());
        count.put("Total", vehicleRepository.count());
        return count;
    }

    @GetMapping(value = "/brand")
    @ResponseStatus(HttpStatus.OK)
    public HashMap<String, Integer> countByBrand() {
        List<Vehicle> allVehicle = vehicleRepository.findAll();
        HashMap<String, Integer> countBrand = new HashMap<>();
        for (Vehicle vehicle : allVehicle) {
            if (countBrand.get(vehicle.getBrand()) == null) {
                countBrand.put(vehicle.getBrand(), 1);
            } else {
                countBrand.put(vehicle.getBrand(), countBrand.get(vehicle.getBrand()) + 1);
            }
        }
        return countBrand;
    }

    @GetMapping(value = "/brand/{brand}")
    @ResponseStatus(HttpStatus.OK)
    public String countByBrand(@PathVariable String brand) {
        return brand + " : " + vehicleRepository.countByBrand(brand);
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
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
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
        } else {
            throw new Exception("Vehicle not found");
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteVehicle(@PathVariable("id") int id) {
        vehicleRepository.deleteById(id);
    }
}
