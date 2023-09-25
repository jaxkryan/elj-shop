package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderVoucherDAO extends jdbc.DBConnect{
    public int deleteByOrderId(int orderId) {
        int rowsAffected = 0;
        String sql = "DELETE FROM [dbo].[OrderVoucher]\n"
                + " WHERE orderId = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, orderId);
            int affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rowsAffected;
    }
}
