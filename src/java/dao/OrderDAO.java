package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;
import model.User;

public class OrderDAO extends jdbc.DBConnect {
    public Vector<Order> getAll() {
        Vector<Order> orders = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[customerId]\n"
                + "      ,[receiver]\n"
                + "      ,[shipStreet]\n"
                + "      ,[shipCity]\n"
                + "      ,[shipProvince]\n"
                + "      ,[shipCountry]\n"
                + "      ,[shipEmail]\n"
                + "      ,[shipPhone]"
                + "      ,[status]\n"
                + "      ,[createdTime]\n"
                + "      ,[totalPrice]\n"
                + "  FROM [dbo].[Order]";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                int customerId = rs.getInt(2);
                String receiver = rs.getString(3);
                String shipStreet = rs.getString(4);
                String shipCity = rs.getString(5);
                String shipProvince = rs.getString(6);
                String shipCountry = rs.getString(7);
                String shipEmail = rs.getString(8);
                String shipPhone = rs.getString(9);
                String status = rs.getString(10);
                String createdTime = rs.getString(11);
                float totalPrice = rs.getFloat(12);
                orders.add(new Order(id, customerId, receiver, shipStreet, shipCity,
                        shipProvince, shipCountry, shipEmail, shipPhone, status, createdTime, totalPrice));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    public Order getById(int id) {
        String sql = "SELECT [id]\n"
                + "      ,[customerId]\n"
                + "      ,[receiver]\n"
                + "      ,[shipStreet]\n"
                + "      ,[shipCity]\n"
                + "      ,[shipProvince]\n"
                + "      ,[shipCountry]\n"
                + "      ,[shipEmail]"
                + "      ,[shipPhone]\n"
                + "      ,[status]\n"
                + "      ,[createdTime]\n"
                + "      ,[totalPrice]\n"
                + "  FROM [dbo].[Order]"
                + " where id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt(2);
                String receiver = rs.getString(3);
                String shipStreet = rs.getString(4);
                String shipCity = rs.getString(5);
                String shipProvince = rs.getString(6);
                String shipCountry = rs.getString(7);
                String shipEmail = rs.getString(8);
                String shipPhone = rs.getString(9);
                String status = rs.getString(10);
                String createdTime = rs.getString(11);
                float totalPrice = rs.getFloat(12);
                return new Order(id, customerId, receiver, shipStreet, shipCity, shipProvince, shipCountry, shipEmail, shipPhone, status, createdTime, totalPrice);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Vector<Order> getByName(String name) {
        Vector<Order> orders = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[customerId]\n"
                + "      ,[receiver]\n"
                + "      ,[shipStreet]\n"
                + "      ,[shipCity]\n"
                + "      ,[shipProvince]\n"
                + "      ,[shipCountry]\n"
                + "      ,[shipEmail]"
                + "      ,[shipPhone]\n"
                + "      ,[status]\n"
                + "      ,[createdTime]\n"
                + "      ,[totalPrice]\n"
                + "  FROM [dbo].[Order]"
                + " where receiver like ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int customerId = rs.getInt(2);
                String receiver = rs.getString(3);
                String shipStreet = rs.getString(4);
                String shipCity = rs.getString(5);
                String shipProvince = rs.getString(6);
                String shipCountry = rs.getString(7);
                String shipEmail = rs.getString(8);
                String shipPhone = rs.getString(9);
                String status = rs.getString(10);
                String createdTime = rs.getString(11);
                float totalPrice = rs.getFloat(12);
                orders.add(new Order(id, customerId, receiver, shipStreet, shipCity, shipProvince, shipCountry, shipEmail, shipPhone, status, createdTime, totalPrice));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    public int deleteByCustomerId(int customerId) {
        int rowsAffected = 0;
        try {
            String sql = "SELECT id FROM [dbo].[Order]\n"
                    + " WHERE customerId = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, customerId);
            ResultSet rs = pre.executeQuery();
            OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
            OrderVoucherDAO orderVoucherDAO = new OrderVoucherDAO();
            while (rs.next()) {
                orderDetailsDAO.deleteByOrderId(rs.getInt(1));
                orderVoucherDAO.deleteByOrderId(rs.getInt(1));
            }

            sql = "DELETE FROM [dbo].[Order]\n"
                    + " WHERE customerId = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, customerId);
            int affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rowsAffected;
    }
}
