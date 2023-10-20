package com.majidim.easybankv4.easybankv4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor

public final class Employe extends Personne {
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
