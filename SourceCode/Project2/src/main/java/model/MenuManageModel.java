/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class MenuManageModel {
    int id;
    int category_id;
    String name;
    int price;
    String img;

    public MenuManageModel() {
    }

    public MenuManageModel(int id, int category_id, String name, int price, String img) {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
        this.price = price;
        this.img = img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
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

    @Override
    public String toString() {
        return "id=" + id + ", category_id=" + category_id + ", name=" + name + ", price=" + price + ", img=" + img;
    }

}
