package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImportOrderDAO extends jdbc.DBConnect {
    public int storeByManagerId(int managerId) {
        int affectedRows = 0;
        try {
            String sql = "UPDATE [dbo].[ImportOrder]\n"
                    + "   SET [active] = 'false'\n"
                    + " WHERE managerId = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, managerId);
            affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return affectedRows;
    }
}
