package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listP;
    }

    public Vector<Product> getHotProducts() {
        Vector<Product> listP = new Vector<>();
        String sql = "select top 3 * from product\n"
                + "order by quantity";
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
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listP;
    }
    public Vector<Product> searchProducts(String statement, String sortOrder) {
        String sql = statement + sortOrder;
        Vector<Product> searchlistProduct = new Vector<>();
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
                searchlistProduct.add(new Product(id, categoryId, providerId, name, description, price, discount, quantity, image));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return searchlistProduct;
    }
    
    public Vector<Product> sortProducts(String sortField, String sortOrder) {
        String sql = "SELECT * FROM product ORDER BY " + sortField + " " + sortOrder;
        Vector<Product> sortlistProduct = new Vector<>();
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
                sortlistProduct.add(new Product(id, categoryId, providerId, name, description, price, discount, quantity, image));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sortlistProduct;
    }

    public int updateQty(int id, int qty) throws SQLException {
        String sql = "UPDATE Product\n"
                + "SET quantity = ? \n"
                + "WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, qty);
        statement.setInt(2, id);
        int n = statement.executeUpdate();
        return n;
    }
}
