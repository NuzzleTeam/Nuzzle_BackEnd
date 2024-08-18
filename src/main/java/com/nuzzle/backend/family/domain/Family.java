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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "family_id")
    private Long familyId;

    @Column(name = "pet_name")
    private String petName;

    @Column(name = "pet_color")
    private String petColor;

    @Column(name = "family_status")
    private String familyStatus;

    @Column(name = "invitation_code")
    private String invitationCode;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @OneToMany(mappedBy = "family")
    private List<FamilyQuestion> familyQuestions;

    @OneToMany(mappedBy = "family")
    private List<FamilyKeyword> familyKeywords;

    @OneToMany(mappedBy = "family")
    private List<User> users;
}
