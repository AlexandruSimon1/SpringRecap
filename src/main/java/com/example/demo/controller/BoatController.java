package com.example.demo.controller;

import com.example.demo.entities.Boat;
import com.example.demo.repository.BoatRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/boat")
public class BoatController {
    private final BoatRepository boatRepository;

    public BoatController(BoatRepository boatRepository) {
        this.boatRepository = boatRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Boat> getAllBoats() {
        return boatRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Boat getBoatById(@PathVariable("id") int id) {
        return boatRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Boat createBoat(@RequestBody Boat boat) {
        return boatRepository.save(boat);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") int id, @RequestBody Boat boat) throws Exception {
        Optional<Boat> updateBoat = boatRepository.findById(id);
        if (updateBoat.isPresent()) {
            updateBoat.get().setBrand(boat.getBrand());
            updateBoat.get().setModel(boat.getModel());
            updateBoat.get().setPrice(boat.getPrice());
            boatRepository.save(updateBoat.get());
        } else {
            throw new Exception("Boat not found");
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteBoat(@PathVariable("id") int id) {
        boatRepository.deleteById(id);
    }
}
