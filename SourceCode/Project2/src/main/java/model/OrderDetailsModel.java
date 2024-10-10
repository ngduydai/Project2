package model;

public class OrderDetailsModel {
    public int id;
    
    public int orderId;
    
    public int quantity;
    
    public int unitPrice;
    
    public int intoMoney;
    
    public String note;
    
    public String nameMenu;

    public OrderDetailsModel() {
    }

    public OrderDetailsModel(int id, int orderId, int quantity, int unitPrice, int intoMoney, String note, String nameMenu) {
        this.id = id;
        this.orderId = orderId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.intoMoney = intoMoney;
        this.note = note;
        this.nameMenu = nameMenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getIntoMoney() {
        return intoMoney;
    }

    public void setIntoMoney(int intoMoney) {
        this.intoMoney = intoMoney;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNameMenu() {
        return nameMenu;
    }

    public void setNameMenu(String nameMenu) {
        this.nameMenu = nameMenu;
    }
}