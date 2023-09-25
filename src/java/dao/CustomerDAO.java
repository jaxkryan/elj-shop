package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Customer;

public class CustomerDAO extends jdbc.DBConnect{
    public int delete(Customer customer) {
        int rowsAffected = 0;
        CartDAO cartDAO = new CartDAO();
        cartDAO.deleteByCustomerId(customer.getId());
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.deleteByCustomerId(customer.getId());
        String sql = "DELETE FROM [dbo].[Customer]\n"
                + " WHERE id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, customer.getId());
            int affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rowsAffected;
    }
    
    public int deleteById(int customerId) {
        int rowsAffected = 0;
        CartDAO cartDAO = new CartDAO();
        cartDAO.deleteByCustomerId(customerId);
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.deleteByCustomerId(customerId);
        String sql = "DELETE FROM [dbo].[Customer]\n"
                + " WHERE id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, customerId);
            int affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rowsAffected;
    }
}
