package com.mycompany.p2ptradewebproject.persistence.jdbc.interfaces;

import com.mycompany.p2ptradewebproject.persistence.entities.UserEntity;

import java.util.Optional;


public interface IDAOUser extends IGenericDAO<UserEntity> {
    Optional<UserEntity> findByUsernamePassword(String username, String password);
}
