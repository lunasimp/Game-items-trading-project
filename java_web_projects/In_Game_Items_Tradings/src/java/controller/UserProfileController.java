/*
*Programmer: Ly The Luong
*Description: This files is controller for displaying user information
 */

package controller;

import dao.GameAccountDAO;
import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import model.GameAccount;
import model.User;

/**
 *
 * @author ACER
 */
@WebServlet(name="UserProfileController", urlPatterns={"/UserProfileController"})
public class UserProfileController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        if (session.getAttribute("username") == null){
            response.sendRedirect("login.jsp");
            session.setAttribute("mess", "Ban chua co tai khoan vui long dang nhap hoac dang ky");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
        HttpSession session = request.getSession();
        GameAccount ga = (GameAccount) request.getSession().getAttribute("id");
        GameAccountDAO gad = new GameAccountDAO();
        session.setAttribute("infor", gad.GetUserInformation(ga.getId()));
        User u = (User) request.getSession().getAttribute("id");
        UserDAO ud = new UserDAO();
        session.setAttribute("umoney",ud.getUserMoney(u.getId()));      
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
