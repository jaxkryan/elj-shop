/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dao.OrderDAO;
import dao.OrderDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import model.Order;
import util.Helper;

/**
 *
 * @author admin
 */
public class ManageOrderController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManageOrderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageOrderController at " + request.getContextPath() + "</h1>");
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
        String service = request.getParameter("go");
        if (service == null || service.equals("")) {
            service = "displayAll";
        }
        if (service.equals("displayAll")) {
            OrderDAO orderDAO = new OrderDAO();
            Vector<Order> orders = orderDAO.GetManagerManageOrder();
            request.setAttribute("orders", orders);
            String[] arr = {"Processed", "Accepted", "Shipped", "Received", "Canceled"};
            List<String> status = Arrays.asList(arr);
            request.setAttribute("status", status);
            request.getRequestDispatcher("/jsp/ManagerManageOrderPage.jsp").forward(request, response);
        } else if (service.equals("delete")) {
            OrderDAO orderDAO = new OrderDAO();
            int oid = Integer.parseInt(request.getParameter("id"));
            Order deleteOrder = orderDAO.getById(oid);
            int checkDelete = orderDAO.deleteOrder(oid);
            if (checkDelete != 0) {
                Helper.setNotification(request, "Delete Order by " + deleteOrder.getReceiver() + " successfully!", "GREEN");
            } else {
                Helper.setNotification(request, "Delete product " + deleteOrder.getReceiver() + " fail!", "RED");
            }
            response.sendRedirect("order");
        } else if (service.equals("getEditOrder")) {
            OrderDAO orderDAO = new OrderDAO();
            int orderId = Integer.parseInt(request.getParameter("id"));
            Order updateOrder = orderDAO.getById(orderId);
            String[] arr = {"Processed", "Accepted", "Shipped", "Received", "Canceled"};
            List<String> status = Arrays.asList(arr);
            request.setAttribute("status", status);
            request.setAttribute("updateOrder", updateOrder);
            request.getRequestDispatcher("/jsp/updateOrderPage.jsp").forward(request, response);
        } else if (service.equals("changeOrderStatus")) {
            int orderId = Integer.parseInt(request.getParameter("id"));
            String newStatus = request.getParameter("newStatus");
            OrderDAO orderDAO = new OrderDAO();
            Order changeStatusOrder = orderDAO.getById(orderId);
            int checkStatusChange = orderDAO.changeOrderStatus(orderId, newStatus);
            if (newStatus.equals("Cancelled")) {
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                orderDetailDAO.increaseProductQuantity(orderId);
            }
            if (checkStatusChange != 0) {
                //Update success notification
                Helper.setNotification(request, "Order status changed to " + newStatus + " successfully for " + changeStatusOrder.getReceiver() + "'s order!", "GREEN");
            } else {
                //Update fail notification
                Helper.setNotification(request, "Failed to change order status to " + newStatus + " for " + changeStatusOrder.getReceiver() + "'s order. Please try again.", "RED");
            }
            response.sendRedirect("order");
        } else if (service.equals("sort")) {
            String searchName = request.getParameter("searchName") != null ? request.getParameter("searchName") : "";
            OrderDAO orderDAO = new OrderDAO();

            String sortType = request.getParameter("sortType");
            Vector<Order> orders = orderDAO.getSortedShippedOrdersByName(sortType, searchName);
            System.out.println(searchName);
            System.out.println(sortType);
            if (!orders.isEmpty()) {
                System.out.println(orders.firstElement().getReceiver());
            }
            request.setAttribute("searchName", searchName);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/jsp/ManagerManageOrderPage.jsp").forward(request, response);
        }
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
        OrderDAO orderDAO = new OrderDAO();
        String service = request.getParameter("go");
        String searchName = request.getParameter("searchName") != null ? request.getParameter("searchName") : "";
        request.setAttribute("searchName", searchName);
        if (service.equals("search")) {
            Vector<Order> orders = orderDAO.getShippedOrdersByName(searchName);
            System.out.println(searchName);
//            System.out.println(orders.firstElement().getReceiver());
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/jsp/ManagerManageOrderPage.jsp").forward(request, response);
        }

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
