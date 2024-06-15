package com.EnigmaCamp.SafePaws.entity;

import com.EnigmaCamp.SafePaws.utils.enums.AnimalStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "animals")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String type;

    private String race;

    private String name;

    private Integer weight;

    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "animal_status")
    private AnimalStatus animalStatus;

    private String description;

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    @JsonIgnore
    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
    private List<Adoption> adoptionList;


}
