package com.example.mywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mywallet.model.User;

public class MainActivity extends AppCompatActivity {
    EditText username ;
    EditText password ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);

    }

    public void SignIn(View v){
         Log.i("Login","im signing in");
        Toast.makeText(MainActivity.this,"Hello "+password.getText(),Toast.LENGTH_SHORT).show();
        String usernameValue = username.getText().toString();
        String passwordValue = password.getText().toString();
        if(!inputcheck()){
            Toast.makeText(MainActivity.this,"username or password empty",Toast.LENGTH_SHORT).show();
            clearCredintials();
        }
        else{
            User user = new User(usernameValue,passwordValue);
            if (user.isUserExist()){
                Log.i("Login","you are logged in successfully");
            }
        }

    }
    public void SignUp(View v){
        Log.i("SignUP","im signing up");
        Toast.makeText(MainActivity.this,"Hello "+password.getText(),Toast.LENGTH_SHORT).show();
        String usernameValue = username.getText().toString();
        String passwordValue = password.getText().toString();
        if(!inputcheck()){
            Toast.makeText(MainActivity.this,"username or password empty",Toast.LENGTH_SHORT).show();
            clearCredintials();
        }
        else{
            User user = new User(usernameValue,passwordValue);
            if(user.isValidUsername()){
                Log.i("SignUP","you are logged in successfully");
            }
            else{
                Toast.makeText(MainActivity.this,"username :" +user.getUSERNAME() + "already used",Toast.LENGTH_SHORT).show();
                clearCredintials();
            }
        }

    }
    public void clearCredintials(){
        username.setText("");
        password.setText("");
    }
    public boolean inputcheck(){
        if(username.getText().toString().length() <= 0 || password.getText().toString().length() <= 0 ){
            return false ;
        }
        return true ;
    }
}