package com.vn.edu.poly.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText edtUSN;
    private EditText edtPass;
    private EditText edtName;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String pass = edtPass.getText().toString();
                String usn = edtUSN.getText().toString();
                postData();

                if (usn.equals("admin") && pass.equals("123456") && name.equals("Anh Huy")) {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("Name", name);
                    startActivity(intent);
                } else {

                    //Toast.makeText(MainActivity.this, "Trưa nay không được ăn cơm rồi", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void initView() {
        edtUSN = (EditText) findViewById(R.id.edtUSN);
        edtPass = (EditText) findViewById(R.id.edtPass);
        edtName = (EditText) findViewById(R.id.edtName);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }

    private void postData() {

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        String url = "http://dotplays.com/android/login.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("kqq", "onResponse: " + response);
                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("kqq", "onErrorResponse: " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("username", edtUSN.getText().toString());
                params.put("password", edtPass.getText().toString());
                params.put("name", edtName.getText().toString());
                return params;
            }
        };



        requestQueue.add(stringRequest);



    }
}
