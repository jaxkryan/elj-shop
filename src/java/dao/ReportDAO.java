/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;
import model.Report;

/**
 *
 * @author Datalia
 */
public class ReportDAO extends jdbc.DBConnect {

    public Vector<Report> getAllReport() {
        Vector<Report> reportList = new Vector<>();
        String sql = "select * from Report";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                int storageStaffId = rs.getInt(2);
                int managerId = rs.getInt(3);
                String title = rs.getString(4);
                String content = rs.getString(5);
                String writeDate = rs.getString(6);
                boolean readStatus = rs.getBoolean(7);
                reportList.add(new Report(id, storageStaffId, managerId, title, content, writeDate, readStatus));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reportList;
    }

    public int updateReport(Report report) {
        int rowsAffected = 0;
        String sql = "UPDATE [dbo].[Report]\n"
                + "   SET [storageStaffId] = ?\n"
                + "      ,[managerId] = ?\n"
                + "      ,[title] = ?\n"
                + "      ,[content] = ?\n"
                + "      ,[writeDate] = ?\n"
                + "      ,[readStatus] = ?\n"
                + " WHERE id = ?\n";
        PreparedStatement pre;
        try {
            pre = conn.prepareStatement(sql);
            pre.setInt(1, report.getStorageStaffId());
            pre.setInt(2, report.getManagerId());
            pre.setString(3, report.getTitle());
            pre.setString(4, report.getContent());
            pre.setString(5, report.getWriteDate());
            pre.setBoolean(6, report.isReadStatus());
            pre.setInt(7, report.getId());;
            rowsAffected = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
    }

    public void deleteReport(int id) {
        String sql = "DELETE FROM [dbo].[Report]\n"
                + "      WHERE id=?\n";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int insertReport(Report report) {
        int affectedRows = 0;
        String sql = "INSERT INTO [dbo].[Report]\n"
                + "           ([storageStaffId]\n"
                + "           ,[managerId]\n"
                + "           ,[title]\n"
                + "           ,[content]\n"
                + "           ,[writeDate]\n"
                + "           ,[readStatus])\n"
                + "     VALUES"
                + "           (?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, report.getStorageStaffId());
            pre.setInt(2, report.getManagerId());
            pre.setString(3, report.getTitle());
            pre.setString(4, report.getContent());
            pre.setString(5, report.getWriteDate());
            pre.setBoolean(6, report.isReadStatus());
            affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return affectedRows;
    }

    public Report getById(int id) {
        String sql = "SELECT [id]\n"
                + "      ,[storageStaffId]\n"
                + "      ,[managerId]\n"
                + "      ,[title]\n"
                + "      ,[content]\n"
                + "      ,[writeDate]\n"
                + "      ,[readStatus]\n"
                + "  FROM [dbo].[Report]"
                + " where id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int storageStaffId = rs.getInt(2);
                int managerId = rs.getInt(3);
                String title = rs.getString(3);
                String content = rs.getString(4);
                String writeDate = rs.getString(5);
                boolean readStatus = rs.getBoolean(6);
                return new Report(id, storageStaffId, managerId, title, content, writeDate, readStatus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
