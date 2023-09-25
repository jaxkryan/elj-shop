package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.User;

public class EmployeeDAO extends jdbc.DBConnect{
    public int deleteById(int employeeId, String role) {
        int rowsAffected = 0;
        if (role.equals("Manager")) {
            ImportOrderDAO importOrderDAO = new ImportOrderDAO(); 
            importOrderDAO.deleteByManagerId(employeeId);
        }
        String sql = "DELETE FROM [dbo].[Employee]\n"
                + " WHERE id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, employeeId);
            int affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rowsAffected;
    }
}
