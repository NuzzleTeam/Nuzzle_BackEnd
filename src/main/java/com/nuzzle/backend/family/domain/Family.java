package com.nuzzle.backend.family.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nuzzle.backend.family.domain.mapping.FamilyKeyword;
import com.nuzzle.backend.family.domain.mapping.FamilyQuestion;
import com.nuzzle.backend.pet.domain.Pet;
import com.nuzzle.backend.pet.domain.PetColor;
import com.nuzzle.backend.user.domain.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "family_id")
    private Long familyId;

    @Column(name = "pet_name")
    private String petName;

    @Enumerated(EnumType.STRING) // EnumType.STRING으로 지정
    @Column(name = "pet_color")
    private PetColor petColor;

    @Column(name = "family_status")
    private String familyStatus;

    @Column(name = "invitation_code")
    private String invitationCode;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    @JsonBackReference
    private Pet pet;

    @OneToMany(mappedBy = "family")
    @JsonManagedReference
    private List<FamilyQuestion> familyQuestions;

    @OneToMany(mappedBy = "family")
    @JsonManagedReference
    private List<FamilyKeyword> familyKeywords;

    @OneToMany(mappedBy = "family")
    @JsonManagedReference
    private List<User> users;
}
