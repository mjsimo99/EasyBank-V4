package com.majidim.easybankv4.easybankv4.dto;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "DemendeCredits" )
public class DemendeCredit {
    @Id
    private String numero;
    @Column
    private LocalDate date;
    @Column
    private Double montant;
    @Column
    private String duree;
    @Column
    private String remarque;
    @Column
    private CreditStatus status;
    @ManyToOne
    @JoinColumn(name = "employe_matricule")
    private Employe employe;

    @ManyToOne
    @JoinColumn(name = "client_code")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "agence_code")
    private Agence agence;

}
