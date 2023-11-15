package controller.admin;

import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.User;

/**
 *
 * @author Huy Nguyen
 */
public class ManageUserController extends HttpServlet {

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
        UserDAO udao = new UserDAO();
        Vector<User> users = new Vector<>();
        String roleFilter = request.getParameter("roleFilter") == null ? "" : request.getParameter("roleFilter");
        String searchName = request.getParameter("searchName") == null ? "" : request.getParameter("searchName");
        if (roleFilter.isEmpty() || roleFilter.equals("All") && searchName.isEmpty()) {
            users = udao.getActiveUsers();
        } else {
            users = udao.filterUsers(roleFilter, searchName);
        }
        request.setAttribute("users", users);
        request.getRequestDispatcher("/jsp/manageUserPage.jsp").forward(request, response);
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
