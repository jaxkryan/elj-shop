package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CartItem;
import model.Order;
import model.OrderDetail;
import model.User;
import model.Voucher;

public class OrderDAO extends jdbc.DBConnect {

    public Vector<Order> getAll() {
        Vector<Order> orders = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[customerId]\n"
                + "      ,[receiver]\n"
                + "      ,[shipStreet]\n"
                + "      ,[shipCity]\n"
                + "      ,[shipProvince]\n"
                + "      ,[shipCountry]\n"
                + "      ,[shipEmail]"
                + "      ,[shipPhone]\n"
                + "      ,[status]\n"
                + "      ,[createdTime]\n"
                + "      ,[totalPrice]\n"
                + "      ,[active]\n"
                + "  FROM [dbo].[Order]"
                + " where active = 1";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                int customerId = rs.getInt(2);
                String receiver = rs.getString(3);
                String shipStreet = rs.getString(4);
                String shipCity = rs.getString(5);
                String shipProvince = rs.getString(6);
                String shipCountry = rs.getString(7);
                String shipEmail = rs.getString(8);
                String shipPhone = rs.getString(9);
                String status = rs.getString(10);
                String createdTime = rs.getString(11);
                float totalPrice = rs.getFloat(12);
                Boolean active = rs.getBoolean(13);
                orders.add(new Order(id, customerId, receiver, shipStreet,
                        shipCity, shipProvince, shipCountry, shipEmail,
                        shipPhone, status, createdTime, totalPrice, active));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    public Order getById(int id) {
        String sql = "SELECT [id]\n"
                + "      ,[customerId]\n"
                + "      ,[receiver]\n"
                + "      ,[shipStreet]\n"
                + "      ,[shipCity]\n"
                + "      ,[shipProvince]\n"
                + "      ,[shipCountry]\n"
                + "      ,[shipEmail]"
                + "      ,[shipPhone]\n"
                + "      ,[status]\n"
                + "      ,[createdTime]\n"
                + "      ,[totalPrice]\n"
                + "      ,[active]\n"
                + "  FROM [dbo].[Order]"
                + " where id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt(2);
                String receiver = rs.getString(3);
                String shipStreet = rs.getString(4);
                String shipCity = rs.getString(5);
                String shipProvince = rs.getString(6);
                String shipCountry = rs.getString(7);
                String shipEmail = rs.getString(8);
                String shipPhone = rs.getString(9);
                String status = rs.getString(10);
                String createdTime = rs.getString(11);
                float totalPrice = rs.getFloat(12);
                Boolean active = rs.getBoolean(13);
                return new Order(id, customerId, receiver, shipStreet,
                        shipCity, shipProvince, shipCountry, shipEmail,
                        shipPhone, status, createdTime, totalPrice, true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Vector<Order> GetManagerManageOrder() {
        Vector<Order> orders = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[customerId]\n"
                + "      ,[receiver]\n"
                + "      ,[shipStreet]\n"
                + "      ,[shipCity]\n"
                + "      ,[shipProvince]\n"
                + "      ,[shipCountry]\n"
                + "      ,[shipEmail]\n"
                + "      ,[shipPhone]\n"
                + "      ,[status]\n"
                + "      ,[createdTime]\n"
                + "      ,[totalPrice]\n"
                + "      ,[active]\n"
                + "  FROM [dbo].[Order]\n"
                + "  where [status] like 'Shipping' and active = 1";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                int customerId = rs.getInt(2);
                String receiver = rs.getString(3);
                String shipStreet = rs.getString(4);
                String shipCity = rs.getString(5);
                String shipProvince = rs.getString(6);
                String shipCountry = rs.getString(7);
                String shipEmail = rs.getString(8);
                String shipPhone = rs.getString(9);
                String status = rs.getString(10);
                String createdTime = rs.getString(11);
                float totalPrice = rs.getFloat(12);
                Boolean active = rs.getBoolean(13);
                orders.add(new Order(id, customerId, receiver, shipStreet,
                        shipCity, shipProvince, shipCountry, shipEmail,
                        shipPhone, status, createdTime, totalPrice, active));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    public Vector<Order> GetSellerManageOrder() {
        Vector<Order> orders = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[customerId]\n"
                + "      ,[receiver]\n"
                + "      ,[shipStreet]\n"
                + "      ,[shipCity]\n"
                + "      ,[shipProvince]\n"
                + "      ,[shipCountry]\n"
                + "      ,[shipEmail]\n"
                + "      ,[shipPhone]\n"
                + "      ,[status]\n"
                + "      ,[createdTime]\n"
                + "      ,[totalPrice]\n"
                + "      ,[active]\n"
                + "  FROM [dbo].[Order]\n"
                + "  where active = 1";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                int customerId = rs.getInt(2);
                String receiver = rs.getString(3);
                String shipStreet = rs.getString(4);
                String shipCity = rs.getString(5);
                String shipProvince = rs.getString(6);
                String shipCountry = rs.getString(7);
                String shipEmail = rs.getString(8);
                String shipPhone = rs.getString(9);
                String status = rs.getString(10);
                String createdTime = rs.getString(11);
                float totalPrice = rs.getFloat(12);
                Boolean active = rs.getBoolean(13);
                orders.add(new Order(id, customerId, receiver, shipStreet,
                        shipCity, shipProvince, shipCountry, shipEmail,
                        shipPhone, status, createdTime, totalPrice, active));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    public Vector<Order> GetStorageManageOrder() {
        Vector<Order> orders = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[customerId]\n"
                + "      ,[receiver]\n"
                + "      ,[shipStreet]\n"
                + "      ,[shipCity]\n"
                + "      ,[shipProvince]\n"
                + "      ,[shipCountry]\n"
                + "      ,[shipEmail]\n"
                + "      ,[shipPhone]\n"
                + "      ,[status]\n"
                + "      ,[createdTime]\n"
                + "      ,[totalPrice]\n"
                + "      ,[active]\n"
                + "  FROM [dbo].[Order]\n"
                + "  where [status] like 'Accepted' and active = 1";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                int customerId = rs.getInt(2);
                String receiver = rs.getString(3);
                String shipStreet = rs.getString(4);
                String shipCity = rs.getString(5);
                String shipProvince = rs.getString(6);
                String shipCountry = rs.getString(7);
                String shipEmail = rs.getString(8);
                String shipPhone = rs.getString(9);
                String status = rs.getString(10);
                String createdTime = rs.getString(11);
                float totalPrice = rs.getFloat(12);
                Boolean active = rs.getBoolean(13);
                orders.add(new Order(id, customerId, receiver, shipStreet,
                        shipCity, shipProvince, shipCountry, shipEmail,
                        shipPhone, status, createdTime, totalPrice, active));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    public int changeOrderStatus(int orderId, String newStatus) {
        int rowsAffected = 0;
        String sql = "UPDATE [dbo].[Order]\n"
                + "   SET [status] = ?\n"
                + " WHERE id = ?;";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, newStatus);
            pre.setInt(2, orderId);
            rowsAffected = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
    }

    public Vector<Order> getProcessedOrderByName(String name) {
        Vector<Order> orders = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[customerId]\n"
                + "      ,[receiver]\n"
                + "      ,[shipStreet]\n"
                + "      ,[shipCity]\n"
                + "      ,[shipProvince]\n"
                + "      ,[shipCountry]\n"
                + "      ,[shipEmail]"
                + "      ,[shipPhone]\n"
                + "      ,[status]\n"
                + "      ,[createdTime]\n"
                + "      ,[totalPrice]\n"
                + "      ,[active]\n"
                + "  FROM [dbo].[Order]"
                + " where receiver like ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int customerId = rs.getInt(2);
                String receiver = rs.getString(3);
                String shipStreet = rs.getString(4);
                String shipCity = rs.getString(5);
                String shipProvince = rs.getString(6);
                String shipCountry = rs.getString(7);
                String shipEmail = rs.getString(8);
                String shipPhone = rs.getString(9);
                String status = rs.getString(10);
                String createdTime = rs.getString(11);
                float totalPrice = rs.getFloat(12);
                Boolean active = rs.getBoolean(13);
                orders.add(new Order(id, customerId, receiver, shipStreet,
                        shipCity, shipProvince, shipCountry, shipEmail,
                        shipPhone, status, createdTime, totalPrice, active));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    public Vector<Order> getShippedOrdersByName(String name) {
        Vector<Order> orders = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[customerId]\n"
                + "      ,[receiver]\n"
                + "      ,[shipStreet]\n"
                + "      ,[shipCity]\n"
                + "      ,[shipProvince]\n"
                + "      ,[shipCountry]\n"
                + "      ,[shipEmail]"
                + "      ,[shipPhone]\n"
                + "      ,[status]\n"
                + "      ,[createdTime]\n"
                + "      ,[totalPrice]\n"
                + "      ,[active]\n"
                + "  FROM [dbo].[Order]"
                + " where receiver like ? and status like 'Shipping'";

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int customerId = rs.getInt(2);
                String receiver = rs.getString(3);
                String shipStreet = rs.getString(4);
                String shipCity = rs.getString(5);
                String shipProvince = rs.getString(6);
                String shipCountry = rs.getString(7);
                String shipEmail = rs.getString(8);
                String shipPhone = rs.getString(9);
                String status = rs.getString(10);
                String createdTime = rs.getString(11);
                float totalPrice = rs.getFloat(12);
                Boolean active = rs.getBoolean(13);
                orders.add(new Order(id, customerId, receiver, shipStreet,
                        shipCity, shipProvince, shipCountry, shipEmail,
                        shipPhone, status, createdTime, totalPrice, active));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    public Vector<Order> getOrdersByStatus(String status) {
        Vector<Order> orders = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[customerId]\n"
                + "      ,[receiver]\n"
                + "      ,[shipStreet]\n"
                + "      ,[shipCity]\n"
                + "      ,[shipProvince]\n"
                + "      ,[shipCountry]\n"
                + "      ,[shipEmail]"
                + "      ,[shipPhone]\n"
                + "      ,[createdTime]\n"
                + "      ,[totalPrice]\n"
                + "      ,[active]\n"
                + "  FROM [dbo].[Order]"
                + " where status like ?";

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, status);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int customerId = rs.getInt(2);
                String receiver = rs.getString(3);
                String shipStreet = rs.getString(4);
                String shipCity = rs.getString(5);
                String shipProvince = rs.getString(6);
                String shipCountry = rs.getString(7);
                String shipEmail = rs.getString(8);
                String shipPhone = rs.getString(9);
                String createdTime = rs.getString(10);
                float totalPrice = rs.getFloat(11);
                Boolean active = rs.getBoolean(12);
                orders.add(new Order(id, customerId, receiver, shipStreet,
                        shipCity, shipProvince, shipCountry, shipEmail,
                        shipPhone, status, createdTime, totalPrice, active));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    public Vector<Order> getSortedShippedOrdersByName(String sort, String name) {
        Vector<Order> orders = new Vector<>();
        String sql = "SELECT [id],"
                + " [customerId],"
                + " [receiver],"
                + " [shipStreet],"
                + " [shipCity],"
                + " [shipProvince],"
                + " [shipCountry],"
                + " [shipEmail],"
                + " [shipPhone],"
                + " [status],"
                + " [createdTime],"
                + " [totalPrice],"
                + " [active]"
                + " FROM [dbo].[Order]"
                + " WHERE receiver LIKE ? AND status = 'Shipping'";

        if ("ASC".equals(sort)) {
            sql += " ORDER BY createdTime ASC";
        } else if ("DESC".equals(sort)) {
            sql += " ORDER BY createdTime DESC";
        }

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int customerId = rs.getInt(2);
                String receiver = rs.getString(3);
                String shipStreet = rs.getString(4);
                String shipCity = rs.getString(5);
                String shipProvince = rs.getString(6);
                String shipCountry = rs.getString(7);
                String shipEmail = rs.getString(8);
                String shipPhone = rs.getString(9);
                String status = rs.getString(10);
                String createdTime = rs.getString(11);
                float totalPrice = rs.getFloat(12);
                Boolean active = rs.getBoolean(13);
                orders.add(new Order(id, customerId, receiver, shipStreet,
                        shipCity, shipProvince, shipCountry, shipEmail,
                        shipPhone, status, createdTime, totalPrice, active));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    public Vector<Order> getSortedProcessedOrdersByName(String sort, String name) {
        Vector<Order> orders = new Vector<>();
        String sql = "SELECT [id],"
                + " [customerId],"
                + " [receiver],"
                + " [shipStreet],"
                + " [shipCity],"
                + " [shipProvince],"
                + " [shipCountry],"
                + " [shipEmail],"
                + " [shipPhone],"
                + " [status],"
                + " [createdTime],"
                + " [totalPrice],"
                + " [active]"
                + " FROM [dbo].[Order]"
                + " WHERE receiver LIKE ? ";

        if ("ASC".equals(sort)) {
            sql += " ORDER BY createdTime ASC";
        } else if ("DESC".equals(sort)) {
            sql += " ORDER BY createdTime DESC";
        }

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int customerId = rs.getInt(2);
                String receiver = rs.getString(3);
                String shipStreet = rs.getString(4);
                String shipCity = rs.getString(5);
                String shipProvince = rs.getString(6);
                String shipCountry = rs.getString(7);
                String shipEmail = rs.getString(8);
                String shipPhone = rs.getString(9);
                String status = rs.getString(10);
                String createdTime = rs.getString(11);
                float totalPrice = rs.getFloat(12);
                Boolean active = rs.getBoolean(13);
                orders.add(new Order(id, customerId, receiver, shipStreet,
                        shipCity, shipProvince, shipCountry, shipEmail,
                        shipPhone, status, createdTime, totalPrice, active));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    public Vector<Order> filterOrders(String status, String name, String sortType) {
        if (status.isEmpty() || status.equals("All")) {
            return getProcessedOrderByName(name);
        } else if (name.isEmpty()) {
            return getOrdersByStatus(status);
        }
        Vector<Order> orders = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[customerId]\n"
                + "      ,[receiver]\n"
                + "      ,[shipStreet]\n"
                + "      ,[shipCity]\n"
                + "      ,[shipProvince]\n"
                + "      ,[shipCountry]\n"
                + "      ,[shipEmail]"
                + "      ,[shipPhone]\n"
                + "      ,[createdTime]\n"
                + "      ,[totalPrice]\n"
                + "      ,[active]\n"
                + "  FROM [dbo].[Order]"
                + " where status = ? and receiver like ?"
                + (sortType.isEmpty() ? "" : " order by createdTime " + (sortType.equals("ASC") ? "" : "DESC"));
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, status);
            statement.setString(2, "%" + name + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int customerId = rs.getInt(2);
                String receiver = rs.getString(3);
                String shipStreet = rs.getString(4);
                String shipCity = rs.getString(5);
                String shipProvince = rs.getString(6);
                String shipCountry = rs.getString(7);
                String shipEmail = rs.getString(8);
                String shipPhone = rs.getString(9);
                String createdTime = rs.getString(10);
                float totalPrice = rs.getFloat(11);
                Boolean active = rs.getBoolean(12);
                orders.add(new Order(id, customerId, receiver, shipStreet,
                        shipCity, shipProvince, shipCountry, shipEmail,
                        shipPhone, status, createdTime, totalPrice, active));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    public Vector<Order> getAcceptedOrdersByName(String name) {
        Vector<Order> orders = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[customerId]\n"
                + "      ,[receiver]\n"
                + "      ,[shipStreet]\n"
                + "      ,[shipCity]\n"
                + "      ,[shipProvince]\n"
                + "      ,[shipCountry]\n"
                + "      ,[shipEmail]"
                + "      ,[shipPhone]\n"
                + "      ,[status]\n"
                + "      ,[createdTime]\n"
                + "      ,[totalPrice]\n"
                + "      ,[active]\n"
                + "  FROM [dbo].[Order]"
                + " where receiver like ? and status like 'Accepted'";

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int customerId = rs.getInt(2);
                String receiver = rs.getString(3);
                String shipStreet = rs.getString(4);
                String shipCity = rs.getString(5);
                String shipProvince = rs.getString(6);
                String shipCountry = rs.getString(7);
                String shipEmail = rs.getString(8);
                String shipPhone = rs.getString(9);
                String status = rs.getString(10);
                String createdTime = rs.getString(11);
                float totalPrice = rs.getFloat(12);
                Boolean active = rs.getBoolean(13);
                orders.add(new Order(id, customerId, receiver, shipStreet,
                        shipCity, shipProvince, shipCountry, shipEmail,
                        shipPhone, status, createdTime, totalPrice, active));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    public int updateOrderInfo(Order order) {
        int rowsAffected = 0;
        String sql = "UPDATE [dbo].[Order]\n"
                + "   SET [customerId] = ?\n"
                + "    ,[receiver] = ?\n"
                + "    ,[shipStreet] = ?\n"
                + "    ,[shipCity] = ?\n"
                + "    ,[shipProvince] = ?\n"
                + "    ,[shipCountry] = ?\n"
                + "    ,[shipEmail] = ?\n"
                + "    ,[shipPhone] = ?\n"
                + "    ,[status] = ?\n"
                + "    ,[createdTime] = ?\n"
                + "    ,[totalPrice] = ?\n"
                + " WHERE id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, order.getCustomerId());
            pre.setString(2, order.getReceiver());
            pre.setString(3, order.getShipStreet());
            pre.setString(4, order.getShipCity());
            pre.setString(5, order.getShipProvince());
            pre.setString(6, order.getShipCountry());
            pre.setString(7, order.getShipEmail());
            pre.setString(8, order.getShipPhone());
            pre.setString(9, order.getStatus());
            pre.setString(10, order.getCreatedTime());
            pre.setFloat(11, order.getTotalPrice());
            pre.setInt(12, order.getId());
            rowsAffected = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rowsAffected;
    }

    public int deleteOrder(int orderId) {
        int rowsAffected = 0;
        String sql = "UPDATE [dbo].[Order]\n"
                + "   SET [active] = 0\n"
                + " WHERE id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, orderId);
            pre.executeUpdate();
            rowsAffected = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
    }

    public void deleteHistory(int orderId) {
        String sql = "delete from [orderdetails] WHERE orderid = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, orderId);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql1 = "delete from [order] WHERE id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql1);
            pre.setInt(1, orderId);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int storeByCustomerId(int customerId) {
        int affectedRows = 0;
        try {
            String sql = "UPDATE [dbo].[Order]\n"
                    + "   SET [active] = 'false'\n"
                    + " WHERE customerId = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, customerId);
            affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return affectedRows;
    }

    public void createOrderWithoutVoucher(int userId, String receiver, String street, String city, String province, String country, String email, String phone, String orderDate, Float orderPrice, Vector<CartItem> cartItem) {
        String sql = "INSERT [Order] ([customerId], [receiver], [shipStreet], [shipCity], [shipProvince], [shipCountry], [shipEmail], [shipPhone], [status], [createdTime], [totalPrice],[active]) "
                + "VALUES "
                + "( ? , ? , ? , ? , ? , ? , ? , ? , 'Processing', ? , ? , 1 ) ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, userId);
            pre.setString(2, receiver);
            pre.setString(3, street);
            pre.setString(4, city);
            pre.setString(5, province);
            pre.setString(6, country);
            pre.setString(7, email);
            pre.setString(8, phone);
            pre.setString(9, orderDate);
            pre.setFloat(10, orderPrice);
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        for (int i = 0; i < cartItem.size(); i++) {
            String sqls = "INSERT [OrderDetails] ([productId], [orderId], [price], [quantity]) \n"
                    + "VALUES (?, ?, ?, ?)";
            try {
                PreparedStatement pre = conn.prepareStatement(sqls);
                pre.setInt(1, cartItem.get(i).getProductId());
                pre.setInt(2, getLastOrderId());
                pre.setFloat(3, cartItem.get(i).getPrice());
                pre.setInt(4, cartItem.get(i).getQuantity());
                pre.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        String sqlss = "delete from [cartItem] where cartid = ? ";
        try {
            PreparedStatement pre = conn.prepareStatement(sqlss);
            pre.setInt(1, cartItem.get(0).getCartId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private int getLastOrderId() {
        int lastOrrderId = 0;
        String sql = "select id from [order] order by id desc";
        try {
            ResultSet rs = getData(sql);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastOrrderId;
    }

    public void createOrderWithVoucher(int userId, Voucher voucher, String receiver, String street, String city, String province, String country, String email, String phone, String orderDate, Float orderPrice, Vector<CartItem> cartItem) {
        String sql = "INSERT [Order] ([customerId], [voucherId], [receiver], [shipStreet], [shipCity], [shipProvince], [shipCountry], [shipEmail], [shipPhone], [status], [createdTime], [totalPrice],[active]) "
                + "VALUES "
                + "( ? , ? , ? , ? , ? , ? , ? , ? , ? , 'Processing', ? , ? , 1 ) ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, userId);
            pre.setInt(2, voucher.getId());
            pre.setString(3, receiver);
            pre.setString(4, street);
            pre.setString(5, city);
            pre.setString(6, province);
            pre.setString(7, country);
            pre.setString(8, email);
            pre.setString(9, phone);
            pre.setString(10, orderDate);
            pre.setFloat(11, (float) (orderPrice * (1 - voucher.getValue() / 100)));
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        for (int i = 0; i < cartItem.size(); i++) {
            String sqls = "INSERT [OrderDetails] ([productId], [orderId], [price], [quantity]) \n"
                    + "VALUES (?, ?, ?, ?)";
            try {
                PreparedStatement pre = conn.prepareStatement(sqls);
                pre.setInt(1, cartItem.get(i).getProductId());
                pre.setInt(2, getLastOrderId());
                pre.setFloat(3, (float) (cartItem.get(i).getPrice() * (1 - voucher.getValue() / 100)));
                pre.setInt(4, cartItem.get(i).getQuantity());
                pre.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        String sqlss = "delete from [cartItem] where cartid = ? ";
        try {
            PreparedStatement pre = conn.prepareStatement(sqlss);
            pre.setInt(1, cartItem.get(0).getCartId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Vector<Order> getAllOrderById(int userId) {
        Vector<Order> orders = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[customerId]\n"
                + "      ,[receiver]\n"
                + "      ,[shipStreet]\n"
                + "      ,[shipCity]\n"
                + "      ,[shipProvince]\n"
                + "      ,[shipCountry]\n"
                + "      ,[shipEmail]\n"
                + "      ,[shipPhone]"
                + "      ,[status]\n"
                + "      ,[createdTime]\n"
                + "      ,[totalPrice]\n"
                + "      ,[active]\n"
                + "  FROM [dbo].[Order] where customerId = ? ";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int customerId = rs.getInt(2);
                String receiver = rs.getString(3);
                String shipStreet = rs.getString(4);
                String shipCity = rs.getString(5);
                String shipProvince = rs.getString(6);
                String shipCountry = rs.getString(7);
                String shipEmail = rs.getString(8);
                String shipPhone = rs.getString(9);
                String status = rs.getString(10);
                String createdTime = rs.getString(11);
                float totalPrice = rs.getFloat(12);
                Boolean active = rs.getBoolean(13);
                orders.add(new Order(id, customerId, receiver, shipStreet,
                        shipCity, shipProvince, shipCountry, shipEmail, shipPhone,
                        status, createdTime, totalPrice, true));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    public Vector<Order> getAllOrderByCustomerId(int userId) {
        Vector<Order> orders = new Vector<>();
        String sql = "SELECT [id]\n"
                + "      ,[customerId]\n"
                + "      ,[receiver]\n"
                + "      ,[shipStreet]\n"
                + "      ,[shipCity]\n"
                + "      ,[shipProvince]\n"
                + "      ,[shipCountry]\n"
                + "      ,[shipEmail]\n"
                + "      ,[shipPhone]"
                + "      ,[status]\n"
                + "      ,[createdTime]\n"
                + "      ,[totalPrice]\n"
                + "      ,[active]\n"
                + "  FROM [dbo].[Order] where customerId = ? and active=1 order by [createdTime] desc ";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int customerId = rs.getInt(2);
                String receiver = rs.getString(3);
                String shipStreet = rs.getString(4);
                String shipCity = rs.getString(5);
                String shipProvince = rs.getString(6);
                String shipCountry = rs.getString(7);
                String shipEmail = rs.getString(8);
                String shipPhone = rs.getString(9);
                String status = rs.getString(10);
                String createdTime = rs.getString(11);
                float totalPrice = rs.getFloat(12);
                Boolean active = rs.getBoolean(13);
                orders.add(new Order(id, customerId, receiver, shipStreet,
                        shipCity, shipProvince, shipCountry, shipEmail, shipPhone,
                        status, createdTime, totalPrice, true));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO();
        Vector<Order> orders = orderDAO.getSortedShippedOrdersByName("DESC", "a");

        if (!orders.isEmpty()) {
            System.out.println(orders.firstElement().getReceiver());
        } else {
            System.out.println("No orders found.");
        }
    }

}
