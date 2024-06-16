package com.EnigmaCamp.SafePaws.service;

import com.EnigmaCamp.SafePaws.entity.User;

public interface UserService {

    User existsByEmail(String email);
}
