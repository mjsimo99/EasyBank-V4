package com.majidim.easybankv4.easybankv4.interfeces;


import com.majidim.easybankv4.easybankv4.dto.Personne;

import java.util.Optional;

public interface IPersonne {
    Optional<Personne> Add(Personne personne);
}


