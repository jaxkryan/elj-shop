/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author maclife
 */
public class Voucher {

    private int id;
    private String voucherCode;
    private String startDate;
    private String endDate;
    private double value;

    public Voucher() {
    }

    public Voucher(int id, String voucherCode, String startDate, String endDate, double value) {
        this.id = id;
        this.voucherCode = voucherCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public double getValue() {
        return value;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setValue(double value) {
        this.value = value;
    }

}
