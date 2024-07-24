package com.nuzzle.backend.pet.repository;

import com.nuzzle.backend.pet.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
