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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class SignUpTeacher extends AppCompatActivity
{
    private static EditText name;
    private static EditText pass;
    private static EditText email;
    private static EditText phn;
    private static EditText address;
    private static EditText sub1;
    private static EditText sub2;
    private static EditText sub3;
    private static Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_teacher);

        name    = (EditText) findViewById(R.id.name);
        pass    = (EditText) findViewById(R.id.pass);
        email   = (EditText) findViewById(R.id.email);
        phn     = (EditText) findViewById(R.id.phn);
        address = (EditText) findViewById(R.id.address);
        sub1    = (EditText) findViewById(R.id.sub1);
        sub2    = (EditText) findViewById(R.id.sub2);
        sub3    = (EditText) findViewById(R.id.sub3);
        submit  = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(event->
        {
            if(name.getText().toString().equals("") || pass.getText().toString().equals("") || email.getText().toString().equals("") || phn.getText().toString().equals("") || address.getText().toString().equals("") || sub1.getText().toString().equals("") || sub2.getText().toString().equals("") || sub3.getText().toString().equals(""))
            {
                Toast.makeText(SignUpTeacher.this, "One or more fields are empty", Toast.LENGTH_SHORT).show();
                return;
            }
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "http://192.168.43.153:3000/signUpTeacher?name=" + name.getText().toString() + "&pass=" + pass.getText().toString()
                    + "&email=" + email.getText().toString() + "&phn=" + phn.getText().toString() + "&address=" + address.getText().toString() + "&sub1=" + sub1.getText().toString()
                    + "&sub2=" + sub2.getText().toString() + "&sub3=" + sub3.getText().toString();

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,(response)->{
                Toast.makeText(SignUpTeacher.this, "Profile added", Toast.LENGTH_SHORT).show();
            }, error -> {
                Toast.makeText(SignUpTeacher.this, error.toString(), Toast.LENGTH_SHORT).show();
            });

            stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(stringRequest);

            Toast.makeText(SignUpTeacher.this, "Profile added", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignUpTeacher.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
