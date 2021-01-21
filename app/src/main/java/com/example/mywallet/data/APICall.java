package com.example.mywallet.data;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

public class APICall {
    RequestQueue requestQueue ;
    private static APICall  apiCall = null;
    APICall(Context context){
         requestQueue =  Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized APICall getInstance(Context cont){
        if(apiCall == null){
            apiCall = new APICall(cont);
        }
        return apiCall ;
    }

    public boolean CheckUser(String user){
        String url = "http://10.0.2.2:2699/checkUser/"+user;
        AtomicBoolean value = new AtomicBoolean(false);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                 value.set(response.getBoolean("status"));


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
    public ArrayList<String[]> GetCoordinates(String user){
        String url = "http://10.0.2.2:2699/userCoordinations/" +user;
        ArrayList<String[]> jsonResponses = new ArrayList<>();


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
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
            Log.i("FETCH",jsonResponses.toString());
            //JSONArray jsonArray = response.getJSONArray("data");
               /* for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String email = jsonObject.getString("email");

                    jsonResponses.add(email);
                }*/
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
        return jsonResponses ;

    }
}
