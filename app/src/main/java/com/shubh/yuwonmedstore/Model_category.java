package com.shubh.yuwonmedstore;

public class Model_category {

    String Amount, Desciption,Image,Name,Price,Key,MRP,Fitness,Covid,Device,Generics,Ointment,Beauty;


    public Model_category(){}

    public Model_category(String amount, String desciption, String image, String name, String price, String MRP, String fitness, String covid, String device, String generics, String ointment, String beauty) {
        this.Amount = amount;
        this.Desciption = desciption;
       this.Image = image;
        this.Name = name;
        this.Price = price;
        this.MRP = MRP;
        this.Fitness = fitness;
        this.Covid = covid;
        this.Device = device;
        this.Generics = generics;
        this.Ointment = ointment;
        this.Beauty = beauty;
    }

    public Model_category(String amount, String image, String name, String price, String MRP) {
        this.Amount = amount;
        this.Image = image;
        this.Name = name;
        this.Price = price;
        this.MRP = MRP;
    }

    public Model_category(String key) {
        Key = key;
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
        Image = image;
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

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getMRP() {
        return MRP;
    }

    public void setMRP(String MRP) {
        this.MRP = MRP;
    }

    public String getFitness() {
        return Fitness;
    }

    public void setFitness(String fitness) {
        Fitness = fitness;
    }

    public String getCovid() {
        return Covid;
    }

    public void setCovid(String covid) {
        Covid = covid;
    }

    public String getDevice() {
        return Device;
    }

    public void setDevice(String device) {
        Device = device;
    }

    public String getGenerics() {
        return Generics;
    }

    public void setGenerics(String generics) {
        Generics = generics;
    }

    public String getOintment() {
        return Ointment;
    }

    public void setOintment(String ointment) {
        Ointment = ointment;
    }

    public String getBeauty() {
        return Beauty;
    }

    public void setBeauty(String beauty) {
        Beauty = beauty;
    }
}
