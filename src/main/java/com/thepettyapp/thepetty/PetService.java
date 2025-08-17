package com.thepettyapp.thepetty;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

@Service
public class PetService {
    private final PetRepository petRepository;
    private Pet pet;
    Timer timer;
    //TO DO:
    //INTRODUCE BOUNDARIES FOR METHODS: IF pet.getHunger() + 2 > 100, then pet.setHunger(100)
    public Pet decreaseHunger() {
        if (pet.getHunger() - 1 >= 0) {
            pet.setHunger(pet.getHunger() - 1);
        }
        return petRepository.save(pet);
    }

//    @Scheduled(fixedRate = 3000)
//    public void autoDecreaseHunger() {
//        decreaseHunger();
//        this.pet = petRepository.save(pet);
//    }
    public Pet changeNameAPI(String newName) {
        this.pet.setName(newName);
        this.pet = petRepository.save(pet);
        return this.pet;
    }

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
        timer = new Timer();

        //IN THE FUTURE YOU'D WANT TO FETCH PET BASED ON THE USER ID OR WHATEVER, NOT JUST ONE HARDCODED PET
        this.pet = fetchPet(1);
    }

    public Pet fetchPet(int petId) {
//        Pet pet = petRepository.findById(petId).orElseThrow(() -> new RuntimeException("Pet not found with such ID: " + petId));
        Pet pet = petRepository.findById(petId).orElseGet(() -> createPet("oskarik"));
        this.pet = pet; // Update cache
        return pet;
    }

    public Pet createPet(String name) {
        Pet newPet = new Pet(name, 50, 50 ,50 );
        System.out.printf("\nCREATING PET... NAME: %s", newPet.getName());
        return petRepository.save(newPet);
    }

    public Pet increaseHunger() {
        if (pet.getHunger() + 2 <= 100 && pet.getBalance() - 1 > 0 && pet.getEnergy() + 1 <= 100) {
            pet.setHunger(pet.getHunger() + 2);
            pet.setBalance(pet.getBalance() - 1);
            pet.setEnergy(pet.getEnergy() + 1);
            this.pet = petRepository.save(pet);
        }
        return this.pet;
    }

    private void refreshPetFromDB() {
        this.pet = fetchPet(1);
    }



    public Pet increaseEnergy() {
//        if (pet.getEnergy() + 2 <= 100) pet.setEnergy(pet.getEnergy() + 2);
        pet.setEnergy(pet.getEnergy() + 2);

        return petRepository.save(pet);
    }

    public Pet decreaseEnergy(int amount) {
        pet.setEnergy(pet.getEnergy() - amount);
        return petRepository.save(pet);
    }

    public Pet play() {
        if (pet.getEnergy() - 10 >= 0 && pet.getHunger() - 1 >= 0) {
            decreaseEnergy(10);
            decreaseHunger();
//            pet.setBalance(pet.getBalance() + 1);
        }
        return petRepository.save(pet);
    }

    public Pet rest() {
        if (pet.getEnergy() < 100) {
            pet.setEnergy(100);
            System.out.println("RESTING.... ENERGY LEVEL: " + pet.getEnergy());
        }
       return petRepository.save(pet);
    }

    public Pet decreaseHealth() {
        if (pet.getHealth() - 1 > 0) {
            pet.setHealth(pet.getHealth() - 1);

        }
        return petRepository.save(pet);
    }

//    @Scheduled(fixedRate = 10000)
//    public void autoDecreaseHealth() {
//        if (pet.getHunger() < 50 && pet.getEnergy() < 50) {
//            decreaseHealth();
//        }
//        this.pet = petRepository.save(pet);
//
//    }

    public Pet increaseHealth() {
        //TO IMPLEMENT:
        //IF HUNGER && ENERGY > 50 -> HEALTH++ EVERY 30 SEC
        pet.setHealth(pet.getHealth() + 1);
        return petRepository.save(pet);
    }

    public Pet increaseHealth(int amount) {
        //TO IMPLEMENT:
        //IF HUNGER && ENERGY < 50 -> HEALTH-- EVERY 30 SEC
        pet.setHealth(pet.getHealth() + amount);
        return petRepository.save(pet);
    }

    public Pet increaseBalance(int amount) {
        pet.setBalance(pet.getBalance() + amount);
        return petRepository.save(pet);
    }
}
