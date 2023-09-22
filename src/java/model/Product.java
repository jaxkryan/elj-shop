/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Product {
    private int id;
    private int catagoryId;
    private int providerId;
    private String name;
    private String description;
    private float price;
    private float discount;
    private int quantity;
    private String image;

    public Product() {
    }

    public Product(int id, int catagoryId, int providerId, String name, String description, float price, float discount, int quantity, String image) {
        this.id = id;
        this.catagoryId = catagoryId;
        this.providerId = providerId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public int getCatagoryId() {
        return catagoryId;
    }

    public int getProviderId() {
        return providerId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public float getDiscount() {
        return discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCatagoryId(int catagoryId) {
        this.catagoryId = catagoryId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
}
