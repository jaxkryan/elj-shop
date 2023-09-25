package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImportOrderDetailsDAO extends jdbc.DBConnect {
    public int deleteByImportOrderId(int importOrderId) {
        int rowsAffected = 0;
        String sql = "DELETE FROM [dbo].[ImportOrderDetails]\n"
                + " WHERE importOrderId = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, importOrderId);
            int affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rowsAffected;
    }
}
