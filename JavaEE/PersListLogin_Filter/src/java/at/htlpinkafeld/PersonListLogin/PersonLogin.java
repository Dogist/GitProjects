/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.PersonListLogin;

import at.htlpinkafeld.utils.PLProperties;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Martin Six
 */
@WebServlet(name = "PersonLogin", urlPatterns = {"/PersonLogin"})
public class PersonLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");
        PLProperties plp = PLProperties.getInstance();
        String[] users = plp.getStringProperty(PLProperties.USER).split(";");
        Boolean userexist = false;
        for (int i = 0; i < users.length; i++) {
            if (users[i].contentEquals(name)) {
                userexist = true;
                request.getSession(true).setAttribute("UserLoggedIn", users[i]);
            }
        }
        PrintWriter out = response.getWriter();
        try {
            if (userexist) {
                ServletContext sc = this.getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher("/PersonListLogin.html");
                out.println("Welcome " + name);
                rd.include(request, response);
            } else {
                ServletContext sc = this.getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher("/PersonLogin.html");
                out.println("<font color=" + plp.getStringProperty(PLProperties.MSGCOLOR) + ">either user name or password is incorrect</font>");
                rd.include(request, response);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
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
