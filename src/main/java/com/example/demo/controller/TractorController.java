package com.example.demo.controller;

import com.example.demo.entities.Tractor;
import com.example.demo.repository.TractorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/tractor")
public class TractorController {
    private final TractorRepository tractorRepository;

    public TractorController(TractorRepository tractorRepository) {
        this.tractorRepository = tractorRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Tractor> getAllTractors() {
        return tractorRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tractor getTractorById(@PathVariable("id") int id) {
        return tractorRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tractor createTractor(@RequestBody Tractor tractor) {
        return tractorRepository.save(tractor);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") int id, @RequestBody Tractor tractor) throws Exception {
        Optional<Tractor> updateTractor = tractorRepository.findById(id);
        if (updateTractor.isPresent()) {
            updateTractor.get().setBrand(tractor.getBrand());
            updateTractor.get().setModel(tractor.getModel());
            updateTractor.get().setPrice(tractor.getPrice());
            tractorRepository.save(updateTractor.get());
        } else {
            throw new Exception("Tractor not found");
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteTractor(@PathVariable("id") int id) {
        tractorRepository.deleteById(id);
    }
}
