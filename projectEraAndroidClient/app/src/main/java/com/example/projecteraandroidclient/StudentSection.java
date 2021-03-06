package com.example.projecteraandroidclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class StudentSection extends AppCompatActivity {

    private static EditText address;
    private static EditText response;
    private static Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_section);

        address = (EditText) findViewById(R.id.address);
        response = (EditText) findViewById(R.id.response);
        search = (Button) findViewById(R.id.search);

        search.setOnClickListener(event->
        {
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "http://192.168.43.153:3000/studentSection?address=" + address.getText().toString();

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,(response)->{
                StudentSection.response.setText(response);
            }, error -> {
                Toast.makeText(StudentSection.this, error.toString(), Toast.LENGTH_SHORT).show();
            });

            stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(stringRequest);
        });
    }
}
