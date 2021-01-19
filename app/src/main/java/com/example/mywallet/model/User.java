package com.example.mywallet.model;

import com.example.mywallet.data.UserDAO;

public class User {

    private String USERNAME ;
    private String PASSWORD ;
    private UserDAO usrDao = new UserDAO();
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



    public boolean Login(){
        boolean login = usrDao.LoginDAO(this.USERNAME,this.PASSWORD);
        return login ;
    }
    public boolean isUserExist(){
        boolean isValid = usrDao.UsernameValid(this.USERNAME);
        return isValid ;

    }
}
