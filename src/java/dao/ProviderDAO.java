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
                String image = rs.getString(3);
                listProvider.add(new Provider(id, companyName, image));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProvider;
    }
}
