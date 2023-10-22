package com.majidim.easybankv4.easybankv4.HibernateImps;

import com.majidim.easybankv4.easybankv4.HibernateImps.Interfaces.IHEmployer;
import com.majidim.easybankv4.easybankv4.dto.Employe;

import java.util.Optional;

public class EmployeImpl extends HibernateImplementation<Employe, String> implements IHEmployer {
    public Optional<Employe> findByID(String id) {
        return findByID(id, Employe.class);
    }
}