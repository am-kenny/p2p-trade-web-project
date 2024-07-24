package com.mycompany.p2ptradewebproject.presentation.commands;

import com.mycompany.p2ptradewebproject.persistence.entities.UserEntity;
import com.mycompany.p2ptradewebproject.presentation.ICommand;
import com.mycompany.p2ptradewebproject.presentation.manager.MessageProperties;
import com.mycompany.p2ptradewebproject.presentation.manager.PageProperties;
import com.mycompany.p2ptradewebproject.service.IUserService;
import com.mycompany.p2ptradewebproject.service.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

public class CommandLogin implements ICommand {
    private static final String LOGIN = "username";
    private static final String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            return PageProperties.getInstance().getProperty(PageProperties.LOGIN_PAGE_PATH);
        }

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        IUserService userService = ServiceFactory.createUserService();
        UserEntity user = userService.login(login, password);

        if (!Objects.isNull(user)) {
            request.getSession().setAttribute("userId", user.getId());
            request.getSession().setAttribute("username", user.getUsername());
            return PageProperties.getInstance().getProperty(PageProperties.MAIN_PAGE_PATH);
        }

        request.setAttribute("errorMessage", MessageProperties.getInstance().getProperty(MessageProperties.LOGIN_ERROR));
        return PageProperties.getInstance().getProperty(PageProperties.LOGIN_PAGE_PATH);
    }
}
