/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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

/**
 *
 * @author Datalia
 */
public class ReportController extends HttpServlet {

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
                request.getRequestDispatcher("/jsp/storageReport.jsp").forward(request, response);
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
                HttpSession session = request.getSession();
                int managerId = (int) session.getAttribute("userId");
                int staffId = Integer.parseInt(request.getParameter("staffId"));
                String title = "Reply for " + request.getParameter("title");
                String content = request.getParameter("reply-content");
                Report report = new Report(staffId, managerId, title, content);
                reportDAO.insertReport(report);
                Vector<Report> reports = reportDAO.getAllReport();
                Vector<User> staff = userDAO.getActiveStorageStaff();
                request.setAttribute("staff", staff);
                request.setAttribute("reports", reports);
                request.getRequestDispatcher("/jsp/manageStorageReport.jsp").forward(request, response);
            }
            if (action.equals("add")) {
                int managerId = Integer.parseInt(request.getParameter("name"));
                String title = request.getParameter("title");
                String content = request.getParameter("content");
                HttpSession session = request.getSession();
                int staffId = (int) session.getAttribute("userId");
                Report report = new Report(staffId, managerId, title, content);
                reportDAO.insertReport(report);
                Vector<Report> reports = reportDAO.getAllReport();
                Vector<User> managers = userDAO.getActiveManager();
                request.setAttribute("reports", reports);
                request.setAttribute("managers", managers);
                request.getRequestDispatcher("/jsp/storageReport.jsp").forward(request, response);
            }
            if (action.equals("manager-add")) {
                HttpSession session = request.getSession();
                int managerId = (int) session.getAttribute("userId");
                int staffId = Integer.parseInt(request.getParameter("name"));
                String title = request.getParameter("title");
                String content = request.getParameter("reply-content");
                Report report = new Report(staffId, managerId, title, content);
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
