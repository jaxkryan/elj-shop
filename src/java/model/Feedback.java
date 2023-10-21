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
    String content;
    String feedbackDate;
    boolean check;

    public Feedback() {
    }

    public Feedback(int id, int customerId, int productId, String content, String feedbackDate, boolean check) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.content = content;
        this.feedbackDate = feedbackDate;
        this.check = check;
    }

    public Feedback(int customerId, int productId, String content, String feedbackDate, boolean check) {
        this.customerId = customerId;
        this.productId = productId;
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

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(String feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

}
