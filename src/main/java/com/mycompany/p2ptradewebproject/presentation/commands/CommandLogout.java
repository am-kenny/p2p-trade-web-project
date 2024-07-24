package com.mycompany.p2ptradewebproject.presentation.commands;

import com.mycompany.p2ptradewebproject.presentation.ICommand;
import com.mycompany.p2ptradewebproject.presentation.manager.PageProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CommandLogout implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return PageProperties.getInstance().getProperty(PageProperties.LOGIN_PAGE_PATH);
    }
}
