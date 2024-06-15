package com.EnigmaCamp.SafePaws.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address_customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    private String description;
}
