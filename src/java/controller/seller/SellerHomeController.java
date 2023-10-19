package controller.seller;

import dao.OrderDAO;
import dao.OrderDetailDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import model.Order;
import model.OrderDetail;
import util.Helper;

/**
 *
 * @author Huy Nguyen
 */
public class SellerHomeController extends HttpServlet {

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
        OrderDAO orderDAO = new OrderDAO();
        if (service == null || service.equals("")) {
            service = "displayAll";
        }
        if (service.equals("displayAll")) {
            Vector<Order> orders = orderDAO.GetSellerManageOrder();
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/jsp/manageOrderPage.jsp").forward(request, response);
//        } else if (service.equals("delete")) {
//            int oid = Integer.parseInt(request.getParameter("id"));
//            Order deleteOrder = orderDAO.getById(oid);
//            int checkDelete = orderDAO.deleteOrder(oid);
//            if (checkDelete != 0) {
//                Helper.setNotification(request, "Delete Order by " + deleteOrder.getReceiver() + " successfully!", "GREEN");
//            } else {
//                Helper.setNotification(request, "Delete product " + deleteOrder.getReceiver() + " fail!", "RED");
//            }
//            response.sendRedirect("home");
//        } else if (service.equals("getEditOrder")) {
//            int orderId = Integer.parseInt(request.getParameter("id"));
//            Order updateOrder = orderDAO.getById(orderId);
//
//            String[] arr = {"Processed", "Accepted", "Shipped", "Received", "Canceled"};
//            List<String> status = Arrays.asList(arr);
//            request.setAttribute("status", status);
//            request.setAttribute("updateOrder", updateOrder);
//            request.getRequestDispatcher("/jsp/updateOrderPage.jsp").forward(request, response);
        } else if (service.equals("changeOrderStatus")) {
            int orderId = Integer.parseInt(request.getParameter("id"));
            String newStatus = request.getParameter("newStatus");
            Order getOrder = orderDAO.getById(orderId);
            int checkStatus = orderDAO.changeOrderStatus(orderId, newStatus);
            if (checkStatus != 0) {
                Helper.setNotification(request, "Cancelled " + getOrder.getReceiver() + "'s order successfully!", "GREEN");
            }
            response.sendRedirect("home");
        } else if (service.equals("updateStatus")) {
            OrderDetailDAO orderDetailsDAO = new OrderDetailDAO();
            int orderId = Integer.parseInt(request.getParameter("id"));
            Vector<OrderDetail> orders = orderDetailsDAO.CheckOrdersQuantity(orderId);
            if (orders.isEmpty()) {
                String newStatus = request.getParameter("newStatus");
                orderDetailsDAO.updateProductQuantity(orderId);
                Order getOrder = orderDAO.getById(orderId);
                int checkStatus = orderDAO.changeOrderStatus(orderId, newStatus);
                if (checkStatus != 0) {
                    Helper.setNotification(request, "Accepted " + getOrder.getReceiver() + "'s order successfully!", "GREEN");
                }
                response.sendRedirect("home");
            } else {
                Helper.setNotification(request, "Out of stock , can not accept", "RED");
                response.sendRedirect("home");
            }
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
        if (request.getParameter("sellerSearchCustomerSubmit") != null) {
            String keyword = request.getParameter("keyword");
            Vector<Order> orders = orderDAO.getByName(keyword);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/jsp/manageOrderPage.jsp").forward(request, response);
//        } else if (service.equals("update")) {
//            int id = Integer.parseInt(request.getParameter("id"));
//            int customerId = Integer.parseInt(request.getParameter("customerId"));
//            String receiver = request.getParameter("receiver");
//            String shipStreet = request.getParameter("shipStreet");
//            String shipCity = request.getParameter("shipCity");
//            String shipProvince = request.getParameter("shipProvince");
//            String shipCountry = request.getParameter("shipCountry");
//            String shipEmail = request.getParameter("shipEmail");
//            String shipPhone = request.getParameter("shipPhone");
//            String status = request.getParameter("status");
//            String createdTime = request.getParameter("createdTime");
//
//            float totalPrice = Float.parseFloat(request.getParameter("totalprice"));
//
//            Order updateOrder = new Order(id, customerId, receiver, shipStreet,
//                    shipCity, shipProvince, shipCountry, shipEmail, shipPhone,
//                    status, createdTime, totalPrice, true);
//            orderDAO.updateOrderInfo(updateOrder);
//
//            response.sendRedirect("home");
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
    }

}
