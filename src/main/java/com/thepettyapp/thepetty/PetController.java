package com.thepettyapp.thepetty;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/pets")
@CrossOrigin(origins = "http://localhost:5173")
public class PetController {
    private final PetService petService;

    //this is called a dependency injection, you don't create a new service in this example but only inject (use a ready one)
    //like swapping engines for cars, i could use some other service for the controller
    public PetController(PetService petService) {
        this.petService = petService;
    }

    //ALLOW CORS REQUESTS WHATEVER
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/changeName")
    public ResponseEntity<Pet> changeName(@RequestBody Map<String, String> payload) {
        String newName = payload.get("name");
        Pet updatedPet = petService.changeNameAPI(newName);
        System.out.println(ResponseEntity.ok(updatedPet));
        return ResponseEntity.ok(updatedPet);
    }

    @PostMapping("/dodep")
    public ResponseEntity<Pet> dodep(@RequestBody Map<String, Integer> payload) {
        int dodep = payload.get("dodep");
        Pet updatedPet = petService.increaseBalance(dodep);
        return ResponseEntity.ok(updatedPet);
    }

    @PostMapping("/feed")
    public ResponseEntity<Pet> feedPet() {
        //rather than returning just body (updated pet) you return full HTTP response with the code (200, 400, 500)
        Pet updatedPet = petService.increaseHunger();
        System.out.println(ResponseEntity.ok(updatedPet));
        return ResponseEntity.ok(updatedPet);
    }

    @PostMapping("/play")
    public ResponseEntity<Pet> play() {
        Pet updatedPet = petService.play();
        System.out.println(ResponseEntity.ok(updatedPet));
        return ResponseEntity.ok(updatedPet);
    }

    @GetMapping
    public ResponseEntity<Pet> getPet() {
        try {
            Pet pet = petService.fetchPet(1);
            System.out.printf("PET NAME: %s HUNGER: %d ENERGY: %d BALANCE: %d", pet.getName(), pet.getHunger(), pet.getEnergy(), pet.getBalance());
            return ResponseEntity.ok(pet);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/rest")
    public ResponseEntity<Pet> rest() {
       Pet pet = petService.rest();
       return ResponseEntity.ok(pet);
    }
}
