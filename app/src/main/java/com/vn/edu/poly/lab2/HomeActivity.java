package com.vn.edu.poly.lab2;

import android.content.Intent;
import android.icu.text.StringPrepParseException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity {

    private TextView txtvA;
    private TextView txtvB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        Intent intent = getIntent();
        String x = intent.getStringExtra("Name");
        txtvA.setText("Xin chào nhân viên " + x );
        getData();

    }


    private void initView() {
        txtvA = (TextView) findViewById(R.id.txtvA);
        txtvB = (TextView) findViewById(R.id.txtvB);
    }

    private void getData(){

        RequestQueue requestQueue = Volley.newRequestQueue(HomeActivity.this);
        String url = "http://dotplays.com/android/bai1.php?food=today";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("ketqua", response);
                        txtvB.setText(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(stringRequest);
    }


    }

