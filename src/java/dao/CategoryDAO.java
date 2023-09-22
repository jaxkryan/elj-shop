/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

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
                listC.add(new Category(id, name));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listC;
    }
}
