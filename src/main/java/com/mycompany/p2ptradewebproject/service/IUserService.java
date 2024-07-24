package com.mycompany.p2ptradewebproject.service;

import com.mycompany.p2ptradewebproject.persistence.entities.UserEntity;
import com.mycompany.p2ptradewebproject.service.transaction.Transactional;

public interface IUserService {

    @Transactional
    UserEntity login(String username, String password);
    Boolean createUser(String username, String email, String password);
}
