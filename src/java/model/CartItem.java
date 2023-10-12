/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class CartItem {
    private int productId;
    private int cartId;
    private float price;
    private int quantity;

    public CartItem(int productId, int cartId, float price, int quantity) {
        this.productId = productId;
        this.cartId = cartId;
        this.price = price;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public int getCartId() {
        return cartId;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(int y) {
        this.quantity = quantity;
    }
    
}
