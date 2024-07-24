package com.mycompany.p2ptradewebproject.service;

import com.mycompany.p2ptradewebproject.persistence.connection.EDatabaseType;
import com.mycompany.p2ptradewebproject.persistence.jdbc.factory.AbstractDAOFactory;

public class ServiceFactory {
    public static IUserService createUserService() {
        return UserService.getInstance(AbstractDAOFactory.createDAOFactory(EDatabaseType.MYSQL).createUserDAO());
    }
}
