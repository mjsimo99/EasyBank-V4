package com.majidim.easybankv4.easybankv4.interfeces;

import com.majidim.easybankv4.easybankv4.dto.EmployeAgency;

import java.util.List;
import java.util.Optional;

public interface IEmployeAgency {


    Optional<EmployeAgency> Affecter(EmployeAgency employeAgency);

    Optional<EmployeAgency> muter(String oldAgencyCode, String newAgencyCode, EmployeAgency employeAgency);
    List<EmployeAgency> ShowList();


}
