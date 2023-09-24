package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

public class UserDAO extends jdbc.DBConnect{
    public Vector<User> getAll() {
        Vector<User> users = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[roleId]\n"
                + "      ,[firstName]\n"
                + "      ,[lastName]\n"
                + "      ,[dateOfBirth]\n"
                + "      ,[street]\n"
                + "      ,[city]\n"
                + "      ,[province]\n"
                + "      ,[country]"
                + "      ,[phone]\n"
                + "      ,[email]\n"
                + "      ,[password]\n"
                + "  FROM [dbo].[User]";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                int roleId = rs.getInt(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String dateOfBirth = rs.getString(5);
                String street = rs.getString(6);
                String city = rs.getString(7);
                String province = rs.getString(8);
                String country = rs.getString(9);
                String phone = rs.getString(10);
                String email = rs.getString(11);
                String password = rs.getString(12);
                users.add(new User(id, roleId, firstName, lastName, dateOfBirth, street, city, province, country, phone, email, password));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    public User getById(int id) {
        String sql = "SELECT [id]\n"
                + "      ,[roleId]\n"
                + "      ,[firstName]\n"
                + "      ,[lastName]\n"
                + "      ,[dateOfBirth]\n"
                + "      ,[street]\n"
                + "      ,[city]\n"
                + "      ,[province]\n"
                + "      ,[country]"
                + "      ,[phone]\n"
                + "      ,[email]\n"
                + "      ,[password]\n"
                + "  FROM [dbo].[User]"
                + " where id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int roleId = rs.getInt(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String dateOfBirth = rs.getString(5);
                String street = rs.getString(6);
                String city = rs.getString(7);
                String province = rs.getString(8);
                String country = rs.getString(9);
                String phone = rs.getString(10);
                String email = rs.getString(11);
                String password = rs.getString(12);
                return new User(id, roleId, firstName, lastName, dateOfBirth, street, city, province, country, phone, email, password);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Vector<User> getByName(String name) {
        Vector<User> users = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[roleId]\n"
                + "      ,[firstName]\n"
                + "      ,[lastName]\n"
                + "      ,[dateOfBirth]\n"
                + "      ,[street]\n"
                + "      ,[city]\n"
                + "      ,[province]\n"
                + "      ,[country]"
                + "      ,[phone]\n"
                + "      ,[email]\n"
                + "      ,[password]\n"
                + "  FROM [dbo].[User]"
                + " where firstName like ? or lastName like ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + name + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int roleId = rs.getInt(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String dateOfBirth = rs.getString(5);
                String street = rs.getString(6);
                String city = rs.getString(7);
                String province = rs.getString(8);
                String country = rs.getString(9);
                String phone = rs.getString(10);
                String email = rs.getString(11);
                String password = rs.getString(12);
                users.add(new User(id, roleId, firstName, lastName, dateOfBirth, street, city, province, country, phone, email, password));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    public User getByEmail(String email) {
        String sql = "SELECT [id]\n"
                + "      ,[roleId]\n"
                + "      ,[firstName]\n"
                + "      ,[lastName]\n"
                + "      ,[dateOfBirth]\n"
                + "      ,[street]\n"
                + "      ,[city]\n"
                + "      ,[province]\n"
                + "      ,[country]"
                + "      ,[phone]\n"
                + "      ,[password]\n"
                + "  FROM [dbo].[User]"
                + " where email = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int roleId = rs.getInt(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String dateOfBirth = rs.getString(5);
                String street = rs.getString(6);
                String city = rs.getString(7);
                String province = rs.getString(8);
                String country = rs.getString(9);
                String phone = rs.getString(10);
                String password = rs.getString(11);
                return new User(id, roleId, firstName, lastName, dateOfBirth, street, city, province, country, phone, email, password);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int addUser(User user) {
        int rowsAffected = 0;
        String sql = "INSERT INTO [dbo].[User]\n"
                + "      ,[roleId]\n"
                + "      ,[firstName]\n"
                + "      ,[lastName]\n"
                + "      ,[dateOfBirth]\n"
                + "      ,[street]\n"
                + "      ,[city]\n"
                + "      ,[province]\n"
                + "      ,[country]"
                + "      ,[phone]\n"
                + "      ,[email]\n"
                + "      ,[password]\n"
                + "     VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, user.getRoleId());
            pre.setString(2, user.getFirstName());
            pre.setString(3, user.getLastName());
            pre.setString(4, user.getDateOfBirth());
            pre.setString(5, user.getStreet());
            pre.setString(6, user.getCity());
            pre.setString(7, user.getProvince());
            pre.setString(8, user.getCountry());
            pre.setString(9, user.getPhone());
            pre.setString(10, user.getEmail());
            pre.setString(11, user.getPassword());
            
            int affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rowsAffected;
    }

    public int addUser(User user, boolean returnId) {
        if (!returnId) {
            return addUser(user);
        }
        int userId = -1;
        String sql = "INSERT INTO [dbo].[User]\n"
                + "      ,[roleId]\n"
                + "      ,[firstName]\n"
                + "      ,[lastName]\n"
                + "      ,[dateOfBirth]\n"
                + "      ,[street]\n"
                + "      ,[city]\n"
                + "      ,[province]\n"
                + "      ,[country]"
                + "      ,[phone]\n"
                + "      ,[email]\n"
                + "      ,[password]\n"
                + "     VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setInt(1, user.getRoleId());
            pre.setString(2, user.getFirstName());
            pre.setString(3, user.getLastName());
            pre.setString(4, user.getDateOfBirth());
            pre.setString(5, user.getStreet());
            pre.setString(6, user.getCity());
            pre.setString(7, user.getProvince());
            pre.setString(8, user.getCountry());
            pre.setString(9, user.getPhone());
            pre.setString(10, user.getEmail());
            pre.setString(11, user.getPassword());

            int affectedRows = pre.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Adding user failed, no rows affected.");
            }

            ResultSet generatedKeys = pre.getGeneratedKeys();
            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Adding user failed, no ID obtained.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return userId;
    }

    /**
     * Update user's information except roleId and password
     * 
     * @param user user to update
     * @return number of affected rows in database
     */
    public int updateUser(User user) {
        int rowsAffected = 0;
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET [firstName] = ?\n"
                + "      ,[lastName] = ?\n"
                + "      ,[dateOfBirth] = ?\n"
                + "      ,[street] = ?\n"
                + "      ,[city] = ?\n"
                + "      ,[province] = ?\n"
                + "      ,[country] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[email] = ?\n"
                + " WHERE id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, user.getFirstName());
            pre.setString(2, user.getLastName());
            pre.setString(3, user.getDateOfBirth());
            pre.setString(4, user.getStreet());
            pre.setString(5, user.getCity());
            pre.setString(6, user.getProvince());
            pre.setString(7, user.getCountry());
            pre.setString(8, user.getPhone());
            pre.setString(9, user.getEmail());
            pre.setInt(10, user.getId());
            int affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rowsAffected;
    }

    public int deleteUser(User user) {
        int rowsAffected = 0;
        
        //not delete user related info yet
        
        String sql = "DELETE FROM [dbo].[User]\n"
                + " WHERE id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, user.getId());
            int affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rowsAffected;
    }
}
