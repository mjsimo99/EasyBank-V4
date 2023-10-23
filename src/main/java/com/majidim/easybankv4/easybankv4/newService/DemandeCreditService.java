package com.majidim.easybankv4.easybankv4.newService;

import com.majidim.easybankv4.easybankv4.HibernateImps.DemandeCreditImpl;
import com.majidim.easybankv4.easybankv4.dto.DemendeCredit;

import java.util.Optional;

public class DemandeCreditService {
    private final DemandeCreditImpl creditImpl;

    public DemandeCreditService(DemandeCreditImpl creditImpl) {
        this.creditImpl = creditImpl;
    }

    public Optional<DemendeCredit> create(DemendeCredit demendeCredit) {
        System.out.println("numero"+demendeCredit.getNumero());
        System.out.println("remarque"+demendeCredit.getRemarque());
        System.out.println("status"+demendeCredit.getStatus());
        return creditImpl.create(demendeCredit);
    }

    public Optional<DemendeCredit> findByCode(String code) {
        return creditImpl.findByID(code);
    }

/*    public List<DemendeCredit> ShowList() {
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
    }*/
}
