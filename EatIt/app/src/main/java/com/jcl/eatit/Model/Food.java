package com.jcl.eatit.Model;

public class Food {
    private String Name,Image,Description,Price,Discount,Menu_Id;

    public Food() {
    }

    public Food(String name, String image, String description, String price, String discount, String menu_Id) {
        Name = name;
        Image = image;
        Description = description;
        Price = price;
        Discount = discount;
        Menu_Id = menu_Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getMenu_Id() {
        return Menu_Id;
    }

    public void setMenu_Id(String menu_Id) {
        Menu_Id = menu_Id;
    }
}
