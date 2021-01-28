package com.example.mywallet.model;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.mywallet.data.AccountDAO;

import java.util.ArrayList;

public class Account {
    String  username ;

    AccountDAO accountDAO = new AccountDAO() ;
    static MutableLiveData<ArrayList<Coordinate>> coordinateArrayList ;
    public Account(String user,Context cnt ){
        username = user ;
        coordinateArrayList= new MutableLiveData<ArrayList<Coordinate>>();
        Coordinate crd = new Coordinate("Instagram","www.ig.com/wa.dii3");
        Coordinate crd2 = new Coordinate("Instagram","www.ig.com/wa.dii4");
        ArrayList<Coordinate> result = new ArrayList<>();
        result.add(crd);
        result.add(crd2);
        coordinateArrayList.setValue(result);


    }

  /*  public boolean DeleteCoordinate(int index ){
        coordinateArrayList.remove(index);
        //TODO push update to database
        return true ;
    }*/

    public MutableLiveData<ArrayList<Coordinate>> getCoordinateArrayList(String username, Context cnt) {
        fetchCoordinateArraylist(username,cnt);
        return coordinateArrayList;
    }

    public void fetchCoordinateArraylist(String username,Context cnt){
        MutableLiveData<ArrayList<String []>> coordinatesList = accountDAO.getUserData(username, cnt);
        if(coordinatesList.getValue().size() != 0){
            ArrayList<Coordinate> result = new ArrayList<>();
            for (String[] coord:coordinatesList.getValue()) {
                Coordinate crdDynamic = new Coordinate(coord[0],coord[1]);
                result.add(crdDynamic);
            }
            coordinateArrayList.setValue(result);
        }

    }

    public String getUsername() {
        return username;
    }
}


