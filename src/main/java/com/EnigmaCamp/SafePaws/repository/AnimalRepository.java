package com.EnigmaCamp.SafePaws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.EnigmaCamp.SafePaws.entity.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, String>, JpaSpecificationExecutor<Animal> {

}
