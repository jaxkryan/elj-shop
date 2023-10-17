/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Datalia
 */
public class Report {

    private int id, storageStaffId, managerId;
    private String title, content, writeDate;
    private boolean readStatus;

    public Report(int id, int storageStaffId, int managerId, String title, String content, String writeDate, boolean readStatus) {
        this.id = id;
        this.storageStaffId = storageStaffId;
        this.managerId = managerId;
        this.title = title;
        this.content = content;
        this.writeDate = writeDate;
        this.readStatus = readStatus;
    }

        public Report(int storageStaffId, int managerId, String title, String content, String writeDate, boolean readStatus) {
        this.storageStaffId = storageStaffId;
        this.managerId = managerId;
        this.title = title;
        this.content = content;
        this.writeDate = writeDate;
        this.readStatus = readStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStorageStaffId() {
        return storageStaffId;
    }

    public void setStorageStaffId(int storageStaffId) {
        this.storageStaffId = storageStaffId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public boolean isReadStatus() {
        return readStatus;
    }

    public void setReadStatus(boolean readStatus) {
        this.readStatus = readStatus;
    }
}
