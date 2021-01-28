package com.example.mywallet.data;


import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class AccountDAO {
    APICall apiCall ;
    public MutableLiveData<ArrayList<String []>> getUserData(String username, Context cnt){
        MutableLiveData<ArrayList<String []>> Coordinates = new MutableLiveData<>();
        apiCall  = new APICall(cnt);
        Coordinates = apiCall.GetCoordinates(username);
        return Coordinates ;
    }

    public boolean addCoordinate(String type ,String value){
        return true ;
    }
}
