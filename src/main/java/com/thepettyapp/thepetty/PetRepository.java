package com.thepettyapp.thepetty;

import org.springframework.data.jpa.repository.JpaRepository;
//pet as for your custom class and Long as for the ID type (can be int or string probably)
public interface PetRepository extends JpaRepository<Pet, Integer> {
    //IF YOU DON'T NEED SOME CUSTOM FANCY FUNCTIONALITY YOU JUST LEAVE AS IS
    //ALL THE CRUD OPERATIONS ALREADY DEFINED AND INCLUDED
}
