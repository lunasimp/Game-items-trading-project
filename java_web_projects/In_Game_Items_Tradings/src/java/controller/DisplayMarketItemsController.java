/*
*Programmer: Nguyễn Hoàng Hiệp 
*Description: This files is controller for displaying items on the market
 */
package controller;

import Context.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.MarketItemsDao;
import java.util.ArrayList;
import model.MarketItems;

/**
 *
 * @author Inspiron
 */
@WebServlet(name = "MarketItemsController", urlPatterns = {"/DisplayMarketItemsController"})
public class DisplayMarketItemsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        ArrayList<MarketItems> list = MarketItemsDao.getAllMarketItems();
//        request.setAttribute("market_list", list);
        response.sendRedirect("buy.jsp");
//        request.getRequestDispatcher("buy.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
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
