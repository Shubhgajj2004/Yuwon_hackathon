package com.shubh.admin;

public class Model_orders_for_adp {

    String PayableAmount,Status,Time,UsedPromo,WithoutPromo,Address,City,ContactNo,Name,Id;


    public Model_orders_for_adp(){}


    public Model_orders_for_adp(String payableAmount, String status, String time, String usedPromo, String withoutPromo, String address, String city, String contactNo, String name) {
        PayableAmount = payableAmount;
        Status = status;
        Time = time;
        UsedPromo = usedPromo;
        WithoutPromo = withoutPromo;
        Address = address;
        City = city;
        ContactNo = contactNo;
        Name = name;
    }


    public Model_orders_for_adp(String id) {
        Id = id;
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPayableAmount() {
        return PayableAmount;
    }

    public void setPayableAmount(String payableAmount) {
        PayableAmount = payableAmount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getUsedPromo() {
        return UsedPromo;
    }

    public void setUsedPromo(String usedPromo) {
        UsedPromo = usedPromo;
    }

    public String getWithoutPromo() {
        return WithoutPromo;
    }

    public void setWithoutPromo(String withoutPromo) {
        WithoutPromo = withoutPromo;
    }
}
