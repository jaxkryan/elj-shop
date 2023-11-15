/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dao.CategoryDAO;
import dao.FeedbackDAO;
import dao.ProductDAO;
import dao.ProviderDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import model.Category;
import model.Feedback;
import model.Product;
import model.Provider;
import util.Helper;

/**
 *
 * @author Admin
 */
public class AddFeedbackController extends HttpServlet {

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
        if (session.getAttribute("userId") == null) {
            Helper.setNotification(request, "Please login to feedback!", "RED");
            response.sendRedirect("home");
        } else {
            FeedbackDAO fdao = new FeedbackDAO();
            int userId = (int) session.getAttribute("userId");
            int proId = Integer.parseInt(request.getParameter("proId"));
            if (!fdao.isBought(userId, proId)) {
                String from = request.getParameter("from");
                CategoryDAO cdao = new CategoryDAO();
                ProviderDAO providerDAO = new ProviderDAO();
                ProductDAO pdao = new ProductDAO();
                Product product = pdao.getProductById(proId);
                Category category = cdao.getCategoryById(product.getCategoryId());
                Provider provider = providerDAO.getProviderById(product.getProviderId());
                request.setAttribute("from", from);
                request.setAttribute("product", product);
                request.setAttribute("categoryName", category.getName());
                request.setAttribute("brandName", provider.getCompanyName());
                Vector<Feedback> feedbacks = fdao.getFeedbackByProductId(proId);
                request.setAttribute("feedbacks", feedbacks);
                request.setAttribute("page", request.getParameter("page"));
                request.setAttribute("sort", request.getParameter("sort"));
                request.setAttribute("searchName", request.getParameter("searchName"));
                request.setAttribute("categoryId", request.getParameter("categoryId"));
                request.setAttribute("providerId", request.getParameter("providerId"));
                request.setAttribute("price", request.getParameter("price"));
                Helper.setNotification(request, "Please experience before review!", "RED");
                request.getRequestDispatcher("/jsp/productDetailPage.jsp").forward(request, response);
                return;
            }
            String content = request.getParameter("content");
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String feedbackDate = dateFormat.format(calendar.getTime());
            fdao.addFeedback(userId, proId, content, null, feedbackDate);
            String from = request.getParameter("from");
            CategoryDAO cdao = new CategoryDAO();
            ProviderDAO providerDAO = new ProviderDAO();
            ProductDAO pdao = new ProductDAO();
            Product product = pdao.getProductById(proId);
            Category category = cdao.getCategoryById(product.getCategoryId());
            Provider provider = providerDAO.getProviderById(product.getProviderId());
            CategoryDAO categoryDAO = new CategoryDAO();
            Vector<Category> categories = categoryDAO.getAllCategory();
            Vector<Provider> providers = providerDAO.getAllProvider();
            request.setAttribute("categories", categories);
            request.setAttribute("providers", providers);
            request.setAttribute("from", from);
            request.setAttribute("product", product);
            request.setAttribute("categoryName", category.getName());
            request.setAttribute("brandName", provider.getCompanyName());
            Vector<Feedback> feedbacks = fdao.getFeedbackByProductId(proId);
            request.setAttribute("feedbacks", feedbacks);
            request.setAttribute("page", request.getParameter("page"));
            request.setAttribute("sort", request.getParameter("sort"));
            request.setAttribute("searchName", request.getParameter("searchName"));
            request.setAttribute("categoryId", request.getParameter("categoryId"));
            request.setAttribute("providerId", request.getParameter("providerId"));
            request.setAttribute("price", request.getParameter("price"));
            Helper.setNotification(request, "Added your Review!", "GREEN");
            request.getRequestDispatcher("/jsp/productDetailPage.jsp").forward(request, response);
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
