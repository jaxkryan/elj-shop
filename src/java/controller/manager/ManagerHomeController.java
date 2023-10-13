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
import util.Helper;

/**
 *
 * @author Huy Nguyen
 */
public class ManagerHomeController extends HttpServlet {

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
        String service = request.getParameter("go");
        CategoryDAO cdao = new CategoryDAO();
        Vector<Category> categories = cdao.getAllCategory();
        request.setAttribute("categories", categories);
        ProductDAO productDAO = new ProductDAO();
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
        } else if (service.equals("delete")) {
            int pid = Integer.parseInt(request.getParameter("pid"));            
            Product deleteProduct = productDAO.getProductById(pid);
            int checkDelete = productDAO.deleteProduct(pid);
            if (checkDelete != 0 ) {
                //Delete success notification
                Helper.setNotification(request, "Delete product " + deleteProduct.getName() + " successfully!", "GREEN");
            }else{
                //Delete fail notification
                Helper.setNotification(request, "Delete product " + deleteProduct.getName() + " fail!", "RED");
            }
            response.sendRedirect("home");
        }else if (service.equals("getEditProduct")) {
            int  pUpdateId = Integer.parseInt(request.getParameter("pid"));
            Product updateProduct = productDAO.getProductById(pUpdateId);
            request.setAttribute("updateProduct", updateProduct);
            ProviderDAO providerDAO = new ProviderDAO();
            Vector<Provider> providers = providerDAO.getAllProvider();
            request.setAttribute("providers", providers);
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/jsp/updateProductPage.jsp").forward(request, response);
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
        String service = request.getParameter("go");
        CategoryDAO cdao = new CategoryDAO();
        Vector<Category> categories = cdao.getAllCategory();
        request.setAttribute("categories", categories);
        ProductDAO proDAO = new ProductDAO();
        if (service.equals("AddProduct")) {
            String pName = request.getParameter("name");
            String pImage = request.getParameter("image");
            float pPrice = Float.parseFloat(request.getParameter("price"));
            String pDescription = request.getParameter("description");
            int pCategory = Integer.parseInt(request.getParameter("category"));
            int pProvider = Integer.parseInt(request.getParameter("provider"));
            int pQuantity = Integer.parseInt(request.getParameter("quantity"));
            float pDiscount = Float.parseFloat(request.getParameter("discount"));        
            int checkInsert = proDAO.insertProduct(pCategory, pProvider, pName, pDescription, pPrice, pDiscount, pQuantity, pImage);
            if (checkInsert != 0) {
                //Insert success notification
                Helper.setNotification(request, "Product " + pName + " added successfully!", "GREEN");
            } else {
                //Insert fail notification
                Helper.setNotification(request, "Added " + pName + " fail!", "RED");
            }
            response.sendRedirect("home");
        }else if (service.equals("UpdateProduct")) {
            int pId = Integer.parseInt(request.getParameter("id"));
            String pName = request.getParameter("name");
            String pImage = request.getParameter("image");
            float pPrice = Float.parseFloat(request.getParameter("price"));
            String pDescription = request.getParameter("description");
            int pCategory = Integer.parseInt(request.getParameter("category"));
            int pProvider = Integer.parseInt(request.getParameter("provider"));
            int pQuantity = Integer.parseInt(request.getParameter("quantity"));
            float pDiscount = Float.parseFloat(request.getParameter("discount"));
            Product UpdateProduct = new Product(pId, pCategory, pProvider, pName, pDescription, pPrice, pDiscount, pQuantity, pImage, true);
            int checkUpdate = proDAO.updateProduct(UpdateProduct);
            if (checkUpdate != 0) {
                //Insert success notification
                Helper.setNotification(request, "Update product " + pName + " successfully!", "GREEN");
            } else {
                //Insert fail notification
                Helper.setNotification(request, "Update " + pName + " fail!", "RED");
            }
            response.sendRedirect("home");
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
