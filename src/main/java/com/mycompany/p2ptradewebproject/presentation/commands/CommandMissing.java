package com.mycompany.p2ptradewebproject.presentation.commands;

import com.mycompany.p2ptradewebproject.presentation.ICommand;
import com.mycompany.p2ptradewebproject.presentation.manager.PageProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CommandMissing implements ICommand {
        @Override
        public String execute(HttpServletRequest request, HttpServletResponse response) {
            return PageProperties.getInstance().getProperty(PageProperties.MAIN_PAGE_PATH);
        }
}
