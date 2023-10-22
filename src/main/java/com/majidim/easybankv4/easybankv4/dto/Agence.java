package com.majidim.easybankv4.easybankv4.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="Agences")
public class Agence {
    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "nom")
    private String nom;
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "tel")
    private String tel;
    /*private List<DemendeCredit> demendeCredits;
    private List<Compte> comptes;
    private List<EmployeAgency> employeAgencies;*/


}