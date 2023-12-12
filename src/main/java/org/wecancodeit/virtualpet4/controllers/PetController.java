package org.wecancodeit.virtualpet4.controllers;

import org.wecancodeit.virtualpet4.enums.RoleEnum;
import org.wecancodeit.virtualpet4.models.PetModel;
import org.wecancodeit.virtualpet4.models.ShelterModel;
import org.wecancodeit.virtualpet4.repositories.PetRepository;
import org.wecancodeit.virtualpet4.repositories.ShelterRepository;
import org.wecancodeit.virtualpet4.repositories.UserRepository;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/pet")
public class PetController extends BaseController {
    @Resource
    PetRepository petRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private ShelterRepository shelterRepository;

    public PetController(ShelterRepository shelterRepository,PetRepository petRepository, UserRepository userRepository) {
        super(userRepository);
        this.petRepository = petRepository;
        this.shelterRepository = shelterRepository;
    }

    @GetMapping()
    public ResponseEntity<?> getAllPets(HttpServletRequest request) {
        try {
            checkAccess(RoleEnum.ADMIN,request);
            return ResponseEntity.ok(petRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/shelter/{shelterId}")
    public ResponseEntity<?> getShelterPets(@PathVariable long shelterId,HttpServletRequest request) {
        try {
            checkAccess(RoleEnum.GUEST,request);
            Iterable<PetModel> pets = petRepository.findAll();
            ArrayList<PetModel> returnValue = new ArrayList<>();
            for(PetModel pet : pets){
                if(pet.getShelter_id()==shelterId){
                    returnValue.add(pet);
                }
            }
            return ResponseEntity.ok(returnValue);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPetById(@PathVariable long id, HttpServletRequest request) {
        try {
            checkAccess(RoleEnum.GUEST,request);
            return ResponseEntity.ok(petRepository.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePetById(@PathVariable long id, HttpServletRequest request) {
        try {
            checkAccess(RoleEnum.ADMIN,request);
            petRepository.deleteById(id);
            Optional<PetModel> pet = petRepository.findById(id);
            return ResponseEntity.ok(!pet.isPresent());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
     @PostMapping()
    public ResponseEntity<?> update(@RequestBody PetModel oldPet, HttpServletRequest request){
        try {
            checkAccess(RoleEnum.MANAGER,request);
            if(oldPet.getShelter_id()>0){
                Optional<ShelterModel> opShelter = shelterRepository.findById(oldPet.getShelter_id());
                if(!opShelter.isPresent()){
                    throw new Exception("You must have a valid shelter");
                }
                PetModel pet = new PetModel(oldPet.getName(), oldPet.getPetType(),oldPet.getAge(),opShelter.get());
                pet.setId(oldPet.getId());
                return ResponseEntity.ok(petRepository.save(pet));
            }else{
                throw new Exception("You must have a valid shelter");
            }
         
        } catch (Exception e) {
             return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
