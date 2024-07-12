package com.mycompany.p2ptradewebproject.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class Counter {
    public static Integer updateCounter(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer visitCounter = (Integer) session.getAttribute("visitCounter");
        if (visitCounter == null) {
            visitCounter = 1;
        } else {
            visitCounter++;
        }
        session.setAttribute("visitCounter", visitCounter);

        return visitCounter;
    }
}
