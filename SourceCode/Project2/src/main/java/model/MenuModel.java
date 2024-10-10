package model;

public class MenuModel {
    public int id;
    
    public int categoryId;
    
    public String name;
    
    public int price;
    
    public String img;

    public MenuModel() {
    }

    public MenuModel(int id, int categoryId, String name, int price, String img) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}