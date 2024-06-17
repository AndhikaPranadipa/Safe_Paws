package com.EnigmaCamp.SafePaws.entity;

import com.EnigmaCamp.SafePaws.utils.enums.AdoptionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "adoption")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Adoption {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private LocalDate inspection_date;

    @Enumerated(EnumType.STRING)
    @Column(name = "adoption_status")
    private AdoptionStatus adoptionStatus;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;
}
