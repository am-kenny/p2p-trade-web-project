package com.mycompany.p2ptradewebproject.service;

import com.mycompany.p2ptradewebproject.persistence.entities.UserEntity;
import com.mycompany.p2ptradewebproject.persistence.jdbc.interfaces.IDAOUser;
import com.mycompany.p2ptradewebproject.service.transaction.Transactional;

import java.util.Optional;

public class UserService implements IUserService {

    private static UserService instance = null;

    private IDAOUser daoUser;

    private UserService(IDAOUser daoUser) {
        this.daoUser = daoUser;
    }

    public static synchronized UserService getInstance(IDAOUser daoUser) {
        if (instance == null) {
            instance = new UserService(daoUser);
        }
        return instance;
    }

    @Override
    @Transactional
    public UserEntity login(String username, String password) {
        Optional<UserEntity> user = daoUser.findByUsernamePassword(username, password);
        return user.orElse(null);
    }

    @Transactional
    public Boolean createUser(String username, String email, String password) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        daoUser.create(user);
        return true;
    }

}
