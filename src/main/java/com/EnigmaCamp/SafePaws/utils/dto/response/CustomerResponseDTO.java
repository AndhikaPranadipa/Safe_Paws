package com.EnigmaCamp.SafePaws.utils.dto.response;

import com.EnigmaCamp.SafePaws.entity.AddressCustomer;
import com.EnigmaCamp.SafePaws.entity.City;
import com.EnigmaCamp.SafePaws.entity.Customer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponseDTO {

    private String fullName;

    private String email;

    private String phone;

    private City city;

    public static CustomerResponseDTO fromUser(Customer customer, AddressCustomer addressCustomer){
        return CustomerResponseDTO.builder()
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .city(addressCustomer.getCity())
                .build();
    }
}
