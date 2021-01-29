package com.example.mywallet.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mywallet.R;
import com.example.mywallet.data.APICall;
import com.example.mywallet.model.Account;
import com.example.mywallet.model.Coordinate;
import com.example.mywallet.model.QRcoder;
import com.example.mywallet.model.User;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.ByteMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.TEXT_ALIGNMENT_CENTER;
import static android.widget.LinearLayout.HORIZONTAL;

public class HomeActivity extends AppCompatActivity {
    EditText linkView ;
    TextView label ;
    final List<String> spinnerArray =  new ArrayList<String>();

    static Account cnt ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        String username = i.getStringExtra("username");
        cnt = new Account(username,this);
        setContentView(R.layout.activity_home);
        label = (TextView)findViewById(R.id.hello_label);
        label.setText("Hello ,"+username);
        spinnerArray.add("Facebook");
        spinnerArray.add("Instagram");
        spinnerArray.add("LinkedIn");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.spinner);
        sItems.setAdapter(adapter);
        APICall apiCall = new APICall(this);
        apiCall.GetCoordinates(username).observe(this,array ->{
            renderCoordinates(fixCooor(array));

        });
        //cnt.getCoordinateArrayList(username,this).observe(this, this::renderCoordinates);

        ImageView tnsd_iv_qr = (ImageView)findViewById(R.id.imageView);
        Bitmap bmp = null;
        try {
            bmp = QRcoder.setQRcode("test");
        } catch (WriterException e) {
            e.printStackTrace();
        }
        tnsd_iv_qr.setImageBitmap(bmp);



    }

    public void addCoord(View v){
        linkView = (EditText)findViewById(R.id.link_text);
        String linkValue = linkView.getText().toString();
        Spinner sItems = (Spinner) findViewById(R.id.spinner);
        String selected = sItems.getSelectedItem().toString();
        if(inputcheck(linkValue,selected)){
            Toast.makeText(HomeActivity.this,selected + "   "+ linkValue,Toast.LENGTH_SHORT).show();
            Coordinate  crd = new Coordinate(selected,linkValue);
            APICall apiCall = new APICall(this);
            apiCall.PostCoordinate(cnt.getUsername(),crd);
            linkView.setText("");

            finish();
            startActivity(getIntent());

        }

    }
    public boolean inputcheck(String link ,String type){
        if(link.length() <= 0 ||type.length() <= 0 ){
            return false ;
        }
        return true ;
    }
    public ArrayList<Coordinate> fixCooor(ArrayList<String []> arr){
        ArrayList<Coordinate> result = new ArrayList<>();
        for (String[] coord:arr) {
            Coordinate crdDynamic = new Coordinate(coord[0],coord[1]);
            result.add(crdDynamic);
        }
        return result ;
    }

    public  void renderCoordinates(ArrayList<Coordinate> coordinatesArray ){
        int i = 0;
        for(Coordinate cn :coordinatesArray){
            i++;

            final LinearLayout linearLayoutForm = (LinearLayout) findViewById(R.id.parent_layout);
            final LinearLayout newView = (LinearLayout) getLayoutInflater().inflate(R.layout.coordinate_item, null);
            final EditText edit_new = (EditText) newView.findViewById(R.id.editTextTextPersonName2);
            edit_new.setId(i);
            final ImageView img = (ImageView) newView.findViewById(R.id.imageView2);
            if(cn.getType().equals("Facebook")){
                img.setImageDrawable(getDrawable(R.drawable.facebook));
            }
            if(cn.getType().equals("Instagram")){
                img.setImageDrawable(getDrawable(R.drawable.instagram));
            }
            if(cn.getType().equals("LinkedIn")){
                img.setImageDrawable(getDrawable(R.drawable.linkedin));
            }
            edit_new.setText(cn.getValue());
            linearLayoutForm.addView(newView);

        }
    }

}