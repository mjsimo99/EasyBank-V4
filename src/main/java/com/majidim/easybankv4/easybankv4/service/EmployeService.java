package com.majidim.easybankv4.easybankv4.service;

import com.majidim.easybankv4.easybankv4.dto.Employe;
import com.majidim.easybankv4.easybankv4.dto.Personne;
import com.majidim.easybankv4.easybankv4.HibernateImps.EmployeImpl;

import java.util.List;
import java.util.Optional;

public class EmployeService {
    private final EmployeImpl employeImpl;

    public EmployeService(EmployeImpl employeImpl) {
        this.employeImpl = employeImpl;
    }
    public Optional<Employe> SearchByMatricule(String matricule){
        return employeImpl.findByID(matricule);
    }
    public boolean Delete(String marticule){
        return employeImpl.delete(marticule);
    }
    public List<Employe> ShowList(){
        return employeImpl.getAll();
    }
    /*public List<Employe> SearchByEmail(String emailAdresse){
        return employeImpl.SearchByEmail(emailAdresse);
    }*/
    public Optional<Employe> Update(Employe employe){
        return employeImpl.update(employe);
    }
    public Optional<Employe> add(Employe employe) {
        return employeImpl.create(employe);
    }

}
