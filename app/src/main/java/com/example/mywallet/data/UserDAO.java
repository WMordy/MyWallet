package com.example.mywallet.data;

public class UserDAO {
     Encryption crpt = new Encryption();
    public boolean UsernameValid(String username){
        //TODO add request here to get validity
        return true ;
    }
    public  boolean LoginDAO(String username,String password) {
         try{
             String HashedPassword = crpt.encrypt(password);
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
