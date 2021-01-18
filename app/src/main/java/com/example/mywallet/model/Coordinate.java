package com.example.mywallet.model;

public class Coordinate {
    private String type ;
    private String value ;
    private int ID ;
    public Coordinate(String tp, String val){
        this.type = tp ;
        this.value = val;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getID() {
        return ID;
    }
}
