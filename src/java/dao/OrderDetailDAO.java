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
import model.OrderDetail;

/**
 *
 * @author LENOVO
 */
public class OrderDetailDAO extends jdbc.DBConnect {

    public OrderDetail getById(int did) {
        String sql = "SELECT [id]\n"
                + "      ,[productId]\n"
                + "      ,[orderId]\n"
                + "      ,[price]\n"
                + "      ,[quantity]\n"
                + "  FROM [OnlineShop].[dbo].[OrderDetail]"
                + " where orderId = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, did);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int productID = rs.getInt(2);
                int orderID = rs.getInt(3);
                double price = rs.getDouble(4);
                int quantity = rs.getInt(5);
                return new OrderDetail(productID, orderID, price, quantity);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Vector<OrderDetail> getOrderDetailsById(int userId, int orderId) {
        Vector<OrderDetail> details = new Vector<>();
        String sql = "select * from orderdetails inner join [order] on orderdetails.orderId = [order].[id] where orderId = ? and customerId=? ";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, orderId);
            statement.setInt(2, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt(1);
                int orderID = rs.getInt(2);
                double price = rs.getDouble(3);
                int quantity = rs.getInt(4);
                details.add(new OrderDetail(productID, orderID, price, quantity));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return details;
    }

    public void deleteOrderDetail(int proId, int orderId) {
        String sql1 = "update [order] set totalprice=(totalprice* 1 / 1.1 - ? * ? )*1.1 where id = ? ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql1);
            pre.setFloat(1, priceToDelete(proId, orderId));
            pre.setInt(2, quanttyToDelete(proId, orderId));
            pre.setInt(3, orderId);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "delete from orderdetails where productid=? and orderid=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, proId);
            pre.setInt(2, orderId);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (priceOfOrder(orderId) <= 0) {
            String sql2 = "delete from [order] where id=?";
            try {
                PreparedStatement pre = conn.prepareStatement(sql2);
                pre.setInt(1, orderId);
                pre.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private float priceOfOrder(int orderId) {
        float price = 0;
        String sql = "select totalPrice from [order] where id=?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, orderId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getFloat(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return price;
    }

    private int quanttyToDelete(int proId, int orderId) {
        int quantity = 0;
        String sql = "select quantity from [orderdetails] where productid=? and orderid=?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, proId);
            statement.setInt(2, orderId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return quantity;
    }

    private float priceToDelete(int proId, int orderId) {
        float price = 0;
        String sql = "select price from [orderdetails] where productid=? and orderid=?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, proId);
            statement.setInt(2, orderId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getFloat(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return price;
    }

    public int updateProductQuantity(int orderId) {
        int rowsAffected = 0;
        String sql = "UPDATE P\n"
                + "SET P.quantity = P.quantity - OD.quantity\n"
                + "FROM OrderDetails OD\n"
                + "INNER JOIN Product P ON OD.productId = P.id\n"
                + "WHERE OD.orderId = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, orderId);
            rowsAffected = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
    }
    
    public int increaseProductQuantity(int orderId) {
        int rowsAffected = 0;
        String sql = "UPDATE P\n"
                + "SET P.quantity = P.quantity + OD.quantity\n"
                + "FROM OrderDetails OD\n"
                + "INNER JOIN Product P ON OD.productId = P.id\n"
                + "WHERE OD.orderId = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, orderId);
            rowsAffected = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
    }
    
    public Vector<OrderDetail> CheckOrdersQuantity(int orderId) {
        Vector<OrderDetail> orders = new Vector<>();
        String sql = "SELECT\n"
                + "    OD.productId,\n"
                + "    OD.orderId,\n"
                + "	OD.price,\n"
                + "    OD.quantity AS orderQuantity\n"
                + "FROM\n"
                + "    OrderDetails OD\n"
                + "JOIN\n"
                + "    Product P ON OD.productId = P.id\n"
                + "WHERE\n"
                + "    OD.orderId = ? AND OD.quantity > P.quantity;";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, orderId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt(1);
                int orderID = rs.getInt(2);
                double price = rs.getDouble(3);
                int quantity = rs.getInt(4);
                orders.add(new OrderDetail(productID, orderID, price, quantity)); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

}
