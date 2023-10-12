/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dao.CartDAO;
import dao.CartItemDAO;
import dao.CategoryDAO;
import dao.ProductDAO;
import dao.ProviderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.StringTokenizer;
import java.util.Vector;
import model.CartItem;
import model.Category;
import model.Product;
import model.Provider;
import util.Helper;

/**
 *
 * @author Admin
 */
public class AddToCartShopPageController extends HttpServlet {

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
                    cidao.addToCart(product, cartId);
                    Vector<CartItem> cartItem = cidao.getCartItemByCartId(cartId);
                    session.setAttribute("cartItem", cartItem);
                    Helper.setNotification(request, "Added " + product.getName() + " to Cart", "GREEN");
                    String sort = request.getParameter("sort") != null ? request.getParameter("sort") : "";
                    int categoryId = Integer.parseInt(request.getParameter("categoryId") != null ? request.getParameter("categoryId") : "-1");
                    int providerId = Integer.parseInt(request.getParameter("providerId") != null ? request.getParameter("providerId") : "-1");
                    String price = request.getParameter("price");
                    double minPrice = 0.0;
                    double maxPrice = 10000000000000.0;
                    String searchName = request.getParameter("searchName") != null ? request.getParameter("searchName") : "";

                    if (price != null && !price.equals("")) {
                        StringTokenizer tokenizer = new StringTokenizer(request.getParameter("price"), "-");
                        minPrice = Double.parseDouble(tokenizer.nextToken());
                        maxPrice = Double.parseDouble(tokenizer.nextToken());
                    }
                    ProductDAO productDAO = new ProductDAO();
                    CategoryDAO categoryDAO = new CategoryDAO();
                    ProviderDAO providerDAO = new ProviderDAO();
                    Vector<Product> products = productDAO.getProductByFilter(sort, categoryId, providerId, minPrice, maxPrice, searchName);
                    Vector<Category> categories = categoryDAO.getAllCategory();
                    Vector<Provider> providers = providerDAO.getAllProvider();
                    request.setAttribute("sort", sort);
                    request.setAttribute("searchName", searchName);
                    request.setAttribute("products", products);
                    request.setAttribute("categoryId", categoryId);
                    request.setAttribute("providerId", providerId);
                    request.setAttribute("price", price);
                    request.setAttribute("categories", categories);
                    request.setAttribute("providers", providers);
                    request.getRequestDispatcher("/jsp/shopPage.jsp").forward(request, response);
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
