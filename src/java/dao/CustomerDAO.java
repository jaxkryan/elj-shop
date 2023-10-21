package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Customer;

public class CustomerDAO extends jdbc.DBConnect {
    public int insert(int userId) {
        int affectedRows = 0;
        try {
            String sql = "INSERT INTO [dbo].[Customer]\n"
                    + "           ([id]\n"
                    + "           ,[balance])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,1)";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, userId);
            affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return affectedRows;
    }

    public int delete(Customer customer) {
        int affectedRows = deleteById(customer.getId());

        return affectedRows;
    }

    public int deleteById(int customerId) {
        int affectedRows = 0;
        CartDAO cartDAO = new CartDAO();
        cartDAO.deleteByCustomerId(customerId);
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.storeByCustomerId(customerId);
        String sql = "DELETE FROM [dbo].[Customer]\n"
                + " WHERE id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, customerId);
            affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return affectedRows;
    }
}
