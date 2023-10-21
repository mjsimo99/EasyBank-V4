package com.majidim.easybankv4.easybankv4.interfeces;


import com.majidim.easybankv4.easybankv4.dto.Operation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IOperation {
    Optional<Operation> Add(Operation operation);
    List<Operation> SearchByNumber(String numero);
    boolean Delete(String numero);
    List<Operation> SearchByCreationDate(LocalDate creationDate);

}
