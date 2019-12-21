package com.example.projecteraandroidclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class LogInStudent extends AppCompatActivity {

    private static EditText email;
    private static EditText pass;
    private static Button login;
    private static TextView response;
    private static Context context;

    private static TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_student);

        context = LogInStudent.this;
        //test = (TextView) findViewById(R.id.textView);
        response = (TextView)findViewById(R.id.response);
        email = (EditText) findViewById(R.id.email);
        pass  = (EditText) findViewById(R.id.pass);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(event->
        {
            if(email.getText().toString().equals("") || pass.getText().toString().equals(""))
            {
                Toast.makeText(context, "One or more fields are empty", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(context, "Loading...", Toast.LENGTH_LONG).show();
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "http://192.168.43.153:3000/logInStudent?email=" + email.getText().toString() + "&pass=" + pass.getText().toString();

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,(response)->{
                String str;
                str = response.toString();
                //LogInStudent.response.setText(str);


                if(str.equals("true"))
                {
                    //LogInStudent.response.setText(response);
                    Intent intent = new Intent(context, StudentSection.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(context, "Profile doesn\'t exists", Toast.LENGTH_SHORT).show();
            }, error -> {
                Toast.makeText(LogInStudent.this, error.toString(), Toast.LENGTH_SHORT).show();
            });

            stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(stringRequest);
        });
    }
}