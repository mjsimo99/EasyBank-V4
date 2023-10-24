package com.majidim.easybankv4.easybankv4.HibernateImps;

import com.majidim.easybankv4.easybankv4.HibernateImps.Interfaces.IHClient;
import com.majidim.easybankv4.easybankv4.dto.Client;

import java.util.List;
import java.util.Optional;

public class ClientImpl extends HibernateImplementation<Client, String > implements IHClient {
    public Optional<Client> findByID(String id){
        return findByID(id, Client.class);
    }
    public List<Client> getAll(){
        return getAll(Client.class);
    }
}
