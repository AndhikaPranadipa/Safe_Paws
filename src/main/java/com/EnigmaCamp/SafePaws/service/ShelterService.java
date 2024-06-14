package com.EnigmaCamp.SafePaws.service;

import com.EnigmaCamp.SafePaws.entity.Shelter;

public interface ShelterService {

    Shelter existsByEmail(String email);
}
