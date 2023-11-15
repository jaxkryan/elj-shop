/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dao.CategoryDAO;
import dao.OrderDAO;
import dao.ProviderDAO;
import dao.UserDAO;
import dao.VoucherDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import model.Category;
import model.Provider;
import model.User;
import model.Voucher;
import util.Helper;

/**
 *
 * @author Admin
 */
public class AddVoucherController extends HttpServlet {

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
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        String voucherCode = request.getParameter("voucherCode") != null ? request.getParameter("voucherCode") : "";
        String remove = request.getParameter("remove") != null ? request.getParameter("remove") : "";
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String orderDate = dateFormat.format(calendar.getTime());
        Float subtotal = Float.parseFloat(request.getParameter("subtotal"));

        if (remove.equals("remove")) {
            OrderDAO odao = new OrderDAO();
            Helper.setNotification(request, "Remove voucher succesfully!", "GREEN");
            UserDAO udao = new UserDAO();
            User user = udao.getById(userId);
            request.setAttribute("user", user);
            request.setAttribute("subtotal", subtotal);
            CategoryDAO categoryDAO = new CategoryDAO();
            ProviderDAO providerDAO = new ProviderDAO();
            Vector<Category> categories = categoryDAO.getAllCategory();
            Vector<Provider> providers = providerDAO.getAllProvider();
            request.setAttribute("categories", categories);
            request.setAttribute("providers", providers);
            request.getRequestDispatcher("/jsp/checkoutPage.jsp").forward(request, response);
            return;
        }
        if (voucherCode.equals("")) {
            OrderDAO odao = new OrderDAO();
            Helper.setNotification(request, "Please enter voucher code!", "RED");
            UserDAO udao = new UserDAO();
            User user = udao.getById(userId);
            request.setAttribute("user", user);
            request.setAttribute("subtotal", subtotal);
            CategoryDAO categoryDAO = new CategoryDAO();
            ProviderDAO providerDAO = new ProviderDAO();
            Vector<Category> categories = categoryDAO.getAllCategory();
            Vector<Provider> providers = providerDAO.getAllProvider();
            request.setAttribute("categories", categories);
            request.setAttribute("providers", providers);
            request.getRequestDispatcher("/jsp/checkoutPage.jsp").forward(request, response);
        } else {
            VoucherDAO vdao = new VoucherDAO();
            Voucher voucher = vdao.getVoucherByVoucherCode(voucherCode);
            if (voucher == null) {
                Helper.setNotification(request, "Voucher do not existed!", "RED");
                UserDAO udao = new UserDAO();
                User user = udao.getById(userId);
                request.setAttribute("user", user);
                request.setAttribute("subtotal", subtotal);
                CategoryDAO categoryDAO = new CategoryDAO();
                ProviderDAO providerDAO = new ProviderDAO();
                Vector<Category> categories = categoryDAO.getAllCategory();
                Vector<Provider> providers = providerDAO.getAllProvider();
                request.setAttribute("categories", categories);
                request.setAttribute("providers", providers);
                request.getRequestDispatcher("/jsp/checkoutPage.jsp").forward(request, response);
            } else {
                try {
                    Date date1 = voucher.getStartDate();
                    Date date2 = dateFormat.parse(orderDate);
                    Date date3 = voucher.getEndDate();
                    if (date1.before(date2) && date2.before(date3)) {
                        OrderDAO odao = new OrderDAO();
                        Helper.setNotification(request, "Using voucher succesfully, you get " + voucher.getValue() + "% discount!", "GREEN");
                        UserDAO udao = new UserDAO();
                        User user = udao.getById(userId);
                        request.setAttribute("user", user);
                        request.setAttribute("subtotal", subtotal);
                        request.setAttribute("voucherCode", voucherCode);
                        request.setAttribute("voucherValue", voucher.getValue());
                        CategoryDAO categoryDAO = new CategoryDAO();
                        ProviderDAO providerDAO = new ProviderDAO();
                        Vector<Category> categories = categoryDAO.getAllCategory();
                        Vector<Provider> providers = providerDAO.getAllProvider();
                        request.setAttribute("categories", categories);
                        request.setAttribute("providers", providers);
                        request.getRequestDispatcher("/jsp/checkoutPage.jsp").forward(request, response);
                    } else {
                        Helper.setNotification(request, "Voucher has expired", "RED");
                        UserDAO udao = new UserDAO();
                        User user = udao.getById(userId);
                        request.setAttribute("user", user);
                        request.setAttribute("subtotal", subtotal);
                        CategoryDAO categoryDAO = new CategoryDAO();
                        ProviderDAO providerDAO = new ProviderDAO();
                        Vector<Category> categories = categoryDAO.getAllCategory();
                        Vector<Provider> providers = providerDAO.getAllProvider();
                        request.setAttribute("categories", categories);
                        request.setAttribute("providers", providers);
                        request.getRequestDispatcher("/jsp/checkoutPage.jsp").forward(request, response);
                    }
                } catch (ParseException e) {
                    Helper.setNotification(request, "ParseException", "RED");
                    UserDAO udao = new UserDAO();
                    User user = udao.getById(userId);
                    request.setAttribute("user", user);
                    request.setAttribute("subtotal", request.getParameter("subtotal"));
                    CategoryDAO categoryDAO = new CategoryDAO();
                    ProviderDAO providerDAO = new ProviderDAO();
                    Vector<Category> categories = categoryDAO.getAllCategory();
                    Vector<Provider> providers = providerDAO.getAllProvider();
                    request.setAttribute("categories", categories);
                    request.setAttribute("providers", providers);
                    request.getRequestDispatcher("/jsp/checkoutPage.jsp").forward(request, response);
                }
            }
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
