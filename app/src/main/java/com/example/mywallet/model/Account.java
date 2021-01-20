package com.example.mywallet.model;

import com.example.mywallet.data.AccountDAO;

import java.util.ArrayList;

public class Account {
    User user ;
    AccountDAO accountDAO = new AccountDAO() ;
    static ArrayList<Coordinate> coordinateArrayList ;
    public Account(String username){
        coordinateArrayList= new ArrayList<Coordinate>();
        Coordinate crd = new Coordinate("Instagram","www.ig.com/wa.dii3");
        Coordinate crd2 = new Coordinate("Instagram","www.ig.com/wa.dii4");
        coordinateArrayList.add(crd);
        coordinateArrayList.add(crd2);
        ArrayList<String[]> coordinatesList = accountDAO.getUserData(username);
        for (String[] coord:coordinatesList) {
            Coordinate crdDynamic = new Coordinate(coord[0],coord[1]);
            coordinateArrayList.add(crdDynamic);

        }
    }
    public boolean AddCoordinate(Coordinate crd){
        if (accountDAO.addCoordinate(crd.getType(),crd.getValue())){
            coordinateArrayList.add(crd);
        }

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

