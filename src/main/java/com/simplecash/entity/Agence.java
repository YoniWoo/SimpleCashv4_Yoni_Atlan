package com.simplecash.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agence {

    @Id
    @Column(length = 5)
    private String id;

    private LocalDate dateCreation;

    @OneToOne(mappedBy = "agence", cascade = CascadeType.ALL)
    private Gerant gerant;

    @OneToMany(mappedBy = "agence")
    private List<Conseiller> conseillers;
}
