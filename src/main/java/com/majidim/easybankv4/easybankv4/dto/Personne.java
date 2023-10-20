package com.majidim.easybankv4.easybankv4.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Personne {
    protected String nom;
    protected String prenom;
    protected LocalDate dateN;
    protected String tel;
    protected String adress;


}

