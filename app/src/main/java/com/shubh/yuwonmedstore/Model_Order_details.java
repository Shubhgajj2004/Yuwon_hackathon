package com.shubh.yuwonmedstore;



public class Model_Order_details {

    String Amount,Image,MRP,Name,Price;

    public Model_Order_details(){}

    public Model_Order_details(String amount, String image, String MRP, String name, String price) {
        this.Amount = amount;
        this.Image = image;
        this.MRP = MRP;
        this.Name = name;
        this.Price = price;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getMRP() {
        return MRP;
    }

    public void setMRP(String MRP) {
        this.MRP = MRP;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
