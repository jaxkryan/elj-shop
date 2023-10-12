package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;

/**
 *
 * @author Admin
 */
public class CategoryDAO extends jdbc.DBConnect {

    public Vector<Category> getAllCategory() {
        Vector<Category> listC = new Vector<>();
        String sql = "SELECT * from category";
        try {

            ResultSet rs = getData(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String image = rs.getString(3);
                listC.add(new Category(id, name, image));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listC;
    }

    public Category getCategoryById(int cateId) {
        try {
            String sql = "SELECT * from category where id = ? ";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, cateId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                return (new Category(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
