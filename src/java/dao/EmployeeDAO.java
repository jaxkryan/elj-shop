package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.User;
import java.time.format.DateTimeFormatter;

public class EmployeeDAO extends jdbc.DBConnect {
    public int insert(int userId) {
        int affectedRows = 0;
        try {
            String sql = "INSERT INTO [dbo].[Employee]\n"
                    + "           ([id]\n"
                    + "           ,[hireDate]\n"
                    + "           ,[salary])\n"
                    + "     VALUES\n"
                    + "           (?, ? ,1000)";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, userId);
            String hireDate = java.time.LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
            pre.setString(2, hireDate);
            affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return affectedRows;
    }
    
    public int store(User user) {
        int affectedRows = 0;
        if (user.getRole().equals("Manager")) {
            ImportOrderDAO importOrderDAO = new ImportOrderDAO();
            importOrderDAO.storeByManagerId(user.getId());
        }
        String sql = "UPDATE [dbo].[Employee]\n"
                + "   SET [leaveDate] = ?\n"
                + " WHERE id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            String leaveDate = java.time.LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
            pre.setString(1, leaveDate);
            pre.setInt(2, user.getId());
            affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return affectedRows;
    }
}
