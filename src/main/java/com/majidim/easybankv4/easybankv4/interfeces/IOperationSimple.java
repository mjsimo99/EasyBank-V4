package com.majidim.easybankv4.easybankv4.interfeces;

import com.majidim.easybankv4.easybankv4.dto.*;

import java.util.List;

public interface IOperationSimple extends IOperation{
    List<Operation> SearchByType(TypeOperation type);

}
