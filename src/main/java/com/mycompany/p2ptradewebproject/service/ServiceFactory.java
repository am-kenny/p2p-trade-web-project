package com.mycompany.p2ptradewebproject.service;

import com.mycompany.p2ptradewebproject.persistence.jdbc.factory.MySqlDAOFactory;

public class ServiceFactory {
    public static IUserService createUserService() {
        return UserService.getInstance(MySqlDAOFactory.getMySqlDAOFactory().getUserDAO());
    }
}
