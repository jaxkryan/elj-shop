/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;
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
                + "      ,[startDate]\n"
                + "      ,[endDate]\n"
                + "      ,[value]\n"
                + "  FROM [dbo].[Voucher] where active = 1";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String code = rs.getString(2);
                Date startDate = rs.getDate(3);
                Date endDate = rs.getDate(4);
                Double value = rs.getDouble(5);
                //
                voucher.add(new Voucher(id, code, startDate, endDate, value, true));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return voucher;
    }

//
    public Vector<Voucher> findByCode(String vcode) {
        Vector<Voucher> voucher = new Vector<>();
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[code]\n"
                    + "      ,[startDate]\n"
                    + "      ,[endDate]\n"
                    + "      ,[value]\n"
                    + "  FROM [OnlineShop].[dbo].[Voucher] where code like ? and active = 1";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + vcode + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String code = rs.getString(2);
                Date startDate = rs.getDate(3);
                Date endDate = rs.getDate(4);
                Double value = rs.getDouble(5);
                //
                voucher.add(new Voucher(id, code, startDate, endDate, value, true));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return voucher;
    }

    public int insertVoucher(Voucher vc) {
        int n = 0;
        try {
            String sql = "INSERT INTO [dbo].[Voucher]\n"
                    + "           ([code]\n"
                    + "           ,[startDate]\n"
                    + "           ,[endDate]\n"
                    + "           ,[value]\n"
                    + "           ,[active])\n"
                    + " VALUES (?, ?, ?, ?, ?) ";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, vc.getCode());
            stm.setDate(2, vc.getStartDate());
            stm.setDate(3, vc.getEndDate());
            stm.setDouble(4, vc.getValue());
            stm.setBoolean(5, true);
            n = stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateVoucher(Voucher vc) {
        int n = 0;
        try {
            String sql = "UPDATE [dbo].[Voucher]\n"
                    + "   SET [code] = ?\n"
                    + "      ,[startDate] = ?\n"
                    + "      ,[endDate] = ?\n"
                    + "      ,[value] = ?\n"
                    + "      ,[active] = ?\n"
                    + " WHERE id = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, vc.getCode());
            stm.setDate(2, vc.getStartDate());
            stm.setDate(3, vc.getEndDate());
            stm.setDouble(4, vc.getValue());
            stm.setBoolean(5, true);
            stm.setInt(6, vc.getId());
            n = stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteVoucher(int voucherID) {
        int n = 0;
        try {
            String sql = "UPDATE [dbo].[Voucher]\n"
                    + "   SET [active] = 0\n"
                    + " WHERE id = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, voucherID);
            n = stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Voucher getById(int id) {
        String sql = "SELECT [id]\n"
                    + "      ,[code]\n"
                    + "      ,[startDate]\n"
                    + "      ,[endDate]\n"
                    + "      ,[value]\n"
                    + "      ,[active]\n"
                    + " FROM [OnlineShop].[dbo].[Voucher] where id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return new Voucher(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getDouble(5), rs.getBoolean(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void main(String[] args) {
        VoucherDAO dao = new VoucherDAO();
        Vector<Voucher> v = dao.getAll();
        System.out.println(v);
    }
}
