package com.majidim.easybankv4.easybankv4.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
@Entity
@Table(name = "Clients")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Client extends Personne {
    @Id
    @Column(name = "code")
    private String code;

    public Client(String code, String nom, String prenom, LocalDate dateN, String tel, String address) {
        super(nom, prenom, dateN, tel, address);
        setCode(code);
    }
}
