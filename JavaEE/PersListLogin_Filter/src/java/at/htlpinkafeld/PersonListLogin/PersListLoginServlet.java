/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.PersonListLogin;

import at.htlpinkafeld.pojo.Person;
import at.htlpinkafeld.utils.PLProperties;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Martin Six
 */
@WebServlet(name = "PersListLoginServlet", urlPatterns = {"/PersListLoginServlet"})
public class PersListLoginServlet extends HttpServlet {

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
        HttpSession ses = request.getSession(true);
        List<Person> persL = (List<Person>) ses.getAttribute("list");
        PLProperties plp = PLProperties.getInstance();
        
        if (persL == null) {
            persL = new ArrayList<>();
        }
        String par = request.getParameter("nr");
        String name = request.getParameter("name");
        if (par != null && name != null && persL.size()<plp.getIntProperty(PLProperties.MAXPERSON)) {
            try {
                int nr = Integer.parseInt(par);
                Person p = new Person(nr, name);
                persL.add(p);

            } catch (NumberFormatException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        ses.setAttribute("list", persL);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<table border=\"1\">");
            out.println("<tr>");
            out.println("<td><font color="+plp.getStringProperty(PLProperties.COLUMNHEADINGCOLOR)+">Nr</font></td>");
            out.println("<td><font color="+plp.getStringProperty(PLProperties.COLUMNHEADINGCOLOR)+">Name</font></td>");
            out.println("</tr>");
            for (Person p : persL) {
                out.println("<tr>");
                out.println("<td>"+p.getNr()+"</td>");
                out.println("<td>"+p.getName()+"</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }
        catch(Exception e){
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
