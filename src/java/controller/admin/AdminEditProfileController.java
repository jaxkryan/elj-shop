package controller.admin;

import constant.IConstant;
import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import util.Helper;

/**
 *
 * @author Huy Nguyen
 */
public class AdminEditProfileController extends HttpServlet {

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
        HttpSession session = request.getSession();
        UserDAO udao = new UserDAO();
        User user = udao.getById((Integer) session.getAttribute("userId"));
        request.setAttribute("user", user);
        request.getRequestDispatcher("/jsp/adminProfilePage.jsp").forward(request, response);
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
        if (request.getParameter("AdminEditPersonalInfoSubmit") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String dateOfBirth = request.getParameter("dateOfBirth");
            String street = request.getParameter("street");
            String city = request.getParameter("city");
            String province = request.getParameter("province");
            String country = request.getParameter("country");
            String phone = request.getParameter("phone");

            HttpSession session = request.getSession();
            UserDAO udao = new UserDAO();
            User user = udao.getById((Integer) session.getAttribute("userId"));
            User userToUpdate = new User(id, "Admin", firstName, lastName, dateOfBirth, street, city, province, country, phone);
            userToUpdate.setEmail(user.getEmail());
            request.setAttribute("user", userToUpdate);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date minimumDate = new Date(System.currentTimeMillis());
            Date userDOB = new Date();
            Date maximumDate = new Date(System.currentTimeMillis());
            Calendar calendar = Calendar.getInstance();
            try {
                userDOB = dateFormat.parse(dateOfBirth);
                calendar.setTime(minimumDate);
                calendar.add(Calendar.YEAR, -100);
                minimumDate = dateFormat.parse(dateFormat.format(calendar.getTime()));
                calendar.setTime(maximumDate);
                calendar.add(Calendar.YEAR, -18);
                maximumDate = dateFormat.parse(dateFormat.format(calendar.getTime()));
            } catch (ParseException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (!firstName.matches(IConstant.REGEX_FIRSTNAME)) {
                Helper.setNotification(request, "First name is invalid!", "RED");
                request.getRequestDispatcher("/jsp/adminProfilePage.jsp").forward(request, response);
            } else if (!lastName.matches(IConstant.REGEX_LASTNAME)) {
                Helper.setNotification(request, "Last name is invalid!", "RED");
                request.getRequestDispatcher("/jsp/adminProfilePage.jsp").forward(request, response);
            } else if (userDOB.before(minimumDate) || userDOB.after(maximumDate)) {
                Helper.setNotification(request, "Age must be greater than 18!", "RED");
                request.getRequestDispatcher("/jsp/adminProfilePage.jsp").forward(request, response);
            } else if (!street.matches(IConstant.REGEX_STREET)) {
                Helper.setNotification(request, "Street name is invalid!", "RED");
                request.getRequestDispatcher("/jsp/adminProfilePage.jsp").forward(request, response);
            } else if (!city.matches(IConstant.REGEX_CITY)) {
                Helper.setNotification(request, "City name is invalid!", "RED");
                request.getRequestDispatcher("/jsp/adminProfilePage.jsp").forward(request, response);
            } else if (!province.matches(IConstant.REGEX_PROVINCE)) {
                Helper.setNotification(request, "Province name is not valid!", "RED");
                request.getRequestDispatcher("/jsp/adminProfilePage.jsp").forward(request, response);
            } else if (!country.matches(IConstant.REGEX_COUNTRY)) {
                Helper.setNotification(request, "Country name is invalid!", "RED");
                request.getRequestDispatcher("/jsp/adminProfilePage.jsp").forward(request, response);
            } else if (!phone.matches(IConstant.REGEX_PHONE)) {
                Helper.setNotification(request, "Please enter valid phone number!", "RED");
                request.getRequestDispatcher("/jsp/adminProfilePage.jsp").forward(request, response);
            } else {
                udao.updateProfile(userToUpdate);
                Helper.setNotification(request, "Change information successfully!", "GREEN");
                response.sendRedirect("profile");
            }
        } else if (request.getParameter("AdminUpdateEmailSubmit") != null) {
            String email = request.getParameter("email");

            HttpSession session = request.getSession();
            UserDAO udao = new UserDAO();
            User user = udao.getById((Integer) session.getAttribute("userId"));
            user.setEmail(email);
            request.setAttribute("user", user);

            if (!email.matches(IConstant.REGEX_EMAIL)) {
                Helper.setNotification(request, "Please enter valid email address!", "RED");
                request.getRequestDispatcher("/jsp/adminProfilePage.jsp").forward(request, response);
            } else {
                udao.updateEmail(user, email);
                request.getSession().invalidate();
                Helper.setNotification(request, "Update email successfully! Please login again!", "GREEN");
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } else if (request.getParameter("AdminChangePasswordSubmit") != null) {
            String oldPassword = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");
            String confirmedPassword = request.getParameter("confirmedPassword");

            HttpSession session = request.getSession();
            UserDAO udao = new UserDAO();
            User user = udao.getById((Integer) session.getAttribute("userId"));
            request.setAttribute("user", user);

            if (!user.getPassword().equals(Helper.hashPassword(oldPassword))) {
                Helper.setNotification(request, "Wrong password", "RED");
                request.getRequestDispatcher("/jsp/adminProfilePage.jsp").forward(request, response);
            } else if (!newPassword.matches(IConstant.REGEX_PASSWORD)) {
                Helper.setNotification(request, "Password must be Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character!", "RED");
                request.getRequestDispatcher("/jsp/adminProfilePage.jsp").forward(request, response);
            } else if (!confirmedPassword.equals(newPassword)) {
                Helper.setNotification(request, "Confirmed password does not match with password!", "RED");
                request.getRequestDispatcher("/jsp/adminProfilePage.jsp").forward(request, response);
            } else {
                User userToUpdate = new User((Integer) session.getAttribute("userId"), user.getEmail(), Helper.hashPassword(newPassword));
                udao.changePassword(userToUpdate, Helper.hashPassword(newPassword));
                request.getSession().invalidate();
                Helper.setNotification(request, "Change password successfully! Please login again!", "GREEN");
                response.sendRedirect(request.getContextPath() + "/login");
            }
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
