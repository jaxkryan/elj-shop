package controller.manager;

import dao.CategoryDAO;
import dao.ProductDAO;
import dao.ProviderDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.Category;
import model.Product;
import model.Provider;

/**
 *
 * @author Huy Nguyen
 */
public class ManagerHomeController extends HttpServlet {

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
        String service = request.getParameter("go");
        CategoryDAO cdao = new CategoryDAO();
        Vector<Category> categories = cdao.getAllCategory();
        request.setAttribute("categories", categories);
        if (service == null || service.equals("")) {
            service = "displayAll";
        }
        if (service.equals("displayAll")) {
            ProductDAO pdao = new ProductDAO();
            Vector<Product> products = pdao.getAllProduct();
            request.setAttribute("products", products);
            ProviderDAO providerDAO = new ProviderDAO();
            Vector<Provider> providers = providerDAO.getAllProvider();
            request.setAttribute("providers", providers);    
            request.getRequestDispatcher("/jsp/manageProductPage.jsp").forward(request, response);
        }else if (service.equals("delete")) {
            String pid = request.getParameter("pid");
            ProductDAO pdao = new ProductDAO();
            pdao.deleteProduct(pid);
            response.sendRedirect("home");
        }
        
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
