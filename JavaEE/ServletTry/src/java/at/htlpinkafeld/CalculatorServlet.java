/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Martin Six
 */
@WebServlet(name = "CalculatorServlet", urlPatterns = {"/CalculatorServlet"})
public class CalculatorServlet extends HttpServlet {

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

        Double res = 0.0;
        String leftO = "", rightO = "";
        String opS = request.getParameter("operator");
        if (opS != null)
        {
            try {
                leftO = request.getParameter("leftO");
                Double l;
                if (leftO != null) {
                    l = Double.parseDouble(leftO);
                } else {
                    l = 0.0;
                }
                rightO = request.getParameter("rightO");
                Double r;
                if (rightO != null) {
                    r = Double.parseDouble(rightO);
                } else {
                    r = 0.0;
                }

                switch (opS) {
                    case "+":
                        res =  (l + r);
//                        pluSel = "selected";
                        break;
                    case "-":
                        res =  (l - r);
//                        minSel = "selected";
                        break;
                    case "*":
                        res =  l * r;
//                        mulSel = "selected";
                        break;
                    case "/":
                        res =  l / r;
//                        divSel = "selected";
                        break;
                    default:
                        res = 0.0;
                }
            } catch (NumberFormatException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        try (PrintWriter out = response.getWriter()) {

            out.println(res);

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
