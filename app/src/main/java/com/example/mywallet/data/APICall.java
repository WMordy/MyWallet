package com.example.mywallet.data;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mywallet.Activities.HomeActivity;
import com.example.mywallet.model.Coordinate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class APICall {
    private static String URL_PREFIX = "http://10.0.2.2:2699";
    RequestQueue requestQueue ;
    private static APICall  apiCall = null;
    private  static boolean loginState = false ;
    public  static MutableLiveData<ArrayList<String []>> coordinates  ;
    private  Context cnt ;




    public APICall(Context context){
        cnt= context.getApplicationContext() ;
         requestQueue =  Volley.newRequestQueue(context.getApplicationContext());
         coordinates = new MutableLiveData<>();

    }

    public static synchronized APICall getInstance(Context cont){
        if(apiCall == null){
            apiCall = new APICall(cont);
        }
        return apiCall ;
    }

    public boolean CheckUser(String user){     //To Remove!!!!!!
        String url = URL_PREFIX+"/checkUser/"+user;
        AtomicBoolean value = new AtomicBoolean(false);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                System.out.println(response);
                 value.set(response.getString("status").equals("true"));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
        return value.get();

    }

    public void fetchCoordinates(ArrayList<String []> array ){
        coordinates .setValue(array); ;
        return  ;
    }
    public MutableLiveData<ArrayList<String[]>> GetCoordinates(String user){
        String url = URL_PREFIX + "/userCoordinations/" + user;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray result ;
                ArrayList jsonResponses = new ArrayList<>();

                try {
                    result = response.getJSONArray("data");
                    for(int i = 0; i < result.length(); i++){
                        JSONArray jsonArray = result.getJSONArray(i);
                        String value = jsonArray.getString(1);
                        String key = jsonArray.getString(0);
                        String[] coordinate = {key,value};
                        jsonResponses.add(coordinate);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                fetchCoordinates(jsonResponses);
                Log.i("FETCH",jsonResponses.toString());
                //JSONArray jsonArray = response.getJSONArray("data");
               /* for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String email = jsonObject.getString("email");

                    jsonResponses.add(email);
                }*/
            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
        return coordinates ;

    }
  /*  public MutableLiveData<ArrayList<String[]>> GetCoordinates(String user){
        String url = URL_PREFIX + "/userCoordinations/" + user;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ArrayList<String[]> jsonResponses = new ArrayList<>();
            Iterator<String> iter = response.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                try {
                    String value = response.getString(key);
                    String[] coordinate = {key,value};
                    jsonResponses.add(coordinate);
                } catch (JSONException e) {
                    // Something went wrong!
                }
            }
            fetchCoordinates(jsonResponses);
            Log.i("FETCH",jsonResponses.toString());
            //JSONArray jsonArray = response.getJSONArray("data");
               *//* for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String email = jsonObject.getString("email");

                    jsonResponses.add(email);
                }*//*
        }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
        return coordinates ;

    }*/
    public  boolean ApiLogin(String username , String hashedpass){
        String postUrl = URL_PREFIX + "/auth";
         boolean[] value = {false};
        JSONObject postData = new JSONObject();
        try {
            postData.put("username", username);
            postData.put("hashedPass", hashedpass);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                boolean loginValue = false;
                try {
                    loginValue = response.getBoolean("status");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(loginValue){
                   System.out.println(response);
                   Intent intent = new Intent(cnt.getApplicationContext(), HomeActivity.class);
                   intent.putExtra("username", username);
                   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   cnt.startActivity(intent);
               }
                else{
                    Toast.makeText(cnt,"wrong username or password",Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });


        requestQueue.add(jsonObjectRequest);
        return value[0];

    }
    public void CreateAccount(String username , String hashedpass){
        String postUrl = URL_PREFIX + "/createAccount";
        boolean[] value = {false};
        JSONObject postData = new JSONObject();
        try {
            postData.put("username", username);
            postData.put("hashedPass", hashedpass);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                boolean loginValue = false;
                try {
                    loginValue = response.getBoolean("status");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(loginValue){
                    System.out.println(response);
                    Intent intent = new Intent(cnt.getApplicationContext(), HomeActivity.class);
                    intent.putExtra("username", username);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    cnt.startActivity(intent);
                }
                else{
                    Toast.makeText(cnt,"used username ",Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });


        requestQueue.add(jsonObjectRequest);
        return ;

    }

    public  boolean PostCoordinate(String username , Coordinate crd){
        String postUrl = URL_PREFIX + "/addCoordination";
        boolean[] value = {false};
        JSONObject postData = new JSONObject();
        try {
            postData.put("username", username);
            postData.put("type", crd.getType());
            postData.put("value", crd.getValue());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                boolean isSent = false;
                try {
                    isSent = response.getBoolean("status");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(isSent){
                    System.out.println("Sent");

                }
                else{
                    Toast.makeText(cnt,"wrong username or password",Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });


        requestQueue.add(jsonObjectRequest);
        return value[0];

    }

    public   void setLoginState(boolean loginState) {
        APICall.loginState = loginState;
    }
}
