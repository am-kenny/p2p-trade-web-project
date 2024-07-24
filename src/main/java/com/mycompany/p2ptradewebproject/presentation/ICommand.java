package com.mycompany.p2ptradewebproject.presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ICommand {
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
