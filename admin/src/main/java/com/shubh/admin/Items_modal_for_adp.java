package com.shubh.admin;

public class Items_modal_for_adp {

    String Amount, Desciption,Image,Name,Price,Key,MRP;


    public String getMRP() {
        return MRP;
    }

    public void setMRP(String MRP) {
        this.MRP = MRP;
    }

    public Items_modal_for_adp(String amount, String desciption, String image, String name, String price, String mrp) {
        Amount = amount;
        Desciption = desciption;
        this.Image = image;
        Name = name;
        this.Price=price;
        this.MRP=mrp;
    }

    public Items_modal_for_adp(String key) {
        Key = key;
    }

    public Items_modal_for_adp()
    {

    }


    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        this.Price = price;
    }


    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getDesciption() {
        return Desciption;
    }

    public void setDesciption(String desciption) {
        Desciption = desciption;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
