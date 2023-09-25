package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO extends jdbc.DBConnect {
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
