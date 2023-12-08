package org.wecancodeit.virtualpet4.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.wecancodeit.virtualpet4.models.ShelterModel;
import org.wecancodeit.virtualpet4.repositories.ShelterRepository;

import jakarta.annotation.Resource;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shelter")
public class ShelterController {

    @Resource
    private ShelterRepository shelterRepository;

    public ShelterController(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllShelters() {
        try {
            return ResponseEntity.ok(shelterRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getShelterById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(shelterRepository.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    
    @GetMapping("/pets/{id}")
    public ResponseEntity<?> getPetsById(@PathVariable long id) {
        try {
              Optional<ShelterModel> opShelter = shelterRepository.findById(id);
            if(opShelter.isPresent()){
                return ResponseEntity.ok(opShelter.get().getPets());
            }
            return ResponseEntity.ok("Shelter not found");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShelter(@PathVariable long id) {
        try {
            shelterRepository.deleteById(id);
            Optional<ShelterModel> shelter = shelterRepository.findById(id);
            return ResponseEntity.ok(!shelter.isPresent());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> update(@RequestBody ShelterModel shelter){
        try {
            return ResponseEntity.ok(shelterRepository.save(shelter));
        } catch (Exception e) {
             return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
