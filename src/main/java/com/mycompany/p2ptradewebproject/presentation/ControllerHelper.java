package com.mycompany.p2ptradewebproject.presentation;

import com.mycompany.p2ptradewebproject.presentation.commands.CommandLogin;
import com.mycompany.p2ptradewebproject.presentation.commands.CommandLogout;
import com.mycompany.p2ptradewebproject.presentation.commands.CommandMissing;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;

public class ControllerHelper {

    private static ControllerHelper instance = null;
    private final HashMap<String, ICommand> commands = new HashMap<>(); //TODO redo to enum (get / post)

    private ControllerHelper() {
        commands.put("login", new CommandLogin());
        commands.put("logout", new CommandLogout());
    }

    public static ControllerHelper getInstance() {
        if (instance == null) {
            instance = new ControllerHelper();
        }
        return instance;
    }

    public ICommand getCommand(HttpServletRequest request) {
        ICommand command = commands.get(request.getParameter("command"));
        if (command == null) {
            command = new CommandMissing();
        }
        return command;
    }
}
