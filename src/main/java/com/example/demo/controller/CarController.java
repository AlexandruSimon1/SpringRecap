package com.example.demo.controller;

import com.example.demo.entities.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/car")
public class CarController {
    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping(value = "/order{order}")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> orderByPrice(@PathVariable String order) {
        if (order.equals("desc")) {
            return carRepository.findByOrderByPriceDesc();
        }
        return carRepository.findByOrderByPriceAsc();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Car getCarById(@PathVariable("id") int id) {
        return carRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Car createCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") int id, @RequestBody Car car) throws Exception {
        Optional<Car> updateCar = carRepository.findById(id);
        if (updateCar.isPresent()) {
            updateCar.get().setBrand(car.getBrand());
            updateCar.get().setModel(car.getModel());
            updateCar.get().setPrice(car.getPrice());
            carRepository.save(updateCar.get());
        } else {
            throw new Exception("Car not found");
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteCar(@PathVariable("id") int id) {
        carRepository.deleteById(id);
    }
}
