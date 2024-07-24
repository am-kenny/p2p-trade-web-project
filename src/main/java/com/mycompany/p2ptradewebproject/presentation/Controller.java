/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.p2ptradewebproject.presentation;

import java.io.IOException;
import java.util.logging.Logger;

import com.mycompany.p2ptradewebproject.presentation.manager.MessageProperties;
import com.mycompany.p2ptradewebproject.presentation.manager.PageProperties;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Andre
 */
public class Controller extends HttpServlet {

    private final ControllerHelper controllerHelper = ControllerHelper.getInstance();
    private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String page = null;

        try  {
            ICommand command = controllerHelper.getCommand(request);
            page = command.execute(request, response);
        } catch (ServletException e) {
            LOGGER.severe(e.getMessage());
            request.setAttribute("messageError", MessageProperties.getInstance().getProperty(MessageProperties.SERVLET_EXCEPTION));
            page = PageProperties.getInstance().getProperty(PageProperties.ERROR_PAGE_PATH);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
            request.setAttribute("messageError", MessageProperties.getInstance().getProperty(MessageProperties.IO_EXCEPTION));
            page = PageProperties.getInstance().getProperty(PageProperties.ERROR_PAGE_PATH);
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
