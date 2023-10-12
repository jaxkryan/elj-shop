/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;
import model.Provider;

/**
 *
 * @author Admin
 */
public class ProviderDAO extends jdbc.DBConnect {
    
    public Vector<Provider> getAllProvider() {
        Vector<Provider> listProvider = new Vector<>();
        String sql = "SELECT * from [provider] where active = 1";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String companyName = rs.getString(2);
                String image = rs.getString(3);
                Boolean active = rs.getBoolean(4);
                listProvider.add(new Provider(id, companyName, image, active));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProvider;
    }
    
    public int updateProvider(Provider provider) {
        int rowsAffected = 0;
        String sql = "UPDATE [dbo].[Provider]\n"
                + "   SET [companyName] = ?\n"                
                + "      ,[image] = ?\n"
                + " WHERE id = ?";
        PreparedStatement pre;
        try {
            pre = conn.prepareStatement(sql);
            pre.setInt(3, provider.getId());
            pre.setString(1, provider.getCompanyName());
            pre.setString(2, provider.getImage());
            rowsAffected = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProviderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
    }
    
    public void deleteProvider(String id) {
        String sql = "UPDATE [dbo].[Provider]\n"
                + "   SET [active] = 0\n"
                + " WHERE id=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public int insertProvider(String companyName, String image) {
        int rowsAffected = 0;
        String sql = "INSERT INTO [dbo].[Provider]\n"
                + "           ([companyName]\n"
                + "           ,[image]\n"
                + "           ,[active])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,1)";
        PreparedStatement pre;
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, companyName);
            pre.setString(2, image);
            rowsAffected = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProviderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
    }

    public void setNullProduct(String id) {
        String sql = "update [dbo].Product\n"
                + "set providerId = null\n"
                + "where providerId = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setNullImportOrder(String id) {
        String sql = "update [dbo].[ImportOrder]\n"
                + "set providerId = null\n"
                + "where providerId = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Provider getProviderById(int proId) {
        String sql = "select * from [provider] where [provider].[id] = ? ";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, proId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return (new Provider(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getBoolean(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProviderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        ProviderDAO pDao = new ProviderDAO();
        pDao.deleteProvider("1");
        List<Provider> providers = pDao.getAllProvider();
        System.out.println(providers);
    }
    
    public Provider getProviderById(int providerId) {
        try {
            String sql = "SELECT * from [provider] where id = ? ";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, providerId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                return (new Provider(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
