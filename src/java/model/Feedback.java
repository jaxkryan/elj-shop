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
    int check;

    public Feedback() {
    }

    public Feedback(int id, int customerId, int productId, String content, String feedbackDate, int check) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.content = content;
        this.feedbackDate = feedbackDate;
        this.check = check;
    }

    public int getId() {
        return id;
    }

    public String getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(String feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getProductId() {
        return productId;
    }

    public String getContent() {
        return content;
    }

    public int getCheck() {
        return check;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCheck(int check) {
        this.check = check;
    }

}
