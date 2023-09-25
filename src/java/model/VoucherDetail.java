/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author maclife
 */
public class VoucherDetail {
    private int id;
    private int voucherID;
    private String startDate;
    private String endDate;
    private double value;

    public VoucherDetail() {
    }

    public VoucherDetail(int id, int voucherID, String startDate, String endDate, double value) {
        this.id = id;
        this.voucherID = voucherID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(int vourcherID) {
        this.voucherID = vourcherID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "VoucherDetail{" + "id=" + id + ", voucherID=" + voucherID + ", startDate=" + startDate + ", endDate=" + endDate + ", value=" + value + '}';
    }
}
