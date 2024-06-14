package com.EnigmaCamp.SafePaws.service;

import com.EnigmaCamp.SafePaws.entity.Customer;

public interface CustomerService {

    Customer existsByEmail(String email);
}
