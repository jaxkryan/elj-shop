package controller.seller;

import dao.OrderDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.Order;

/**
 *
 * @author Huy Nguyen
 */
public class SellerHomeController extends HttpServlet {
   
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
        String service = request.getParameter("go");
        if (service == null || service.equals("")) {
            service = "displayAll";
        }
        if (service.equals("displayAll")) {
            OrderDAO orderDAO = new OrderDAO();
            Vector<Order> orders = orderDAO.getAll();
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/jsp/manageOrderPage.jsp").forward(request, response);
        } else if (service.equals("update")) {
            OrderDAO orderDAO = new OrderDAO();
            int orderId = Integer.parseInt(request.getParameter("id"));
            Order orderToUpdate = orderDAO.getById(orderId);
            request.setAttribute("orderToUpdate", orderToUpdate);
            request.getRequestDispatcher("/jsp/manageOrderPage.jsp").forward(request, response);
        }
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
        OrderDAO orderDAO = new OrderDAO();
        if (request.getParameter("sellerSearchCustomerSubmit") != null) {
            String keyword = request.getParameter("keyword");
            Vector<Order> orders = orderDAO.getByName(keyword);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/jsp/manageOrderPage.jsp").forward(request, response);
            }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
