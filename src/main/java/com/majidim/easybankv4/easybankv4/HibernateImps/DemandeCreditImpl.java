package com.majidim.easybankv4.easybankv4.HibernateImps;

import com.majidim.easybankv4.easybankv4.dto.DemendeCredit;
import com.majidim.easybankv4.easybankv4.HibernateImps.Interfaces.IHDemandeCredit;

import java.util.Optional;

public class DemandeCreditImpl extends HibernateImplementation<DemendeCredit , String> implements IHDemandeCredit {
    public Optional<DemendeCredit> findByID(String code) {
        return findByID(code, DemendeCredit.class);
    }
}
