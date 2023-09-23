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
    private String image;

    public Provider() {
    }

    public Provider(int Id, String companyName, String image) {
        this.Id = Id;
        this.companyName = companyName;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return Id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
