package controller.admin;

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
public class UpdateUserController extends HttpServlet {

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
        if (!Helper.isUserExists(request)) {
            Helper.setNotification(request, "User doesn't exists!", "RED");
            response.sendRedirect("home");
        } else {
            UserDAO udao = new UserDAO();
            User userToUpdate = udao.getById(Integer.parseInt(request.getParameter("userId")));
            request.setAttribute("userToUpdate", userToUpdate);
            request.getRequestDispatcher("/jsp/updateUserPage.jsp").forward(request, response);
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
        if (request.getParameter("updateUserSubmit") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            String role = request.getParameter("role");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String dateOfBirth = request.getParameter("dateOfBirth");
            String street = request.getParameter("street");
            String city = request.getParameter("city");
            String province = request.getParameter("province");
            String country = request.getParameter("country");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            UserDAO udao = new UserDAO();
            udao.update(new User(id, role, firstName, lastName, dateOfBirth, street, city, province, country, phone, email, password));
            Helper.setNotification(request, "Update user information successfully!", "GREEN");
        }
        response.sendRedirect("home");
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
