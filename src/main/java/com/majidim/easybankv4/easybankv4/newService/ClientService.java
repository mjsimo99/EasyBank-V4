package com.majidim.easybankv4.easybankv4.newService;

import com.majidim.easybankv4.easybankv4.HibernateImps.ClientImpl;
import com.majidim.easybankv4.easybankv4.dto.Client;
import com.majidim.easybankv4.easybankv4.dto.Personne;

import java.util.Optional;

public class ClientService {
    private final ClientImpl clientImpl;

    public ClientService(ClientImpl clientImpl) {
        System.out.println("client service constructor");
        this.clientImpl =  clientImpl;
    }

    public Optional<Client> create(Client client) {
        System.out.println("from create");
        return clientImpl.create(client);
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


    public List<Client> SearchByMatricule(String code) {
        return clientImpl.SearchByMatricule(code);
    }*/

}


