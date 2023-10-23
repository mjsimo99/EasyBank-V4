package com.majidim.easybankv4.easybankv4.service;

import com.majidim.easybankv4.easybankv4.dto.Client;
import com.majidim.easybankv4.easybankv4.dto.Employe;
import com.majidim.easybankv4.easybankv4.dto.Personne;
import com.majidim.easybankv4.easybankv4.HibernateImps.ClientImpl;
import java.util.List;
import java.util.Optional;

public class ClientService {
    private final ClientImpl clientImpl;

    public ClientService(ClientImpl clientImpl) {
        this.clientImpl =  clientImpl;
    }

    public Optional<Client> SearchByCode(String code) {
        return clientImpl.findByID(code);
    }

    public boolean Delete(String code) {
        return clientImpl.delete(code);
    }

    public List<Client> Showlist() {
        return clientImpl.getAll();
    }

    public Optional<Client> Update(Client client) {
        return clientImpl.update(client);
    }

    public Optional<Client> Add(Client client) {
        return clientImpl.create(client);
    }


}


