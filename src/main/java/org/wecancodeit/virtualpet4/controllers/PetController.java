package org.wecancodeit.virtualpet4.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.wecancodeit.virtualpet4.models.PetModel;
import org.wecancodeit.virtualpet4.repositories.PetRepository;

import jakarta.annotation.Resource;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/pet")
public class PetController {
    @Resource
    PetRepository petRepository;

    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @GetMapping()
    public ResponseEntity<?> getAllPets() {
        try {
            return ResponseEntity.ok(petRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/shelter/{shelterId}")
    public ResponseEntity<?> getShelterPets(@PathVariable long shelterId) {
        try {
            return ResponseEntity.ok(petRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPetById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(petRepository.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePetById(@PathVariable long id) {
        try {
            petRepository.deleteById(id);
            Optional<PetModel> pet = petRepository.findById(id);
            return ResponseEntity.ok(!pet.isPresent());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
     @PostMapping()
    public ResponseEntity<?> update(@RequestBody PetModel pet){
        try {
            return ResponseEntity.ok(petRepository.save(pet));
        } catch (Exception e) {
             return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
