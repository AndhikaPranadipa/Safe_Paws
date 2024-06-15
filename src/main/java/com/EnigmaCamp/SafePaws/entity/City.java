package com.EnigmaCamp.SafePaws.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City {
    @Id
    private String id;

    @JsonProperty("text")
    private String city;

    @ManyToOne
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;

    @JsonIgnore
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<AddressShelter> addressShelterList;

    @JsonIgnore
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<AddressCustomer> addressCustomerList;
}
