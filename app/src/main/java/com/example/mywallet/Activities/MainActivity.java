package com.example.mywallet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mywallet.R;
import com.example.mywallet.model.User;

public class MainActivity extends AppCompatActivity {
    EditText username ;
    EditText password ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        /*this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);   */
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
            User user = new User(usernameValue,passwordValue,this);
            if (user.Login()){
                Log.i("Login","you are logged in successfully");
                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                intent.putExtra("username", usernameValue);
                startActivity(intent);
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
            User user = new User(usernameValue,passwordValue,this);
            if(user.isUserExist()){   // THIS IS JUST FOR TEST CHANGE IT !!!!!!
                Toast.makeText(MainActivity.this,"username :" +user.getUSERNAME() + "already used",Toast.LENGTH_SHORT).show();
                clearCredintials();
                Log.i("SignUP","you are logged in successfully");
            }
            else{
              try{
                  user.CreateUser();
                  user.Login();
                  Log.i("Login","you are logged in successfully");
                  Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                  intent.putExtra("username", usernameValue);
                  startActivity(intent);

              }
              catch (Exception e){
                  e.printStackTrace();
              }
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