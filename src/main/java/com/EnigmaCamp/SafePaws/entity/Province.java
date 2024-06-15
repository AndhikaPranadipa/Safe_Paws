package com.EnigmaCamp.SafePaws.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Province {
    @Id
    private String id;

    @JsonProperty("text")
    private String province;

    @JsonIgnore
    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    private List<City> cities;
}
