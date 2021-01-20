package com.example.mywallet.data;


import java.util.ArrayList;

public class AccountDAO {
    public ArrayList getUserData(String username){
        ArrayList Coordinates = new ArrayList();
        String[] coordinate = {"type","value"};
        // loop for resultlist
        Coordinates.add(coordinate);
        return Coordinates ;
    }
    public boolean addCoordinate(String type ,String value){
        return true ;
    }
}
