/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

/**
 *
 * @author Datalia
 */
@WebServlet(name = "SortController", urlPatterns = {"/SortController"})
public class StorageController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            ProductDAO productDAO = new ProductDAO();
            String service = request.getParameter("sort");
            String submit = request.getParameter("submit");
            String search = request.getParameter("search") != null ? request.getParameter("search") : "";
            String keyword = request.getParameter("keyword");
            request.setAttribute("keySearch", keyword);
            if (search.equals("All")) {
                String statement = "Select * from product where name like '%" + keyword + "%' ";
                Vector<Product> products = productDAO.searchProducts(statement, "");
                request.setAttribute("products", products);
                request.setAttribute("keySearch", keyword);
                request.getRequestDispatcher("/jsp/manageStoragePage.jsp").forward(request, response);
                return;
            } else if (search.equals("Asc")) {
                String statement = "Select * from product where name like '%" + keyword + "%' ";
                Vector<Product> products = productDAO.searchProducts(statement, " order by quantity asc");
                request.setAttribute("products", products);
                request.setAttribute("keySearch", keyword);
                request.getRequestDispatcher("/jsp/ascStoragePage.jsp").forward(request, response);
                return;
            } else if (search.equals("Desc")) {
                String statement = "Select * from product where name like '%" + keyword + "%' ";
                Vector<Product> products = productDAO.searchProducts(statement, " order by quantity desc");
                request.setAttribute("products", products);
                request.setAttribute("keySearch", keyword);
                request.getRequestDispatcher("/jsp/descStoragePage.jsp").forward(request, response);
                return;
            }

            if (service == null || service.equals("All")) {
                //submit button
                if (submit != null) {
                    int pid = Integer.parseInt(request.getParameter("prodId"));
                    int quantity = Integer.parseInt(request.getParameter("qty"));
                    ProductDAO dao = new ProductDAO();
                    try {
                        dao.updateQty(pid, quantity);
                    } catch (SQLException ex) {
                        Logger.getLogger(StorageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                Vector<Product> products = productDAO.getAllProduct();
                request.setAttribute("products", products);
                RequestDispatcher disp = request.getRequestDispatcher("/jsp/manageStoragePage.jsp");
                disp.forward(request, response);
                service = "All";
            }
            if (service.equals("Asc")) {
                if (submit != null) {
                    int pid = Integer.parseInt(request.getParameter("prodId"));
                    int quantity = Integer.parseInt(request.getParameter("qty"));
                    ProductDAO dao = new ProductDAO();
                    try {
                        dao.updateQty(pid, quantity);
                    } catch (SQLException ex) {
                        Logger.getLogger(StorageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                String keySearch = request.getParameter("keySearch") != null ? request.getParameter("keySearch") : "";
                if (!keySearch.equals("")) {
                    String statement = "Select * from product where name like '%" + keySearch + "%' ";
                    Vector<Product> products = productDAO.searchProducts(statement, " order by quantity asc");
                    request.setAttribute("products", products);
                    request.setAttribute("keySearch", keySearch);
                    request.getRequestDispatcher("/jsp/ascStoragePage.jsp").forward(request, response);
                }
                RequestDispatcher disp = request.getRequestDispatcher("/jsp/ascStoragePage.jsp");
                Vector<Product> products = productDAO.sortProducts("quantity", "Asc");
                request.setAttribute("products", products);
                disp.forward(request, response);
                service = "Asc";
            }
            if (service.equals("Desc")) {
                if (submit != null) {
                    int pid = Integer.parseInt(request.getParameter("prodId"));
                    int quantity = Integer.parseInt(request.getParameter("qty"));
                    ProductDAO dao = new ProductDAO();
                    try {
                        dao.updateQty(pid, quantity);
                    } catch (SQLException ex) {
                        Logger.getLogger(StorageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                String keySearch = request.getParameter("keySearch") != null ? request.getParameter("keySearch") : "";
                if (!keySearch.equals("")) {
                    String statement = "Select * from product where name like '%" + keySearch + "%' ";
                    Vector<Product> products = productDAO.searchProducts(statement, " order by quantity desc");
                    request.setAttribute("products", products);
                    request.setAttribute("keySearch", keySearch);
                    request.getRequestDispatcher("/jsp/descStoragePage.jsp").forward(request, response);
                }
                RequestDispatcher disp = request.getRequestDispatcher("/jsp/descStoragePage.jsp");
                Vector<Product> products = productDAO.sortProducts("quantity", "Desc");
                Vector<Product> ascProducts = productDAO.sortProducts("quantity", "Asc");
                request.setAttribute("products", products);
                request.setAttribute("ascProduct", ascProducts);
                disp.forward(request, response);
                service = "Desc";
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
