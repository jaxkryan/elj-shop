package controller.manager;

import dao.CategoryDAO;
import dao.ProductDAO;
import dao.ProviderDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.StringTokenizer;
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
        CategoryDAO categoryDAO = new CategoryDAO();
        Vector<Category> categories = categoryDAO.getAllCategory();
        request.setAttribute("categories", categories);

        ProviderDAO providerDAO = new ProviderDAO();
        Vector<Provider> providers = providerDAO.getAllProvider();
        Vector<Provider> brandProviders = providerDAO.getAllBrand();
        request.setAttribute("providers", providers);
        request.setAttribute("brand", brandProviders);
        ProductDAO productDAO = new ProductDAO();
        if (service == null || service.equals("")) {
            service = "displayAll";
        }

        if (service.equals("displayAll")) {
            ProductDAO pdao = new ProductDAO();
            Vector<Product> products = pdao.getAllProduct();
            request.setAttribute("products", products);
            request.getRequestDispatcher("/jsp/manageProductPage.jsp").forward(request, response);
        } else if (service.equals("delete")) {
            int pid = Integer.parseInt(request.getParameter("pid"));
            Product deleteProduct = productDAO.getProductById(pid);
            int checkDelete = productDAO.deleteProduct(pid);
            if (checkDelete != 0) {
                //Delete success notification
                Helper.setNotification(request, "Delete product " + deleteProduct.getName() + " successfully!", "GREEN");
            } else {
                //Delete fail notification
                Helper.setNotification(request, "Delete product " + deleteProduct.getName() + " fail!", "RED");
            }
            response.sendRedirect("home");

        } else if (service.equals("filter")) {
            response.setContentType("text/html;charset=UTF-8");
            String sort = request.getParameter("sort") != null ? request.getParameter("sort") : "";
            int categoryId = Integer.parseInt(request.getParameter("categoryId") != null ? request.getParameter("categoryId") : "-1");
            int providerId = Integer.parseInt(request.getParameter("providerId") != null ? request.getParameter("providerId") : "-1");
            String price = request.getParameter("price");
            double minPrice = 0.0;
            double maxPrice = 10000000000000.0;
            String searchName = request.getParameter("searchName") != null ? request.getParameter("searchName") : "";
            String filter = "filter";

            if (price != null && !price.equals("")) {
                StringTokenizer tokenizer = new StringTokenizer(request.getParameter("price"), "-");
                minPrice = Double.parseDouble(tokenizer.nextToken());
                maxPrice = Double.parseDouble(tokenizer.nextToken());
            }
            Vector<Product> products = productDAO.getProductByFilter(sort, categoryId, providerId, minPrice, maxPrice, searchName);
            
            request.setAttribute("filter", filter);
            request.setAttribute("sort", sort);
            request.setAttribute("searchName", searchName);
            request.setAttribute("products", products);
            request.setAttribute("categoryId", categoryId);
            request.setAttribute("providerId", providerId);
            request.setAttribute("price", price);
            request.getRequestDispatcher("/jsp/manageProductPage.jsp").forward(request, response);
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
        CategoryDAO categoryDAO = new CategoryDAO();
        Vector<Category> categories = categoryDAO.getAllCategory();
        request.setAttribute("categories", categories);

        ProviderDAO providerDAO = new ProviderDAO();
        Vector<Provider> providers = providerDAO.getAllProvider();
        request.setAttribute("providers", providers);
        ProductDAO productDAO = new ProductDAO();
        if (service.equals("search")) {
            String searchName = request.getParameter("searchName");
            Vector<Product> products = new Vector<>();
            products = productDAO.getProductByName(searchName);
            if (searchName != null) {                
                request.setAttribute("searchName", searchName);
                request.setAttribute("products", products);
            }
            if(products.isEmpty()){
                //no product found notification
                Helper.setNotification(request, "No product found!", "RED");
            }
            request.getRequestDispatcher("/jsp/manageProductPage.jsp").forward(request, response);
        } else if (service.equals("AddProduct")) {
            String pName = request.getParameter("name");
            String pImage = request.getParameter("image");
            float pPrice = Float.parseFloat(request.getParameter("addPrice"));
            String pDescription = request.getParameter("description");
            int pCategory = Integer.parseInt(request.getParameter("category"));
            int pProvider = Integer.parseInt(request.getParameter("provider"));
            int pQuantity = Integer.parseInt(request.getParameter("quantity"));
            float pDiscount = Float.parseFloat(request.getParameter("addDiscount"));
            if (productDAO.checkExistProducts(pName) == false) {
                Helper.setNotification(request, "Product " + pName + " already exist!", "RED");
                response.sendRedirect("home");
                return;
            }
            int checkInsert = productDAO.insertProduct(pCategory, pProvider, pName, pDescription, pPrice, pDiscount, pQuantity, pImage);
            if (checkInsert != 0) {
                //Insert success notification
                Helper.setNotification(request, "Product " + pName + " added successfully!", "GREEN");
            } else {
                //Insert fail notification
                Helper.setNotification(request, "Added " + pName + " fail!", "RED");
            }
            response.sendRedirect("home");
        } else if (service.equals("UpdateProduct")) {
            int pId = Integer.parseInt(request.getParameter("id"));
            String pName = request.getParameter("name");
            String pImage = request.getParameter("image");
            float pPrice = Float.parseFloat(request.getParameter("updatePrice"));
            String pDescription = request.getParameter("description");
            int pCategory = Integer.parseInt(request.getParameter("category"));
            int pProvider = Integer.parseInt(request.getParameter("provider"));
            int pQuantity = Integer.parseInt(request.getParameter("quantity"));
            float pDiscount = Float.parseFloat(request.getParameter("updateDiscount"));
            if (pDiscount > pPrice) {
                Helper.setNotification(request, "Prioe must greater than discount", "RED");
                response.sendRedirect("home");
                return;
            }
            Product UpdateProduct = new Product(pId, pCategory, pProvider, pName, pDescription, pPrice, pDiscount, pQuantity, pImage, true);
            int checkUpdate = productDAO.updateProduct(UpdateProduct);
            
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
