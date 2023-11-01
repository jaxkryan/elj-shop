/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dao.CartDAO;
import dao.CartItemDAO;
import dao.CategoryDAO;
import dao.FeedbackDAO;
import dao.ProductDAO;
import dao.ProviderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.CartItem;
import model.Category;
import model.Feedback;
import model.Product;
import model.Provider;
import util.Helper;

/**
 *
 * @author Admin
 */
public class AddToCartController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("proId") == null || request.getParameter("proId").equals("")) {
            response.sendRedirect("jsp/Error.jsp");
        } else {
            int proId = Integer.parseInt(request.getParameter("proId"));
            int quantity = 0;
            try {
                quantity = Integer.parseInt(request.getParameter("quantityToBuy") != null ? request.getParameter("quantityToBuy") : "1");
            } catch (Exception e) {
                String from = request.getParameter("from");
                ProductDAO pdao = new ProductDAO();
                CategoryDAO cdao = new CategoryDAO();
                ProviderDAO providerDAO = new ProviderDAO();
                Product product = pdao.getProductById(proId);
                Category category = cdao.getCategoryById(product.getCategoryId());
                Provider provider = providerDAO.getProviderById(product.getProviderId());
                request.setAttribute("from", from);
                request.setAttribute("product", product);
                request.setAttribute("categoryName", category.getName());
                request.setAttribute("brandName", provider.getCompanyName());
                FeedbackDAO fdao = new FeedbackDAO();
                Vector<Feedback> feedbacks = fdao.getFeedbackByProductId(proId);
                request.setAttribute("feedbacks", feedbacks);
                CategoryDAO categoryDAO = new CategoryDAO();
                Vector<Category> categories = categoryDAO.getAllCategory();
                Vector<Provider> providers = providerDAO.getAllProvider();
                request.setAttribute("categories", categories);
                request.setAttribute("providers", providers);
                Helper.setNotification(request, "Please enter a valid quantity!", "RED");
                request.getRequestDispatcher("/jsp/productDetailPage.jsp").forward(request, response);
                return;
            }
            if (quantity <= 0) {
                String from = request.getParameter("from");
                ProductDAO pdao = new ProductDAO();
                CategoryDAO cdao = new CategoryDAO();
                ProviderDAO providerDAO = new ProviderDAO();
                Product product = pdao.getProductById(proId);
                Category category = cdao.getCategoryById(product.getCategoryId());
                Provider provider = providerDAO.getProviderById(product.getProviderId());
                request.setAttribute("from", from);
                request.setAttribute("product", product);
                request.setAttribute("categoryName", category.getName());
                request.setAttribute("brandName", provider.getCompanyName());
                FeedbackDAO fdao = new FeedbackDAO();
                Vector<Feedback> feedbacks = fdao.getFeedbackByProductId(proId);
                request.setAttribute("feedbacks", feedbacks);
                CategoryDAO categoryDAO = new CategoryDAO();
                Vector<Category> categories = categoryDAO.getAllCategory();
                Vector<Provider> providers = providerDAO.getAllProvider();
                request.setAttribute("categories", categories);
                request.setAttribute("providers", providers);
                Helper.setNotification(request, "Please enter a valid quantity!", "RED");
                request.getRequestDispatcher("/jsp/productDetailPage.jsp").forward(request, response);
                return;
            }
            String thisPage = request.getParameter("thisPage");
            ProductDAO pdao = new ProductDAO();
            Product product = pdao.getProductById(proId);
            if (product == null) {
                Helper.setNotification(request, "Product does not existed!", "RED");
                response.sendRedirect("home");
            } else {
                HttpSession session = request.getSession();
                if (session.getAttribute("userId") == null) {
                    Helper.setNotification(request, "Please login!", "RED");
                    response.sendRedirect("home");
                } else {
                    int userId = (int) session.getAttribute("userId");
                    CartDAO cdao = new CartDAO();
                    int cartId = cdao.getCartIdByCustomerId(userId);
                    CartItemDAO cidao = new CartItemDAO();
                    Vector<CartItem> cartItem = cidao.getCartItemByCartId(cartId);
                    int quantityInCart = 0;
                    for (int i = 0; i < cartItem.size(); i++) {
                        CartItem item = cartItem.get(i);
                        if (item.getProductId() == product.getId()) {
                            quantityInCart = item.getQuantity();
                        }
                    }
                    if (quantity + quantityInCart > product.getQuantity()) {
                        Helper.setNotification(request, "Not enough " + product.getName() + " in stock!", "RED");
                        response.sendRedirect("home");
                        return;
                    }
                    cidao.addToCart(product, cartId, quantity);
                    cartItem = cidao.getCartItemByCartId(cartId);
                    session.setAttribute("cartItem", cartItem);
                    Helper.setNotification(request, "Added " + quantity + " " + product.getName() + " to Cart", "GREEN");
                    response.sendRedirect("home");
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
