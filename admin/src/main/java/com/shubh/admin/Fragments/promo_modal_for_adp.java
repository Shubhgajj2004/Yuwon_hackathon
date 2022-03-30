package com.shubh.admin.Fragments;

public class promo_modal_for_adp {

    String MinAmount,PercentDiscount,Promo,Type,Key;

    public promo_modal_for_adp(String minAmount, String percentDiscount, String promo , String type) {
        this.MinAmount = minAmount;
        this.PercentDiscount = percentDiscount;
        this.Promo = promo;
        this.Type = type;
    }

    public promo_modal_for_adp(String key) {
        Key = key;
    }
    public promo_modal_for_adp(){}


    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getMinAmount() {
        return MinAmount;
    }

    public void setMinAmount(String minAmount) {
        MinAmount = minAmount;
    }

    public String getPercentDiscount() {
        return PercentDiscount;
    }

    public void setPercentDiscount(String percentDiscount) {
        PercentDiscount = percentDiscount;
    }

    public String getPromo() {
        return Promo;
    }

    public void setPromo(String promo) {
        Promo = promo;
    }
}
