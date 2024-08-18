package com.nuzzle.backend.pet.domain;

import com.nuzzle.backend.family.domain.Family;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Pet {
    @Id
<<<<<<< HEAD
    @GeneratedValue(strategy = GenerationType.AUTO)
=======
    @GeneratedValue(strategy = GenerationType.IDENTITY)
>>>>>>> c9f35ada6c60f5934cc32d4d0762fd6c01719896
    @Column(name = "pet_id")
    private Long petId;

    @Column(name = "pet_type")
    private String petType;

    @Column(name = "pet_img")
    private String petImg;

    @OneToMany(mappedBy = "pet")
    private List<Family> families;
}
