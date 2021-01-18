package com.example.mywallet.model;

public class User {

    private String USERNAME ;
    private String PASSWORD ;
    public User(String username, String password){

        USERNAME = username;
        PASSWORD = password ;

    }
    public User(String username){
        this.USERNAME = username ;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    protected String getPASSWORD() {
        return PASSWORD;
    }

    protected void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    protected void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
    public boolean isValidUsername(){
        //TODO check if user valid
        return false ;
    }
    public boolean isUserExist(){
        //TODO check if user valid
        return true ;
    }
}
