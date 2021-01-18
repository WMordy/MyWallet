package com.example.mywallet.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mywallet.R;
import com.example.mywallet.model.Account;
import com.example.mywallet.model.Coordinate;
import com.example.mywallet.model.User;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.TEXT_ALIGNMENT_CENTER;
import static android.widget.LinearLayout.HORIZONTAL;

public class HomeActivity extends AppCompatActivity {
    EditText linkView ;
    List<String> spinnerArray =  new ArrayList<String>();

    Account cnt ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        String username = i.getStringExtra("id");
        cnt = new Account(username);
        setContentView(R.layout.activity_home);
        renderCoordinates();
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
            Coordinate  crd = new Coordinate(selected,linkValue);
            cnt.AddCoordinate(crd);
            linkView.setText("");

        }

    }
    public boolean inputcheck(String link ,String type){
        if(link.length() <= 0 ||type.length() <= 0 ){
            return false ;
        }
        return true ;
    }

    public  void renderCoordinates(  ){
        int i = 0;
        for(Coordinate cn :cnt.getCoordinateArrayList()){
            i++;
        /* ImageView logo = new ImageView(this);
         EditText text = new EditText(this);
         LinearLayout layout = new LinearLayout(this);
         layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
         layout.setId(cn.getID());
         layout.setOrientation(HORIZONTAL);
         LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                 LinearLayout.LayoutParams.WRAP_CONTENT,
                 LinearLayout.LayoutParams.WRAP_CONTENT);
         text.setLayoutParams(params);
         text.setText(cn.getValue());
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, getResources().getDisplayMetrics());
            int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, getResources().getDisplayMetrics());
            logo.setLayoutParams(new LinearLayout.LayoutParams(height,width));

         logo.setImageResource(R.drawable.fb);
         text.setForegroundGravity(TEXT_ALIGNMENT_CENTER);
         layout.addView(logo);
         layout.addView(text);
         LinearLayout parent = (LinearLayout)findViewById(R.id.parent_layout);
            View to_add = inflater.inflate(layout, false);
         parent.addView(layout);*/
            final LinearLayout linearLayoutForm = (LinearLayout) findViewById(R.id.parent_layout);
            final LinearLayout newView = (LinearLayout) getLayoutInflater().inflate(R.layout.coordinate_item, null);
            final EditText edit_new = (EditText) newView.findViewById(R.id.editTextTextPersonName2);
            edit_new.setId(i);
            edit_new.setText(cn.getValue());
            linearLayoutForm.addView(newView);

        }
    }

}