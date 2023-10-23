package com.majidim.easybankv4.easybankv4.service;

import com.majidim.easybankv4.easybankv4.dto.*;
import com.majidim.easybankv4.easybankv4.dto.DemendeCredit;
import com.majidim.easybankv4.easybankv4.implementation.DemandeCreditImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DemandeCreditService {
    private final DemandeCreditImpl creditImpl;

    public DemandeCreditService(DemandeCreditImpl creditImpl) {
        this.creditImpl = creditImpl;
    }

    public Optional<DemendeCredit> create(DemendeCredit demendeCredit) {
        return creditImpl.Add(demendeCredit);
    }


   public List<DemendeCredit> ShowList() {
        return creditImpl.ShowList();
    }

    public List<DemendeCredit> SearchByStatus(CreditStatus status) {
        return creditImpl.SearchByStatus(status);
    }

    public List<DemendeCredit> SearchByDate(LocalDate date) {
        return creditImpl.SearchBydate(date);
    }

    public List<DemendeCredit> SearchByCode(String numero) {
        return creditImpl.SearchByCode(numero);
    }

    public Optional<DemendeCredit> UpdateStatus(DemendeCredit demendeCredit) {
        return creditImpl.UpdateStatus(demendeCredit);
    }
}
