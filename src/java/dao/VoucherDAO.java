/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.DBConnect;
import model.Voucher;

/**
 *
 * @author maclife
 */
public class VoucherDAO extends DBConnect {

    public Vector<Voucher> getAll() {
        Vector<Voucher> voucher = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[code]\n"
                + "  FROM [OnlineShop].[dbo].[Voucher]";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String code = rs.getString(2);
                voucher.add(new Voucher(id, code));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return voucher;
    }

    public Vector<Voucher> findByCode(String vcode) {
        Vector<Voucher> voucher = new Vector<>();
        try {
        String sql = "SELECT [id]\n"
                + "      ,[code]\n"
                + "  FROM [OnlineShop].[dbo].[Voucher] where code like ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + vcode + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String code = rs.getString(2);
                voucher.add(new Voucher(id, code));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return voucher;
    }
    
}