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

    public int updateProduct(Product product) {
        int rowsAffected = 0;
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [categoryId] = ?\n"
                + "      ,[providerId] = ?\n"
                + "      ,[name] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[discount] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[image] = ?\n"
                + " WHERE id = ?";
        PreparedStatement pre;
        try {
            pre = conn.prepareStatement(sql);
            pre.setInt(1, product.getCategoryId());
            pre.setInt(2, product.getProviderId());
            pre.setString(3, product.getName());
            pre.setString(4, product.getDescription());
            pre.setDouble(5, product.getPrice());
            pre.setDouble(6, product.getDiscount());
            pre.setInt(7, product.getQuantity());
            pre.setString(8, product.getImage());
            pre.setInt(9, product.getId());
            int affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
    }

    public void deleteProduct(String id) {
        setNullOrderDetail(id);
        setNullCartItem(id);
        String sql = "DELETE FROM [dbo].[Product]\n"
                + "      WHERE id=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setNullOrderDetail(String id) {
        String sql = "update [dbo].OrderDetail\n"
                + "set productId = null\n"
                + "where productId = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setNullCartItem(String id) {
        String sql = "update [dbo].CartItem\n"
                + "set productId = null\n"
                + "where productId = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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
