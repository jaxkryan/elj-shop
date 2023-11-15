package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
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
        String sql = "SELECT * from product where active = 1";
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
                Boolean active = rs.getBoolean(10);
                listP.add(new Product(id, categoryId, providerId, name, description, price, discount, quantity, image, active));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listP;
    }

    public List<String> getAllProductName() {
        List<String> listProductName = new Vector<>();
        String sql = "SELECT Name from product where active = 1";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                String name = rs.getString(1);
                listProductName.add(name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProductName;
    }

    public boolean checkUpdateProductNameExistence(List<String> listProductName, String productName) {
        int count = 0;
        String lowercaseproductName = productName.toLowerCase(); // Convert to lowercase for case-insensitive comparison

        for (String name : listProductName) {
            if (name.toLowerCase().equals(lowercaseproductName)) {
                count++;
                System.out.println("Pluss success");
                System.out.println(name);
                return true;
            }
        }
        return false;
    }

    public Vector<Product> getHotProducts() {
        Vector<Product> listP = new Vector<>();
        String sql = "select top 3 * from product where active=1\n"
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
                Boolean active = rs.getBoolean(10);
                listP.add(new Product(id, categoryId, providerId, name, description, price, discount, quantity, image, active));
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
                Boolean active = rs.getBoolean(10);
                searchlistProduct.add(new Product(id, categoryId, providerId, name, description, price, discount, quantity, image, active));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return searchlistProduct;
    }

    public Vector<Product> sortProducts(String sortField, String sortOrder) {
        String sql = "SELECT * FROM product where active = 1 ORDER BY " + sortField + " " + sortOrder;
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
                Boolean active = rs.getBoolean(10);
                sortlistProduct.add(new Product(id, categoryId, providerId, name, description, price, discount, quantity, image, active));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sortlistProduct;
    }

    public Product getProductNonActiveById(int proId) {
        String sql = "select * from [product] where [product].[id] = ? and active=0";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, proId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return (new Product(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getFloat(6),
                        rs.getFloat(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getBoolean(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Product getProductByIdInHistoryDetail(int proId) {
        String sql = "select * from [product] where [product].[id] = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, proId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return (new Product(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getFloat(6),
                        rs.getFloat(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getBoolean(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int insertProduct(int categoryId, int providerId, String name, String description, float price, float discount, int quantity, String image) {
        int rowsAffected = 0;
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "           ([categoryId]\n"
                + "           ,[providerId]\n"
                + "           ,[name]\n"
                + "           ,[description]\n"
                + "           ,[price]\n"
                + "           ,[discount]\n"
                + "           ,[quantity]\n"
                + "           ,[image]\n"
                + "           ,[active])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,1)";
        PreparedStatement pre;
        try {
            pre = conn.prepareStatement(sql);
            pre.setInt(1, categoryId);
            pre.setInt(2, providerId);
            pre.setString(3, name);
            pre.setString(4, description);
            pre.setDouble(5, price);
            pre.setDouble(6, discount);
            pre.setInt(7, quantity);
            pre.setString(8, image);
            rowsAffected = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
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
            rowsAffected = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sqls = "update cartitem set price = ? where productid = ?";
        try {
            pre = conn.prepareStatement(sqls);
            pre.setFloat(1, product.getPrice() - product.getDiscount());
            pre.setInt(2, product.getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
    }

    public int deleteProduct(int productId) {
        int rowsAffected = 0;
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [active] = 0\n"
                + " WHERE id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, productId);
            pre.executeUpdate();
            rowsAffected = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        deleteProductInCart(productId);
        return rowsAffected;
    }

    public void deleteProductInCart(int productId) {
        String sql = "DELETE FROM [dbo].[CartItem]\n"
                + "      WHERE productId = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, productId);
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

    public Vector<Product> getProductByName(String searchName) {
        Vector<Product> listP = new Vector<>();
        String sql = "select * from product where name like ? and active = 1";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + searchName + "%");
            ResultSet rs = statement.executeQuery();
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
                Boolean active = rs.getBoolean(10);
                listP.add(new Product(id, categoryId, providerId, name, description, price, discount, quantity, image, active));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listP;
    }

    public boolean checkExistProducts(String checkName) {
        Vector<Product> listP = new Vector<>();
        String sql = "SELECT *\n"
                + "FROM Product\n"
                + "WHERE name LIKE ? and active = 1";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, checkName);
            ResultSet rs = statement.executeQuery();
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
                Boolean active = rs.getBoolean(10);
                listP.add(new Product(id, categoryId, providerId, name, description, price, discount, quantity, image, active));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (listP.isEmpty() == false) {
            return false;
        }
        return true;

    }

    public Vector<Product> getProductByFilterWithPage(String sort, int searchCategoryId, int searchProviderId, double minPrice, double maxPrice, String searchName, int page) {
        if (sort.equals("")) {
            Vector<Product> listP = new Vector<>();
            String sql = "select * from (select * , ROW_NUMBER() over (order by id) as r\n"
                    + "FROM product where categoryId in (" + (searchCategoryId == -1 ? "select id from Category" : "?") + ")\n"
                    + "and providerId in (" + (searchProviderId == -1 ? "select id from Provider" : "?") + ")\n"
                    + "and price between ? and ?\n"
                    + "and [product].[name] like ? and active = 1  ) as x where  r between 16*?-15 and 16*?";
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                if (searchCategoryId == -1 && searchProviderId == -1) { //No filter by both category and brand
                    statement.setDouble(1, minPrice);
                    statement.setDouble(2, maxPrice);
                    statement.setString(3, "%" + searchName + "%");
                    statement.setInt(4, page);
                    statement.setInt(5, page);
                } else if (searchCategoryId == -1 || searchProviderId == -1) {//Filter either category or brand
                    if (searchCategoryId != -1) {
                        statement.setInt(1, searchCategoryId);
                    } else {
                        statement.setInt(1, searchProviderId);
                    }
                    statement.setDouble(2, minPrice);
                    statement.setDouble(3, maxPrice);
                    statement.setString(4, "%" + searchName + "%");
                    statement.setInt(5, page);
                    statement.setInt(6, page);
                } else {//Filter by both category and brand
                    statement.setInt(1, searchCategoryId);
                    statement.setInt(2, searchProviderId);
                    statement.setDouble(3, minPrice);
                    statement.setDouble(4, maxPrice);
                    statement.setString(5, "%" + searchName + "%");
                    statement.setInt(6, page);
                    statement.setInt(7, page);
                }
                ResultSet rs = statement.executeQuery();
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
                    Boolean active = rs.getBoolean(10);
                    listP.add(new Product(id, categoryId, providerId, name, description, price, discount, quantity, image, active));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return listP;
        } else if (sort.equals("ascending")) {
            Vector<Product> listP = new Vector<>();
            String sql = "select * from (select * , ROW_NUMBER() over (order by id) as r\n"
                    + "FROM product where categoryId in (" + (searchCategoryId == -1 ? "select id from Category" : "?") + ")\n"
                    + "and providerId in (" + (searchProviderId == -1 ? "select id from Provider" : "?") + ")\n"
                    + "and price between ? and ?\n"
                    + "and [product].[name] like ? and active = 1  ) as x where  r between 16*?-15 and 16*? order by price-discount";
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                if (searchCategoryId == -1 && searchProviderId == -1) { //No filter by both category and brand
                    statement.setDouble(1, minPrice);
                    statement.setDouble(2, maxPrice);
                    statement.setString(3, "%" + searchName + "%");
                    statement.setInt(4, page);
                    statement.setInt(5, page);
                } else if (searchCategoryId == -1 || searchProviderId == -1) {//Filter either category or brand
                    if (searchCategoryId != -1) {
                        statement.setInt(1, searchCategoryId);
                    } else {
                        statement.setInt(1, searchProviderId);
                    }
                    statement.setDouble(2, minPrice);
                    statement.setDouble(3, maxPrice);
                    statement.setString(4, "%" + searchName + "%");
                    statement.setInt(5, page);
                    statement.setInt(6, page);
                } else {//Filter by both category and brand
                    statement.setInt(1, searchCategoryId);
                    statement.setInt(2, searchProviderId);
                    statement.setDouble(3, minPrice);
                    statement.setDouble(4, maxPrice);
                    statement.setString(5, "%" + searchName + "%");
                    statement.setInt(6, page);
                    statement.setInt(7, page);
                }
                ResultSet rs = statement.executeQuery();
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
                    Boolean active = rs.getBoolean(10);
                    listP.add(new Product(id, categoryId, providerId, name, description, price, discount, quantity, image, active));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return listP;
        } else {
            Vector<Product> listP = new Vector<>();
            String sql = "select * from (select * , ROW_NUMBER() over (order by id) as r\n"
                    + "FROM product where categoryId in (" + (searchCategoryId == -1 ? "select id from Category" : "?") + ")\n"
                    + "and providerId in (" + (searchProviderId == -1 ? "select id from Provider" : "?") + ")\n"
                    + "and price between ? and ?\n"
                    + "and [product].[name] like ? and active = 1  ) as x where  r between 16*?-15 and 16*? order by price-discount desc";
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                if (searchCategoryId == -1 && searchProviderId == -1) { //No filter by both category and brand
                    statement.setDouble(1, minPrice);
                    statement.setDouble(2, maxPrice);
                    statement.setString(3, "%" + searchName + "%");
                    statement.setInt(4, page);
                    statement.setInt(5, page);
                } else if (searchCategoryId == -1 || searchProviderId == -1) {//Filter either category or brand
                    if (searchCategoryId != -1) {
                        statement.setInt(1, searchCategoryId);
                    } else {
                        statement.setInt(1, searchProviderId);
                    }
                    statement.setDouble(2, minPrice);
                    statement.setDouble(3, maxPrice);
                    statement.setString(4, "%" + searchName + "%");
                    statement.setInt(5, page);
                    statement.setInt(6, page);
                } else {//Filter by both category and brand
                    statement.setInt(1, searchCategoryId);
                    statement.setInt(2, searchProviderId);
                    statement.setDouble(3, minPrice);
                    statement.setDouble(4, maxPrice);
                    statement.setString(5, "%" + searchName + "%");
                    statement.setInt(6, page);
                    statement.setInt(7, page);
                }
                ResultSet rs = statement.executeQuery();
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
                    Boolean active = rs.getBoolean(10);
                    listP.add(new Product(id, categoryId, providerId, name, description, price, discount, quantity, image, active));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return listP;
        }
    }

    public Product getProductById(int proId) {
        String sql = "select * from [product] where [product].[id] = ? and active=1";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, proId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return (new Product(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getFloat(6),
                        rs.getFloat(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getBoolean(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getNumberOfProduct(String sort, int searchCategoryId, int searchProviderId, double minPrice, double maxPrice, String searchName) {
        int num = 0;
        String sql = "select count(*) \n"
                + "FROM product where categoryId in (" + (searchCategoryId == -1 ? "select id from Category" : "?") + ")\n"
                + "and providerId in (" + (searchProviderId == -1 ? "select id from Provider" : "?") + ")\n"
                + "and price between ? and ?\n"
                + "and [product].[name] like ? and active = 1 ";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            if (searchCategoryId == -1 && searchProviderId == -1) { //No filter by both category and brand
                statement.setDouble(1, minPrice);
                statement.setDouble(2, maxPrice);
                statement.setString(3, "%" + searchName + "%");
            } else if (searchCategoryId == -1 || searchProviderId == -1) {//Filter either category or brand
                if (searchCategoryId != -1) {
                    statement.setInt(1, searchCategoryId);
                } else {
                    statement.setInt(1, searchProviderId);
                }
                statement.setDouble(2, minPrice);
                statement.setDouble(3, maxPrice);
                statement.setString(4, "%" + searchName + "%");
            } else {//Filter by both category and brand
                statement.setInt(1, searchCategoryId);
                statement.setInt(2, searchProviderId);
                statement.setDouble(3, minPrice);
                statement.setDouble(4, maxPrice);
                statement.setString(5, "%" + searchName + "%");
            }
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return num;
    }

    public Vector<Product> getProductByFilter(String sort, int searchCategoryId, int searchProviderId, double minPrice, double maxPrice, String searchName) {
        if (sort.equals("")) {
            Vector<Product> listP = new Vector<>();
            String sql = "select * \n"
                    + "FROM product where categoryId in (" + (searchCategoryId == -1 ? "select id from Category" : "?") + ")\n"
                    + "and providerId in (" + (searchProviderId == -1 ? "select id from Provider" : "?") + ")\n"
                    + "and price between ? and ?\n"
                    + "and [product].[name] like ? and active = 1 ";
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                if (searchCategoryId == -1 && searchProviderId == -1) { //No filter by both category and brand
                    statement.setDouble(1, minPrice);
                    statement.setDouble(2, maxPrice);
                    statement.setString(3, "%" + searchName + "%");
                } else if (searchCategoryId == -1 || searchProviderId == -1) {//Filter either category or brand
                    if (searchCategoryId != -1) {
                        statement.setInt(1, searchCategoryId);
                    } else {
                        statement.setInt(1, searchProviderId);
                    }
                    statement.setDouble(2, minPrice);
                    statement.setDouble(3, maxPrice);
                    statement.setString(4, "%" + searchName + "%");
                } else {//Filter by both category and brand
                    statement.setInt(1, searchCategoryId);
                    statement.setInt(2, searchProviderId);
                    statement.setDouble(3, minPrice);
                    statement.setDouble(4, maxPrice);
                    statement.setString(5, "%" + searchName + "%");
                }
                ResultSet rs = statement.executeQuery();
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
                    Boolean active = rs.getBoolean(10);
                    listP.add(new Product(id, categoryId, providerId, name, description, price, discount, quantity, image, active));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return listP;
        } else if (sort.equals("ascending")) {
            Vector<Product> listP = new Vector<>();
            String sql = "select * \n"
                    + "FROM product where categoryId in (" + (searchCategoryId == -1 ? "select id from Category" : "?") + ")\n"
                    + "and providerId in (" + (searchProviderId == -1 ? "select id from Provider" : "?") + ")\n"
                    + "and price between ? and ?\n"
                    + "and [product].[name] like ? and active = 1 order by price-discount ";
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                if (searchCategoryId == -1 && searchProviderId == -1) { //No filter by both category and brand
                    statement.setDouble(1, minPrice);
                    statement.setDouble(2, maxPrice);
                    statement.setString(3, "%" + searchName + "%");
                } else if (searchCategoryId == -1 || searchProviderId == -1) {//Filter either category or brand
                    if (searchCategoryId != -1) {
                        statement.setInt(1, searchCategoryId);
                    } else {
                        statement.setInt(1, searchProviderId);
                    }
                    statement.setDouble(2, minPrice);
                    statement.setDouble(3, maxPrice);
                    statement.setString(4, "%" + searchName + "%");
                } else {//Filter by both category and brand
                    statement.setInt(1, searchCategoryId);
                    statement.setInt(2, searchProviderId);
                    statement.setDouble(3, minPrice);
                    statement.setDouble(4, maxPrice);
                    statement.setString(5, "%" + searchName + "%");
                }
                ResultSet rs = statement.executeQuery();
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
                    Boolean active = rs.getBoolean(10);
                    listP.add(new Product(id, categoryId, providerId, name, description, price, discount, quantity, image, active));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return listP;
        } else {
            Vector<Product> listP = new Vector<>();
            String sql = "select * \n"
                    + "FROM product where categoryId in (" + (searchCategoryId == -1 ? "select id from Category" : "?") + ")\n"
                    + "and providerId in (" + (searchProviderId == -1 ? "select id from Provider" : "?") + ")\n"
                    + "and price between ? and ?\n"
                    + "and [product].[name] like ? and active = 1 order by price-discount desc";
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                if (searchCategoryId == -1 && searchProviderId == -1) { //No filter by both category and brand
                    statement.setDouble(1, minPrice);
                    statement.setDouble(2, maxPrice);
                    statement.setString(3, "%" + searchName + "%");
                } else if (searchCategoryId == -1 || searchProviderId == -1) {//Filter either category or brand
                    if (searchCategoryId != -1) {
                        statement.setInt(1, searchCategoryId);
                    } else {
                        statement.setInt(1, searchProviderId);
                    }
                    statement.setDouble(2, minPrice);
                    statement.setDouble(3, maxPrice);
                    statement.setString(4, "%" + searchName + "%");
                } else {//Filter by both category and brand
                    statement.setInt(1, searchCategoryId);
                    statement.setInt(2, searchProviderId);
                    statement.setDouble(3, minPrice);
                    statement.setDouble(4, maxPrice);
                    statement.setString(5, "%" + searchName + "%");
                }
                ResultSet rs = statement.executeQuery();
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
                    Boolean active = rs.getBoolean(10);
                    listP.add(new Product(id, categoryId, providerId, name, description, price, discount, quantity, image, active));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return listP;
        }
    }
    
    public static void main(String[] args) {
        ProductDAO pDao = new ProductDAO();

        List<Product> providers = pDao.getAllProduct();
        List<String> listStrings = pDao.getAllProductName();
        System.out.println(listStrings);
        boolean check = pDao.checkUpdateProductNameExistence(listStrings, "Adidas Gazelle Shoes new");
        System.out.println(check);
    }
}
