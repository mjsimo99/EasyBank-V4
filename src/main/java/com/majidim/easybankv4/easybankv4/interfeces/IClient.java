package com.majidim.easybankv4.easybankv4.interfeces;

import com.majidim.easybankv4.easybankv4.dto.Client;
import com.majidim.easybankv4.easybankv4.dto.Personne;

import java.util.List;
import java.util.Optional;

public interface IClient extends IPersonne {
    List<Client> SearchByCode(String code);
    boolean Delete(String code);
    List<Client> Showlist();
    Optional<Client> Update(Client client);
    List<Client> SearchByMatricule(String code);

}
