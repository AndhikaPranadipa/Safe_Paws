package com.EnigmaCamp.SafePaws.repository;

import com.EnigmaCamp.SafePaws.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, String> {
}
