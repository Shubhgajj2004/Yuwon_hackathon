package com.shubh.yuwonmedstore;

public class Model_for_pets_profile {

    String Name , DOB , Sex , Type ;


    public Model_for_pets_profile(){}

    public Model_for_pets_profile(String name, String DOB, String sex, String type) {
        this.Name = name;
        this.DOB = DOB;
        this.Sex = sex;
        this.Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
