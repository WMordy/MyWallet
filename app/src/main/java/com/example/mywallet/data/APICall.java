package com.example.mywallet.data;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

    public void volleyGet(){
        String url = "http://10.0.2.2:2699/userCoordinations/sammu";
        List<String> jsonResponses = new ArrayList<>();


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                String value = response.getString("ig");
                Log.i("FETCH DATA",value);
                //JSONArray jsonArray = response.getJSONArray("data");
               /* for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String email = jsonObject.getString("email");

                    jsonResponses.add(email);
                }*/
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

    }
}
