/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.sun.xml.internal.bind.v2.model.core.ID;

/**
 *
 * @author Admin
 */
public class Provider {

    private int Id;
    private String companyName;
    private String email;
    private String image;
    private boolean active;

    public Provider() {
    }

    public Provider(int Id, String companyName, String email, String image, boolean active) {
        this.Id = Id;
        this.companyName = companyName;
        this.email = email;
        this.image = image;
        this.active = active;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    
}
