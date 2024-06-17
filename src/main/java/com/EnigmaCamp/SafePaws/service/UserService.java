package com.EnigmaCamp.SafePaws.service;

import com.EnigmaCamp.SafePaws.entity.User;
import com.EnigmaCamp.SafePaws.utils.dto.user.request.UpdateUserDTO;

public interface UserService {

//    User existsByEmail(String email);
    User getByJWT();

    User updateUser(UpdateUserDTO request);
}
