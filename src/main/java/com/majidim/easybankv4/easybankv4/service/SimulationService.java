package com.majidim.easybankv4.easybankv4.service;

import com.majidim.easybankv4.easybankv4.dto.DemendeCredit;
import com.majidim.easybankv4.easybankv4.HibernateImps.SimulationImp;

import java.util.Optional;

public class SimulationService {
    private final SimulationImp emp;

    public SimulationService(SimulationImp emp){
        this.emp = emp;
    }
    public Optional<DemendeCredit> create(DemendeCredit demendeCredit) {

        return emp.create(demendeCredit);
    }
}
