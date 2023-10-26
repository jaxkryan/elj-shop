/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Report;
import java.util.Vector;
import dao.ReportDAO;
import dao.UserDAO;
import jakarta.servlet.http.HttpSession;
import model.User;
import java.util.Date;
import java.text.SimpleDateFormat;
import util.Helper;

/**
 *
 * @author Datalia
 */
public class ManageReportController extends HttpServlet {

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
            ReportDAO reportDAO = new ReportDAO();
            UserDAO userDAO = new UserDAO();
            String action = request.getParameter("action");
            if (action.equals("view")) {
                Vector<Report> reports = reportDAO.getAllReport();
                Vector<User> managers = userDAO.getActiveManager();
                request.setAttribute("managers", managers);
                request.setAttribute("reports", reports);
                request.getRequestDispatcher("/jsp/manageStorageReport.jsp").forward(request, response);
            }
            if (action.equals("delete")) {
                ReportDAO rpdao = new ReportDAO();
                int rpId = Integer.parseInt(request.getParameter("reportId"));
                rpdao.deleteReport(rpId);
                response.sendRedirect("write-report?action=view");
            }
            if (action.equals("reply")) {
                Vector<Report> reports = reportDAO.getAllReport();
                Vector<User> staff = userDAO.getActiveStorageStaff();
                request.setAttribute("staff", staff);
                request.setAttribute("reports", reports);
                request.getRequestDispatcher("/jsp/manageStorageReport.jsp").forward(request, response);
            }
            if (action.equals("reply-staff")) {
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                String formattedDate = formatter.format(date);
                HttpSession session = request.getSession();
                int reportId = Integer.parseInt(request.getParameter("reportId"));
                reportDAO.changeReportStatus(reportId);
                int managerId = (int) session.getAttribute("userId");
                int staffId = Integer.parseInt(request.getParameter("staffId"));
                String title = "Reply for " + request.getParameter("title");
                String content = request.getParameter("reply-content");
                Report report = new Report(staffId, managerId, title, content, formattedDate, true);
                int writeReport = reportDAO.insertReport(report);
                String staffName = userDAO.getById(staffId).getFirstName() +" "+ userDAO.getById(staffId).getLastName();
                if (writeReport != 0) {
                //Update success notification
                Helper.setNotification(request, "Send reply to " + staffName + " successfully!", "GREEN");
            } else {
                //Update fail notification
                Helper.setNotification(request, "Send reply to " + staffName + " fail!", "RED");
            }
                Vector<Report> reports = reportDAO.getAllReport();
                Vector<User> staff = userDAO.getActiveStorageStaff();
                request.setAttribute("staff", staff);
                request.setAttribute("reports", reports);
                request.getRequestDispatcher("/jsp/manageStorageReport.jsp").forward(request, response);
            }
            if (action.equals("add")) {
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                String formattedDate = formatter.format(date);
                int managerId = Integer.parseInt(request.getParameter("name"));
                String title = request.getParameter("title");
                String content = request.getParameter("content");
                HttpSession session = request.getSession();
                int staffId = (int) session.getAttribute("userId");
                Report report = new Report(staffId, managerId, title, content, formattedDate, false);
                reportDAO.insertReport(report);
                Vector<Report> reports = reportDAO.getAllReport();
                Vector<User> managers = userDAO.getActiveManager();
                request.setAttribute("reports", reports);
                request.setAttribute("managers", managers);
                request.getRequestDispatcher("/jsp/storageReport.jsp").forward(request, response);
            }
            if (action.equals("manager-add")) {
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                String formattedDate = formatter.format(date);
                HttpSession session = request.getSession();
                int managerId = (int) session.getAttribute("userId");
                int staffId = Integer.parseInt(request.getParameter("name"));
                String title = request.getParameter("title");
                String content = request.getParameter("content");
                Report report = new Report(staffId, managerId, title, content, formattedDate, false);
                reportDAO.insertReport(report);
                Vector<Report> reports = reportDAO.getAllReport();
                Vector<User> staff = userDAO.getActiveStorageStaff();
                request.setAttribute("staff", staff);
                request.setAttribute("reports", reports);
                request.getRequestDispatcher("/jsp/manageStorageReport.jsp").forward(request, response);;
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
