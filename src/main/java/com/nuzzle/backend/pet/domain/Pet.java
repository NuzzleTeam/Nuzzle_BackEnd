package com.nuzzle.backend.pet.domain;

import com.nuzzle.backend.family.domain.Family;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long petId;

    @Column(name = "pet_type")
    private String petType;

    @Column(name = "pet_img")
    private String petImg;

    @OneToMany(mappedBy = "pet")
    private List<Family> families;
}
