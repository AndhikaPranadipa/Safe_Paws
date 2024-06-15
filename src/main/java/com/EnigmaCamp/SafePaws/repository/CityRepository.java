package com.EnigmaCamp.SafePaws.repository;

import com.EnigmaCamp.SafePaws.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City,String> {
}
