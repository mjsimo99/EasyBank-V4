package com.majidim.easybankv4.easybankv4.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "Employes")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Employe extends Personne {
    @Id
    @Column(name = "matricule")
    private String matricule;
    private LocalDate dateRecrutement;
    private String emailAdresse;



    public Employe(String nom, String prenom, LocalDate dateN, String tel, String adress, String matricule, LocalDate dateRecrutement, String emailAdresse) {
        super(nom, prenom, dateN, tel, adress);
        setMatricule(matricule);
        setDateRecrutement(dateRecrutement);
        setEmailAdresse(emailAdresse);
    }

}
