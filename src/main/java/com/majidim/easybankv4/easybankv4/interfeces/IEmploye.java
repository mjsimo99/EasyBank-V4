package com.majidim.easybankv4.easybankv4.interfeces;


import com.majidim.easybankv4.easybankv4.dto.Employe;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IEmploye extends IPersonne {
    List<Employe> SearchByMatricule(String matricule);
    boolean Delete(String marticule);
    List<Employe> ShowList();
    List<Employe> SearchByEmail(String emailAdresse);
    Optional<Employe> Update(Employe employe);

}
