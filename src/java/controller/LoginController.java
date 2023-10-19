package controller;

import dao.CartDAO;
import dao.CartItemDAO;
import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.CartItem;
import model.User;
import util.Helper;

/**
 *
 * @author Huy Nguyen
 */
public class LoginController extends HttpServlet {

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
        request.getRequestDispatcher("/jsp/loginPage.jsp").forward(request, response);
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        if (username == null || username.equals("")) {
            Helper.setNotification(request, "Email cannot be blank!", "RED");
            request.getRequestDispatcher("/jsp/loginPage.jsp").forward(request, response);
            return;
        } else if (password == null || password.equals("")) {
            Helper.setNotification(request, "Password cannot be blank!", "RED");
            request.getRequestDispatcher("/jsp/loginPage.jsp").forward(request, response);
            return;
        }
        UserDAO udao = new UserDAO();
        User user = udao.getActiveUserByEmail(username);
        if (user != null) {
            System.out.println(Helper.hashPassword(password));
            if (user.getPassword().equals(Helper.hashPassword(password))) {
                //Reset user infomation
                session.removeAttribute("userId");
                session.removeAttribute("userRole");
                session.removeAttribute("cartItem");

                //Get new infomation
                session = request.getSession();
                session.setAttribute("userId", user.getId());
                session.setAttribute("userRole", user.getRole());
                CartDAO cdao = new CartDAO();
                int cartId = cdao.getCartIdByCustomerId(user.getId());
                CartItemDAO cidao = new CartItemDAO();
                Vector<CartItem> cartItem = cidao.getCartItemByCartId(cartId);
                session.setAttribute("cartItem", cartItem);
                sendRedirectByRole(response, user.getRole());
            } else {
                Helper.setNotification(request, "Wrong password!", "RED");
                request.getRequestDispatcher("/jsp/loginPage.jsp").forward(request, response);
            }
        } else {
            Helper.setNotification(request, "User doesn't exists!", "RED");
            request.getRequestDispatcher("/jsp/loginPage.jsp").forward(request, response);
        }
    }

    private void sendRedirectByRole(HttpServletResponse response, String role) throws IOException {
        switch (role) {
            case "Admin":
                response.sendRedirect("admin/home");
                break;
            case "Customer":
                response.sendRedirect("home");
                break;
            case "Seller":
                response.sendRedirect("seller/home");
                break;
            case "Storage Staff":
                response.sendRedirect("storage-staff/home");
                break;
            case "Marketing Staff":
                response.sendRedirect("marketing-staff/home");
                break;
            case "Manager":
                response.sendRedirect("manager/home");
                break;
            default:
                break;
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
