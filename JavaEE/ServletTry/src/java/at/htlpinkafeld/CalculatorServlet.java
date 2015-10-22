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

        String res = "";
        String pluSel = "", minSel = "", mulSel = "", divSel = "";
        String leftO="", rightO="";
        String opS = request.getParameter("operator");
        if(opS!=null)
        try {
            leftO = request.getParameter("leftO");
            Double l;
            if (leftO != null) {
                l = Double.parseDouble(leftO);
            } else {
                l = 1.0;
            }
            rightO = request.getParameter("rightO");
            Double r;
            if (leftO != null) {
                r = Double.parseDouble(rightO) ;
            } else {
                r = 1.0;
            }
            
            switch (opS) {
                case "+":
                    res = "" + (l + r);
                    pluSel = "selected";
                    break;
                case "-":
                    res = "" + (l - r);
                    minSel = "selected";
                    break;
                case "*":
                    res = "" + l * r;
                    mulSel = "selected";
                    break;
                case "/":
                    res = "" + l / r;
                    divSel = "selected";
                    break;
                default:
                    res = "unbekannter Fehler";
            }
        } catch (NumberFormatException e) {
            res = "Invalid Number inputted";
        } catch (Exception e) {
            res = "Error";
            System.out.println(e);
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Calculator</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Calculator</h1>");
            out.println("<form method=\"get\" >\n");
            out.println("<table border=\"0\" width=\"100% \">\n");
            out.println("<colgroup>\n");
            out.println("<col width=\"150\">\n");
            out.println("<col width=\"*\">\n");
            out.println("</colgroup>\n");
            out.println("        	<tr>\n");
            out.println("          	<td align=\"left\">Left operand:</td>\n");
            out.println("          	<td align=\"left\"><input  name=\"leftO\" value=\"" + leftO + "\"></td>\n");
            out.println("        	</tr>\n");
            out.println("        	<tr>\n");
            out.println("          	<td align=\"left\">Right operand:</td>\n");
            out.println("          	<td align=\"left\"><input  name=\"rightO\" value=\"" + rightO + "\"></td>\n");
            out.println("        	</tr>\n");
            out.println("        	<tr>\n");
            out.println("				<td align=\"left\"><input type=\"submit\" value=\"Calculate\"></td>\n");
            out.println("          		<td align=\"left\">\n");
            out.println("					<select name=\"operator\">\n");
            out.println("						<option value=\"+\" " + pluSel + ">+</option>\n");
            out.println("				  		<option value=\"-\" " + minSel + ">-</option>\n");
            out.println("						<option value=\"*\" " + mulSel + ">*</option>\n");
            out.println("						<option value=\"/\" " + divSel + ">/</option>\n");
            out.println("					</select>		  \n");
            out.println("               </td>\n");
            out.println("           </tr>\n");
            out.println("           <tr>\n");
            out.println("                       <td align=\"left\">Result:</td>\n");
            out.println("<td align=\"left\">" + res + "</td>");
            out.println("           </tr>\n");
            out.println(" </table>\n");
            out.println(" </form>");
            out.println("</body>");
            out.println("</html>");
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
