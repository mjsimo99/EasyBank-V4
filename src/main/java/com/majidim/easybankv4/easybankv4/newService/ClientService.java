package com.majidim.easybankv4.easybankv4.newService;

import com.majidim.easybankv4.easybankv4.HibernateImps.ClientImpl;
import com.majidim.easybankv4.easybankv4.dto.Client;

import java.util.Optional;

public class ClientService {
    private final ClientImpl clientImpl;

    public ClientService(ClientImpl clientImpl) {
        this.clientImpl =  clientImpl;
    }

    public Optional<Client> findByCode(String code) {
        return clientImpl.findByID(code);
    }
/*
    public boolean Delete(String code) {
        return clientImpl.Delete(code);
    }

    public List<Client> Showlist() {
        return clientImpl.Showlist();
    }

    public Optional<Client> Update(Client client) {
        return clientImpl.Update(client);
    }

    public Optional<Personne> Add(Personne personne) {
        return clientImpl.Add(personne);
    }

    public List<Client> SearchByMatricule(String code) {
        return clientImpl.SearchByMatricule(code);
    }*/

}


