package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImportOrderDAO extends jdbc.DBConnect {
    public int deleteByManagerId(int managerId) {
        int rowsAffected = 0;
        try {
            String sql = "SELECT id FROM [dbo].[ImportOrder]\n"
                    + " WHERE managerId = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, managerId);
            ResultSet rs = pre.executeQuery();
            ImportOrderDetailsDAO importOrderDetailsDAO = new ImportOrderDetailsDAO();
            while (rs.next()) {
                importOrderDetailsDAO.deleteByImportOrderId(rs.getInt(1));
            }

            sql = "DELETE FROM [dbo].[ImportOrder]\n"
                    + " WHERE managerId = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, managerId);
            int affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rowsAffected;
    }
}
