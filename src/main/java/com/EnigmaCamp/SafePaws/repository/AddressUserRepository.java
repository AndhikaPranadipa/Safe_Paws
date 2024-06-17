package com.EnigmaCamp.SafePaws.repository;

import com.EnigmaCamp.SafePaws.entity.AddressUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressUserRepository extends JpaRepository<AddressUser, String> {
    List<AddressUser> findByUserId(String userId);
}
