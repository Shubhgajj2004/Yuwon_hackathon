package com.shubh.yuwonmedstore;

public class modal_vaccination_pet {

    public modal_vaccination_pet(){}

    String Name ,Type , NameVac  ;
    int Duration;

    public modal_vaccination_pet(String name, String type, String nameVac, int duration) {
        Name = name;
        Type = type;
        NameVac = nameVac;
        Duration = duration;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getNameVac() {
        return NameVac;
    }

    public void setNameVac(String nameVac) {
        NameVac = nameVac;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }
}
