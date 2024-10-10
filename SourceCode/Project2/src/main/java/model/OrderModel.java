package model;

import java.sql.Timestamp;

public class OrderModel {
    public int id;
    
    public int userId;
    
    public int tableId;
    
    public int statusId;
    
    public int total;
    
    public String fullname;
    
    public Timestamp time;

    public OrderModel() {
    }

    public OrderModel(int id, int userId, int tableId, int statusId, int total, String fullname, Timestamp time) {
        this.id = id;
        this.userId = userId;
        this.tableId = tableId;
        this.statusId = statusId;
        this.total = total;
        this.fullname = fullname;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}