package com.shubh.yuwonmedstore;

public class Model_Orders {


    String PayableAmount,Status,Time,UsedPromo,WithoutPromo,Id;

    public Model_Orders(String payableAmount, String status, String time, String usedPromo, String withoutPromo) {
        this.PayableAmount = payableAmount;
        this.Status = status;
        this.Time = time;
        this.UsedPromo = usedPromo;
        this.WithoutPromo = withoutPromo;
    }

    public Model_Orders()
    {

    }

    public  Model_Orders(String id)
    {
        this.Id=id;
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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
