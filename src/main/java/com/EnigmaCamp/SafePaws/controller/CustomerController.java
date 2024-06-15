package com.EnigmaCamp.SafePaws.controller;

import com.EnigmaCamp.SafePaws.entity.AddressCustomer;
import com.EnigmaCamp.SafePaws.service.AddressCustomerService;
import com.EnigmaCamp.SafePaws.utils.dto.Res;
import com.EnigmaCamp.SafePaws.utils.dto.request.AddressCustomerDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@PreAuthorize("hasRole('CUSTOMER')")
@AllArgsConstructor
public class CustomerController {

    private final AddressCustomerService addressCustomerService;

    @GetMapping
    public ResponseEntity<?> hello() {

        String result = "hello";

        return Res.renderJson(result, "Hello anda Customer", HttpStatus.CREATED);
    }

    @PostMapping(path = "/address")
    public ResponseEntity<?> setAddress(@RequestBody AddressCustomerDTO request) {

        AddressCustomer result = addressCustomerService.create(request);

        return Res.renderJson(result, "Address customer successfully", HttpStatus.CREATED);
    }
}
