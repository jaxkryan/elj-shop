package controller.seller;

import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProviderDAO;
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
import model.Provider;
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
            ProviderDAO providerDAO = new ProviderDAO();
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
            Vector<Order> orders = new Vector<>();

            if (service == null || service.equals("")) {
                service = "displayAll";
            }
            if (service.equals("displayAll")) {
                orders = orderDAO.GetSellerManageOrder();
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("/jsp/manageOrderPage.jsp").forward(request, response);
            } else if (service.equals("viewOrderDetails")) {
                int orderId = Integer.parseInt(request.getParameter("id"));
                Vector<OrderDetail> orderdetails = orderDetailDAO.getById(orderId);
                request.setAttribute("orderdetails", orderdetails);
                request.getRequestDispatcher("/jsp/manageOrderDetailPage.jsp").forward(request, response);
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
                Vector<OrderDetail> orderdetails = orderDetailsDAO.CheckOrdersQuantity(orderId);
                if (orderdetails.isEmpty()) {
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
            } else if (service.equals("filter")) {
                String sortType = request.getParameter("sortType") != null ? request.getParameter("sortType") : "";
                String statusFilter = request.getParameter("statusFilter") == null ? "" : request.getParameter("statusFilter");
                String searchName = request.getParameter("searchName") == null ? "" : request.getParameter("searchName");
                
                orders = orderDAO.filterOrders(statusFilter, searchName, sortType);
                
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("/jsp/manageOrderPage.jsp").forward(request, response);
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
        
            () {
        return "Short description";
        }

    }
