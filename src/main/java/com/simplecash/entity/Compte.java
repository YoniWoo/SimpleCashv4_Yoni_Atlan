package com.simplecash.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private BigDecimal solde;
    private LocalDate dateOuverture;
    private String typeCompte;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
