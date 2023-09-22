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
import model.Product;

/**
 *
 * @author Admin
 */
public class ProductDAO extends jdbc.DBConnect {

    public Vector<Product> getAllProduct() {
        Vector<Product> listP = new Vector<>();
        String sql = "SELECT * from product";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                int categoryId = rs.getInt(2);
                int providerId = rs.getInt(3);
                String name = rs.getString(4);
                String description = rs.getString(5);
                float price = rs.getFloat(6);
                float discount = rs.getFloat(7);
                int quantity = rs.getInt(8);
                String image = rs.getString(9);
                listP.add(new Product(id, categoryId, providerId, name, description, price, discount, quantity, image));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listP;
    }

    public Product getHotProduct() {
        String sql = "select top 1 * from product\n"
                + "order by quantity";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getFloat(6),
                        rs.getFloat(7),
                        rs.getInt(8),
                        rs.getString(9));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
