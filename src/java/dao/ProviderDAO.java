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
        String sql = "SELECT * from provider";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String companyName = rs.getString(2);
                String email = rs.getString(3);
                String image = rs.getString(4);
                listProvider.add(new Provider(id, companyName, email, image));
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
                + "      ,[email] = ?\n"
                + "      ,[image] = ?\n"
                + " WHERE id = ?";
        PreparedStatement pre;
        try {
            pre = conn.prepareStatement(sql);
            pre.setInt(4, provider.getId());
            pre.setString(1, provider.getCompanyName());
            pre.setString(2, provider.getEmail());
            pre.setString(3, provider.getImage());
            int affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProviderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
    }

    public void deleteProvider(String id) {
        setNullProduct(id);
        setNullImportOrder(id);
        String sql = "DELETE FROM [dbo].[Provider]\n"
                + "      WHERE id=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public static void main(String[] args) {
        ProviderDAO pDao = new ProviderDAO();
        pDao.deleteProvider("1");
        List<Provider> providers = pDao.getAllProvider();
        System.out.println(providers);
    }
    
}
