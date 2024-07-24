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
    private Long petId;

    private String petType;
    private String petImg;

    @OneToMany(mappedBy = "pet")
    private List<Family> families;


}
