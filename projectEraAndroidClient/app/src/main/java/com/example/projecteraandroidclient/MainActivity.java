package com.example.projecteraandroidclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private static Button logInAsTeacher;
    private static Button logInAsStudent;
    private static Button signUpAsTeacher;
    private static Button signUpAsStudent;
    private static TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating instances of views
        logInAsTeacher  = (Button) findViewById(R.id.logInAsTeacher);
        logInAsStudent  = (Button) findViewById(R.id.logInAsStudent);
        signUpAsTeacher = (Button) findViewById(R.id.signUpAsTeacher);
        signUpAsStudent = (Button) findViewById(R.id.signUpAsStudent);
        info            = (TextView) findViewById(R.id.info);

        //Adding listeners to views
        logInAsTeacher.setOnClickListener(event->
        {
            Intent intent = new Intent(MainActivity.this, LogInTeacher.class);
            startActivity(intent);
        });
        logInAsStudent.setOnClickListener(event->
        {
            Intent intent = new Intent(MainActivity.this, LogInStudent.class);
            startActivity(intent);
        });
        signUpAsTeacher.setOnClickListener(event->
        {
            Intent intent = new Intent(MainActivity.this, SignUpTeacher.class);
            startActivity(intent);
        });
        signUpAsStudent.setOnClickListener(event->
        {
            Intent intent = new Intent(MainActivity.this, SignUpStudent.class);
            startActivity(intent);
        });
        info.setOnClickListener(event->
        {
            String str = "Course Title : Software development project 1\n" +
                    "Course No : CSE 1200\n" +
                    "Submitted by :\n" +
                    " Anika Tabassum Era\n" +
                    " 1703176\n" +
                    " CSE'17\n" +
                    "Submitted to :\n" +
                    " Sadia Zaman Mishu\n" +
                    " Assistant Professor,\n" +
                    " Dept. of CSE,\n" +
                    " RUET";
            Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
        });
    }
}
