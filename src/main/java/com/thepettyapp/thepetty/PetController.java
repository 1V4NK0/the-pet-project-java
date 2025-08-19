package com.thepettyapp.thepetty;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("/pets")
@CrossOrigin(origins = "*")
public class PetController {
    private final PetService petService;

    //this is called a dependency injection, you don't create a new service in this example but only inject (use a ready one)
    //like swapping engines for cars, i could use some other service for the controller
    public PetController(PetService petService) {
        this.petService = petService;
    }

    //ALLOW CORS REQUESTS WHATEVER
    @CrossOrigin(origins = "*")
    @PostMapping("/changeName")
    public ResponseEntity<Pet> changeName(@RequestBody Map<String, String> payload) {
        String newName = payload.get("name");
        Pet updatedPet = petService.changeNameAPI(newName);
        System.out.println(ResponseEntity.ok(updatedPet));
        return ResponseEntity.ok(updatedPet);
    }

    //defines a GET endpoint, produces = MediaType.TEXT_EVENT_STREAM_VALUE means continuous stream of data instead of one static JSON
    @GetMapping(value = "/sse/pet-stats", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Pet> streamPetStats() {
        return Flux.interval(Duration.ofSeconds(15))
                .map(tick -> {
                    petService.decreaseEnergy();
                    petService.decreaseHunger();
                    return petService.fetchPet(1);
                });
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/dodep")
    public ResponseEntity<Pet> dodep(@RequestBody Map<String, Integer> payload) {
        System.out.println("\nINSIDE PET CONTROLLER DODEP METHOD");
        System.out.println("received dodep payload: " + payload);
        int dodep =  payload.get("amount");
        System.out.println("dodep amount: " + dodep);
        Pet updatedPet = petService.increaseBalance(dodep);
        System.out.println("updated pet balance: " + updatedPet.getBalance());
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
