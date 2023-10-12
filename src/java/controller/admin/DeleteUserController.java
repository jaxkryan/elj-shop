package controller.admin;

import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import util.Helper;

/**
 *
 * @author Huy Nguyen
 */
public class DeleteUserController extends HttpServlet {

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
        } else {
            UserDAO udao = new UserDAO();
            int userId = Integer.parseInt(request.getParameter("userId"));
            HttpSession session = request.getSession();
            if (userId == (Integer) session.getAttribute("userId")) {
                Helper.setNotification(request, "You cannot delete your-self!", "RED");
            } else {
                User userToDelete = udao.getById(userId);
                udao.delete(userToDelete);
            }
            response.sendRedirect("home");
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
