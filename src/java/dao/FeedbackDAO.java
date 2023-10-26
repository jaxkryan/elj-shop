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
import org.eclipse.jdt.internal.compiler.ast.FalseLiteral;

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
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getBoolean(9)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return feedbacks;
    }

    public int getProductHaveFeedbackInOrder(int orderId) {
        int count = 0;
        try {
            String sql = "SELECT DISTINCT OD.productId\n"
                    + "FROM [OrderDetails] OD\n"
                    + "INNER JOIN [Feedback] F ON OD.productId = F.productId\n"
                    + "WHERE F.content IS NOT NULL\n"
                    + "AND OD.orderId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int productId = rs.getInt(1);
                count++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }



    public void changeFeedbackStatus(int feedbackId) {
        String sql = "UPDATE Feedback SET checked = 1 WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, feedbackId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vector<Feedback> getAllFeedback() {
        Vector<Feedback> feedbacks = new Vector<>();
        String sql = "  SELECT *\n"
                + "FROM FeedBack\n";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int customerId = rs.getInt(2);
                int productId = rs.getInt(3);
                int sellerId = rs.getInt(4);
                String content = rs.getString(5);
                String reply = rs.getString(6);
                String feedbackDate = rs.getString(7);
                String replyDate = rs.getString(8);
                boolean checked = rs.getBoolean(9);
                feedbacks.add(new Feedback(id, customerId, productId, sellerId,
                        content, reply, feedbackDate, replyDate, checked));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return feedbacks;
    }

    public void addFeedback(int userId, int productId, String content, String reply, String feedbackDate) {
        String sql = "Insert [Feedback] (customerId, productId, content, reply, feedbackDate, [checked])values (? , ?, ?,?, 0)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, userId);
            pre.setInt(2, productId);
            pre.setString(3, content);
            pre.setString(4, reply);
            pre.setString(5, feedbackDate);
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int insertCheckedFeedback(Feedback feedback) {
        int affectedRows = 0;
        String sql = "INSERT INTO [dbo].[Feedback]\n"
                + "           ([customerId]\n"
                + "           ,[productId]\n"
                + "           ,[sellerId]\n"
                + "           ,[content]\n"
                + "           ,[reply]\n"
                + "           ,[feedbackDate]\n"
                + "           ,[replyDate]\n"
                + "           ,[checked])\n"
                + "     VALUES"
                + "           (?,?,?,?,?,?,?,0)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, feedback.getCustomerId());
            pre.setInt(2, feedback.getProductId());
            pre.setInt(3, feedback.getSellerId());
            pre.setString(4, feedback.getContent());
            pre.setString(5, feedback.getReply());
            pre.setString(6, feedback.getFeedbackDate());
            pre.setString(7, feedback.getReplyDate());
            affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return affectedRows;
    }

    public boolean isBought(int userId, int proId) {
        String sql = "select customerId,productId from [order] join"
                + " [orderdetails] on [order].[id]=orderdetails.[orderid]"
                + " where status='Received' and customerid=? and productId=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, userId);
            pre.setInt(2, proId);
            ResultSet rs = pre.executeQuery();
            while (rs.isBeforeFirst()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public int updateFeedback(Feedback feedback) {
        int row = 0;
        String sql = "UPDATE [dbo].[Feedback]\n"
                + "   SET [customerId] = ?\n"
                + "      ,[productId] = ?\n"
                + "      ,[sellerId] = ?\n"
                + "      ,[content] = ?\n"
                + "      ,[reply] = ?\n"
                + "      ,[feedbackDate] =?\n"
                + "      ,[replyDate] = ?\n"
                + "      ,[checked] = ?\n"
                + " WHERE id=?";
        PreparedStatement pre;
        try {
            pre = conn.prepareStatement(sql);
            pre.setInt(1, feedback.getCustomerId());
            pre.setInt(2, feedback.getProductId());
            pre.setInt(3, feedback.getSellerId());
            pre.setString(4, feedback.getContent());
            pre.setString(5, feedback.getReply());
            pre.setString(6, feedback.getFeedbackDate());
            pre.setString(7, feedback.getReplyDate());
            pre.setBoolean(8, true);
            pre.setInt(9, feedback.getId());
//            System.out.println(feedback.getCustomerId());
//            System.out.println(feedback.getProductId());
//            System.out.println(feedback.getSellerId());
//            System.out.println(feedback.getContent());
//            System.out.println(feedback.getReply());
//            System.out.println(feedback.getReplyDate());
//            System.out.println(feedback.getFeedbackDate());
//            System.out.println(feedback.getId());
            row = pre.executeUpdate();
            System.out.println(row);
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

//      public static void main(String[] args) {
//        FeedbackDAO feedbackDAO = new FeedbackDAO();
//        Feedback test = new Feedback(3, 4, 1, "abcd", "god product", "2023-01-09", "2023-01-09", true);
//        feedbackDAO.updateFeedback(test);
//    }
    public String getOrderFeedBack(int proId, int orderId) {
        String sql = "  select content from [Feedback] as f inner join [OrderDetails] as od on f.productId = od.productId\n"
                + "  inner join [Order] as o on od.orderId = o.id where f.productId = ? and od.orderId = ? ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, proId);
            pre.setInt(2, orderId);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void main(String[] args) {
        FeedbackDAO dao = new FeedbackDAO();
        String content = dao.getOrderFeedBack(4, 10);
        System.out.println(content);
    }
}
