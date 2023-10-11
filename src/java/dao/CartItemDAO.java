package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Product;

public class CartItemDAO extends jdbc.DBConnect {

    public int deleteByCartId(int cartId) {
        int affectedRows = 0;
        String sql = "DELETE FROM [dbo].[CartItem]\n"
                + " WHERE cartId = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, cartId);
            affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return affectedRows;
    }

    public void addToCart(Product product, int cartId) {
        if (!existed(product.getId(), cartId)) {
            String sql = "INSERT INTO [CartItem] ([productId], [cartId], [price], [quantity]) \n"
                    + "VALUES (?, ?, ?, 1)";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setInt(1, product.getId());
                pre.setInt(2, cartId);
                pre.setFloat(3, product.getPrice());
                pre.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("Product already exists in cart. 😊");
        }
    }

    private boolean existed(int productId, int cartId) {
        String sql = "SELECT * FROM CartItem WHERE productId = ? AND cartId = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, productId);
            pre.setInt(2, cartId);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
