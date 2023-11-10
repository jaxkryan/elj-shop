/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import constant.IConstant;
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
import model.User;
import util.Helper;
import java.util.Calendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CartItem;
import model.Voucher;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Category;
import model.Provider;

/**
 *
 * @author Admin
 */
public class CreateOrderController extends HttpServlet {

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
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String receiver = lastName + " " + firstName;
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String province = request.getParameter("province");
        String country = request.getParameter("country");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Float subtotal = Float.parseFloat(request.getParameter("subtotal"));
        float checktotal = 0;
        Vector<CartItem> cartItem = (Vector<CartItem>) session.getAttribute("cartItem");
        for (CartItem item : cartItem) {
            checktotal += item.getPrice() * item.getQuantity();
        }
        if (subtotal != checktotal) {
            Helper.setNotification(request, "Some error occurred", "RED");
            response.sendRedirect("home");
            return;
        }
        double orderPrice = subtotal * 1.1;
        if ((orderPrice * 10) % 10 >= 5) {
            orderPrice = Math.ceil(orderPrice);
        } else {
            orderPrice = Math.floor(orderPrice);
        }
        System.out.println("sub: " + subtotal +", orderPrice: " + orderPrice);
        String voucherCode = request.getParameter("voucherCode") != null ? request.getParameter("voucherCode") : "";
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String orderDate = dateFormat.format(calendar.getTime());

        if (!firstName.matches(IConstant.REGEX_FIRSTNAME)) {
            Helper.setNotification(request, "Please enter valid First Name!", "RED");
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
        if (!lastName.matches(IConstant.REGEX_LASTNAME)) {
            Helper.setNotification(request, "Please enter valid Last Name!", "RED");
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
        if (!street.matches(IConstant.REGEX_STREET)) {
            Helper.setNotification(request, "Please enter valid Street!", "RED");
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
        if (!city.matches(IConstant.REGEX_CITY)) {
            Helper.setNotification(request, "Please enter valid City!", "RED");
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
        if (!province.matches(IConstant.REGEX_PROVINCE)) {
            Helper.setNotification(request, "Please enter valid Province!", "RED");
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
        if (!country.matches(IConstant.REGEX_COUNTRY)) {
            Helper.setNotification(request, "Please enter valid Country!", "RED");
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
        if (!email.matches(IConstant.REGEX_EMAIL)) {
            Helper.setNotification(request, "Please enter valid Email!", "RED");
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
        if (!phone.matches(IConstant.REGEX_PHONE)) {
            Helper.setNotification(request, "Please enter valid Phone!", "RED");
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
            odao.createOrderWithoutVoucher(userId, receiver, street, city, province, country, email, phone, orderDate, orderPrice, cartItem);
            cartItem = new Vector<>();
            session.setAttribute("cartItem", cartItem);
            Helper.setNotification(request, "Place order succesfully!", "GREEN");
            response.sendRedirect("home");
        } else {
            VoucherDAO vdao = new VoucherDAO();
            Voucher voucher = vdao.getVoucherByVoucherCode(voucherCode);
            if (voucher == null) {
                Helper.setNotification(request, "Voucher code is not exist!", "RED");
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
                        odao.createOrderWithVoucher(userId, voucher, receiver, street, city, province, country, email, phone, orderDate, orderPrice, cartItem);
                        cartItem = new Vector<>();
                        session.setAttribute("cartItem", cartItem);
                        Helper.setNotification(request, "Place order succesfully, you get " + voucher.getValue() + "% discount!", "GREEN");
                        response.sendRedirect("home");
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
                    request.setAttribute("subtotal", subtotal);
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
