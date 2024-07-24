package com.nuzzle.backend.family.domain;

import com.nuzzle.backend.family.domain.mapping.FamilyKeyword;
import com.nuzzle.backend.family.domain.mapping.FamilyQuestion;
import com.nuzzle.backend.pet.domain.Pet;
import com.nuzzle.backend.user.domain.User;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long familyId;

    private String petName;
    private String petColor;
    private String FamilyStatus;


    @ManyToOne
    @JoinColumn(name = "petId")
    private Pet pet;

    @OneToMany(mappedBy = "family")
    private List<FamilyQuestion> familyQuestions;

    @OneToMany(mappedBy = "family")
    private List<FamilyKeyword> familyKeywords;

    @OneToMany(mappedBy = "family")
    private List<User> users;
}
