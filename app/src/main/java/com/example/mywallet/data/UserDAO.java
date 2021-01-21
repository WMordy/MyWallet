package com.example.mywallet.data;

import android.content.Context;

public class UserDAO {
     Encryption crpt = new Encryption();
     APICall apiCall ;

     public  UserDAO(Context context){
         apiCall = new APICall(context);
     }
    public boolean UsernameValid(String username){
        //TODO add request here to get validity
        apiCall.CheckUser(username);
        return true ;
    }
    public  boolean LoginDAO(String username,String password) {
         try{
             String HashedPassword = crpt.encrypt(password);
             apiCall.GetCoordinates(username);
             return true ;
         }
         catch(Exception e){
             e.printStackTrace();
             return false ;
         }
        //TODO add request here to get validity

    }
    public boolean CreateUserDAO(String username , String password){
        try{
            String HashedPassword = crpt.encrypt(password);
            return true ;
        }
        catch(Exception e){
            e.printStackTrace();
            return false ;
        }
    }
}
