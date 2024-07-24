package com.mycompany.p2ptradewebproject.presentation.commands;

import com.mycompany.p2ptradewebproject.presentation.ICommand;
import com.mycompany.p2ptradewebproject.presentation.manager.PageProperties;
import com.mycompany.p2ptradewebproject.service.IUserService;
import com.mycompany.p2ptradewebproject.service.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CommandRegister implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        IUserService userService = ServiceFactory.createUserService();
        userService.createUser(username, email, password); //TODO Validation & creation verification & duplicate check

        return PageProperties.getInstance().getProperty(PageProperties.MAIN_PAGE_PATH);
    }
}
