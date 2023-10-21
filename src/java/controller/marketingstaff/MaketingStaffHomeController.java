package controller.marketingstaff;

import dao.VoucherDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.Vector;
import model.Voucher;
import util.Helper;

/**
 *
 * @author maclile
 */
public class MaketingStaffHomeController extends HttpServlet {

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
        if (service == null || service.equals("")) {
            service = "displayAll";
        }
        if (service.equals("displayAll")) {
            VoucherDAO voucherDAO = new VoucherDAO();
            Vector<Voucher> voucher = voucherDAO.getAll();
            request.setAttribute("voucher", voucher);
            request.getRequestDispatcher("/jsp/manageVoucherPage.jsp").forward(request, response);
        }
        if (service.equals("delete")) {
            VoucherDAO dao = new VoucherDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            Voucher deleteVoucher = dao.getById(id);
            int checkDelete = dao.deleteVoucher(id);
            
            if (checkDelete != 0 ) {
                //Delete success notification
                Helper.setNotification(request, "Delete voucher " + deleteVoucher.getCode() + " successfully!", "GREEN");
            }else{
                //Delete fail notification
                Helper.setNotification(request, "Delete voucher " + deleteVoucher.getCode() + " fail!", "RED");
            }
            response.sendRedirect("home");
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
        VoucherDAO voucherDAO = new VoucherDAO();
        //
        if (request.getParameter("search") != null) {
            String code = request.getParameter("code");
            Vector<Voucher> voucher = voucherDAO.findByCode(code);
            request.setAttribute("voucher", voucher);
            request.getRequestDispatcher("/jsp/manageVoucherPage.jsp").forward(request, response);
        }
        //
        if (service.equals("add")) {
            Voucher vc = new Voucher();
            vc.setCode(request.getParameter("code"));
            vc.setStartDate(Date.valueOf(request.getParameter("startDate")));
            vc.setEndDate(Date.valueOf(request.getParameter("endDate")));
            vc.setValue(Double.parseDouble(request.getParameter("value")));
            voucherDAO.insertVoucher(vc);
            response.sendRedirect(request.getContextPath() + "/marketing-staff/home");
            Helper.setNotification(request, "Create new voucher successfully!", "GREEN");
        }
        if (service.equals("updateVoucher")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String code = request.getParameter("code");
            Date startDate = Date.valueOf(request.getParameter("startDate"));
            Date endDate = Date.valueOf(request.getParameter("endDate"));
            Double value = Double.parseDouble(request.getParameter("value"));
            //
            Voucher updateVoucher = new Voucher(id, code, startDate, endDate, value, true);
            //
            int checkUpdate = voucherDAO.updateVoucher(updateVoucher);
            //
            if (checkUpdate != 0) {
                //Insert success notification
                Helper.setNotification(request, "Update product " + code + " successfully!", "GREEN");
            } else {
                //Insert fail notification
                Helper.setNotification(request, "Update " + code + " fail!", "RED");
            }
            response.sendRedirect("home");
        }
    }

}
