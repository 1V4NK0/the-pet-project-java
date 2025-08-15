package com.thepettyapp.thepetty;

import jakarta.persistence.*;

@Entity
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int health;
    private int energy;
    private int hunger;
    private int balance;
    private String owner_name;

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }
//    @Version // ✅ JPA will handle concurrency automatically
//    private Long version;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Pet() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public Pet(String name, int health, int energy, int hunger) {
        this.name = name;
        this.health = health;
        this.energy = energy;
        this.hunger = hunger;
        this.balance = 50;
    }

    public void setId(int id) {
        this.id = id;
    }
}
