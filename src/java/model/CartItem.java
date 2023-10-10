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
    private int quantit;

    public CartItem(int productId, int cartId, float price, int quantit) {
        this.productId = productId;
        this.cartId = cartId;
        this.price = price;
        this.quantit = quantit;
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

    public int getQuantit() {
        return quantit;
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

    public void setQuantit(int quantit) {
        this.quantit = quantit;
    }
    
}
