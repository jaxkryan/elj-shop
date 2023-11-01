/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Feedback {

    int id;
    int customerId;
    int productId;
    int orderId;
    int sellerId;
    String content;
    String reply;
    String feedbackDate;
    String replyDate;
    boolean check;

    public Feedback() {
    }

    public Feedback(int id, int orderId, int customerId, int productId, int sellerId, String content, String reply, String feedbackDate, String replyDate, boolean check) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.orderId = orderId;
        this.sellerId = sellerId;
        this.content = content;
        this.reply = reply;
        this.feedbackDate = feedbackDate;
        this.replyDate = replyDate;
        this.check = check;
    }

    public Feedback(int orderId, int customerId, int productId, int sellerId, String content, String reply, String feedbackDate, String replyDate, boolean check) {
        this.customerId = customerId;
        this.productId = productId;
        this.orderId = orderId;
        this.sellerId = sellerId;
        this.content = content;
        this.reply = reply;
        this.feedbackDate = feedbackDate;
        this.replyDate = replyDate;
        this.check = check;

    }

    public Feedback(int orderId, int customerId, int productId, int sellerId, String content, String reply, String feedbackDate, String replyDate, boolean check, int id) {
        this.customerId = customerId;
        this.productId = productId;
        this.orderId = orderId;
        this.sellerId = sellerId;
        this.content = content;
        this.reply = reply;
        this.feedbackDate = feedbackDate;
        this.replyDate = replyDate;
        this.check = check;
        this.id = id;
    }

    public Feedback(int customerId, int productId, int orderId, String content, String feedbackDate, boolean check) {
        this.customerId = customerId;
        this.productId = productId;
        this.orderId = orderId;
        this.content = content;
        this.feedbackDate = feedbackDate;
        this.check = check;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(String feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public String getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
