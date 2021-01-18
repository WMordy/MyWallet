package com.example.mywallet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mywallet.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    EditText linkView ;
    List<String> spinnerArray =  new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_home);
        spinnerArray.add("Facebook");
        spinnerArray.add("Instagram");
        spinnerArray.add("LinkedIn");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.spinner);
        sItems.setAdapter(adapter);
    }

    public void addCoord(View v){
        linkView = (EditText)findViewById(R.id.link_text);
        String linkValue = linkView.getText().toString();
        Spinner sItems = (Spinner) findViewById(R.id.spinner);
        String selected = sItems.getSelectedItem().toString();
        if(inputcheck(linkValue,selected)){
            Toast.makeText(HomeActivity.this,selected + "   "+ linkValue,Toast.LENGTH_SHORT).show();
            linkView.setText("");

        }

    }
    public boolean inputcheck(String link ,String type){
        if(link.length() <= 0 ||type.length() <= 0 ){
            return false ;
        }
        return true ;
    }

}