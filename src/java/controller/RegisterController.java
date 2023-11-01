package controller;

import constant.IConstant;
import dao.CartDAO;
import dao.CustomerDAO;
import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import util.Helper;

/**
 *
 * @author Huy Nguyen
 */
public class RegisterController extends HttpServlet {

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
        request.getRequestDispatcher("/jsp/registerPage.jsp").forward(request, response);
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
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmedPassword = request.getParameter("confirmedPassword");
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("email", email);
        request.setAttribute("password", password);
        request.setAttribute("confirmedPassword", confirmedPassword);
        
        if (!firstName.matches(IConstant.REGEX_FIRSTNAME)) {
            Helper.setNotification(request, "First name is invalid!", "RED");
            request.getRequestDispatcher("/jsp/registerPage.jsp").forward(request, response);
        } else if (!lastName.matches(IConstant.REGEX_LASTNAME)) {
            Helper.setNotification(request, "Last name is invalid!", "RED");
            request.getRequestDispatcher("/jsp/registerPage.jsp").forward(request, response);
        } else if (!email.matches(IConstant.REGEX_EMAIL)) {
            Helper.setNotification(request, "Please enter valid email address!", "RED");
            request.getRequestDispatcher("/jsp/registerPage.jsp").forward(request, response);
        } else if (!password.matches(IConstant.REGEX_PASSWORD)) {
            Helper.setNotification(request, "Password must be Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character!", "RED");
            request.getRequestDispatcher("/jsp/registerPage.jsp").forward(request, response);
        } else if (!confirmedPassword.equals(password)) {
            Helper.setNotification(request, "Confirmed password does not match with password!", "RED");
            request.getRequestDispatcher("/jsp/registerPage.jsp").forward(request, response);
        } else {
            UserDAO udao = new UserDAO();
            if(udao.isEmailExisted(email)) {
                Helper.setNotification(request, "Email address has been used!", "RED");
                request.getRequestDispatcher("/jsp/registerPage.jsp").forward(request, response);
            } else {
                User user = new User("Customer", firstName, lastName, null, null, null, null, null, null, email, Helper.hashPassword(password));
                user.setId(udao.insert(user, true));
                CustomerDAO customerDAO = new CustomerDAO();
                customerDAO.insert(user.getId());
                CartDAO cartDAO = new CartDAO();
                cartDAO.insert(user.getId());
                Helper.setNotification(request, "Register successfully! Please login to access your account", "GREEN");
                response.sendRedirect("login");
            }
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
