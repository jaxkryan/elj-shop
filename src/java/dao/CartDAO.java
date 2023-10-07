package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartDAO extends jdbc.DBConnect {
    public int deleteByCustomerId(int customerId) {
        int affectedRows = 0;
        try {
            String sql = "SELECT id FROM [dbo].[Cart]\n"
                    + " WHERE customerId = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, customerId);
            ResultSet rs = pre.executeQuery();
            CartItemDAO cartItemDAO = new CartItemDAO();
            while (rs.next()) {
                cartItemDAO.deleteByCartId(rs.getInt(1));
            }

            sql = "DELETE FROM [dbo].[Cart]\n"
                    + " WHERE customerId = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, customerId);
            affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return affectedRows;
    }
}
