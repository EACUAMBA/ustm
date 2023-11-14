package com.eacuamba.dev.web;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/")
public class UstmFlix extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mensagem = "Olá a partir do servlet!";
        request.setAttribute("mensagem", mensagem);
        request.getRequestDispatcher("/meujsp.jsp").forward(request, response);
    }
}
