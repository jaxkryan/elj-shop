package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CartItem;
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
            String sql = "update [dbo].CartItem\n"
                    + "                set quantity = (select top 1 quantity from cartItem where productId = ? and cartId = ? ) +1\n"
                    + "                where productId = ? and cartId = ? ";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setInt(1, product.getId());
                pre.setInt(2, cartId);
                pre.setInt(3, product.getId());
                pre.setInt(4, cartId);
                pre.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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

    public Vector<CartItem> getCartItemByCartId(int cartId) {
        Vector<CartItem> cartItems = new Vector<>();
        String sql = "select * from [CartItem] where [cartId] = ? ";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, cartId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                cartItems.add(new CartItem(rs.getInt(1),
                        rs.getInt(2),
                        rs.getFloat(3),
                        rs.getInt(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cartItems;
    }

    public void updateCartItemQuantity(int productId, int cartId, String change) {
        if (change.equals("minus")) {
            String sql = "update [dbo].CartItem\n"
                    + "                set quantity = (select top 1 quantity from cartItem where productId = ? and cartId = ? ) -1\n"
                    + "                where productId = ? and cartId = ? ";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setInt(1, productId);
                pre.setInt(2, cartId);
                pre.setInt(3, productId);
                pre.setInt(4, cartId);
                pre.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            String sql = "update [dbo].CartItem\n"
                    + "                set quantity = (select top 1 quantity from cartItem where productId = ? and cartId = ? ) +1\n"
                    + "                where productId = ? and cartId = ? ";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setInt(1, productId);
                pre.setInt(2, cartId);
                pre.setInt(3, productId);
                pre.setInt(4, cartId);
                pre.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void updateQuantity(int productId, int cartIdOfItem, int quantity) {
        String sql = "update [dbo].CartItem\n"
                + "                set quantity = ? \n"
                + "                where productId = ? and cartId = ? ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, quantity);
            pre.setInt(2, productId);
            pre.setInt(3, cartIdOfItem);
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteCartItem(int productId, int cartId) {
        String sql = "DELETE FROM [dbo].[CartItem]\n"
                + " WHERE productId = ? and cartId = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, productId);
            pre.setInt(2, cartId);
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
