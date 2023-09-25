/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.DBConnect;
import model.VoucherDetail;

/**
 *
 * @author maclife
 */
public class VoucherDetailDAO extends DBConnect {

    /**
     *
     * @param voucherDetailId
     * @return
     */
    public VoucherDetail getByID(int voucherDetailId) {
        String sql = "SELECT [id]\n"
                + "      ,[voucherId]\n"
                + "      ,[startDate]\n"
                + "      ,[endDate]\n"
                + "      ,[value]\n"
                + "  FROM [OnlineShop].[dbo].[VoucherDetails] where voucherId = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, voucherDetailId);
            ResultSet rs = getData(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                int voucherID = rs.getInt(2);
                String startDate = rs.getString(3);
                String endDate = rs.getString(4);
                double value = rs.getDouble(4);
                return new VoucherDetail(id, voucherID, startDate, endDate, value);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
