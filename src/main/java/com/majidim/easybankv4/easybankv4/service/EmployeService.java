package com.majidim.easybankv4.easybankv4.service;

import com.majidim.easybankv4.easybankv4.dto.Employe;
import com.majidim.easybankv4.easybankv4.dto.Personne;
import com.majidim.easybankv4.easybankv4.implementation.EmployeImpl;

import java.util.List;
import java.util.Optional;

public class EmployeService {
    private final EmployeImpl employeImpl;

    public EmployeService(EmployeImpl employeImpl) {
        this.employeImpl = employeImpl;
    }
    public List<Employe> SearchByMatricule(String matricule){
        return employeImpl.SearchByMatricule(matricule);
    }
    public boolean Delete(String marticule){
        return employeImpl.Delete(marticule);
    }
    public List<Employe> ShowList(){
        return employeImpl.ShowList();
    }
    public List<Employe> SearchByEmail(String emailAdresse){
        return employeImpl.SearchByEmail(emailAdresse);
    }
    public Optional<Employe> Update(Employe employe){
        return employeImpl.Update(employe);
    }
    public Optional<Personne> add(Personne personne) {
        return employeImpl.Add(personne);
    }

}
