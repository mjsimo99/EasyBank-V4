package com.majidim.easybankv4.easybankv4.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
public final class Client extends Personne {
    private String code;

    public Client(String code, String nom, String prenom, LocalDate dateN, String tel, String adress) {
        super(nom, prenom, dateN, tel, adress);
        setCode(code);
    }
}
