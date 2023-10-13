/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Feedback;
import model.Provider;

/**
 *
 * @author Admin
 */
public class FeedbackDAO extends jdbc.DBConnect {

    public Vector<Feedback> getFeedbackByProductId(int productId) {
        Vector<Feedback> feedbacks = new Vector<>();
        String sql = "SELECT * FROM feedback WHERE productId = ? ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, productId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                feedbacks.add(new Feedback(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return feedbacks;
    }

    public void addFeedback(int userId, int productId, String content, String feedbackDate) {
        String sql = "Insert [Feedback] (customerId, productId, content, feedbackDate, [checked])values (? , ?, ?,?, 0)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, userId);
            pre.setInt(2, productId);
            pre.setString(3, content);
            pre.setString(4, feedbackDate);
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
