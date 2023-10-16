package controller.marketingstaff;

import dao.VoucherDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.Voucher;

/**
 *
 * @author maclile
 */
public class MaketingStaffHomeController extends HttpServlet {

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
        if (service == null || service.equals("")) {
            service = "displayAll";
        }
//        if (service.equals("displayAll")) {
//            VoucherDAO voucherDAO = new VoucherDAO();
//            Vector<Voucher> voucher = voucherDAO.getAll();
//            request.setAttribute("voucher", voucher);
//            request.getRequestDispatcher("/jsp/manageVoucherPage.jsp").forward(request, response);
//        }
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
        VoucherDAO voucherDAO = new VoucherDAO();
//        if(request.getParameter("search") != null){
//            String code = request.getParameter("code");
//            Vector<Voucher> voucher = voucherDAO.findByCode(code);
//            request.setAttribute("voucher", voucher);
//            request.getRequestDispatcher("/jsp/manageVoucherPage.jsp").forward(request, response);
//        }
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
