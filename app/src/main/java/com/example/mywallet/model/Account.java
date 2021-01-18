package com.example.mywallet.model;

import java.util.ArrayList;

public class Account {
    User user ;
    ArrayList<Coordinate> coordinateArrayList ;
    public Account(String username){
        coordinateArrayList= new ArrayList<Coordinate>();
        Coordinate crd = new Coordinate("Instagram","www.ig.com/wa.dii3");
        Coordinate crd2 = new Coordinate("Instagram","www.ig.com/wa.dii4");
        coordinateArrayList.add(crd);
        coordinateArrayList.add(crd2);
        //TODO fetch user data
    }
    public boolean AddCoordinate(Coordinate crd){
        coordinateArrayList.add(crd);
        //TODO push data to user database
        return true ;
    }
    public boolean DeleteCoordinate(int index ){
        coordinateArrayList.remove(index);
        //TODO push update to database
        return true ;
    }

    public ArrayList<Coordinate> getCoordinateArrayList() {
        return coordinateArrayList;
    }
}

