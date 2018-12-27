package com.example.merav.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class teacher_home extends AppCompatActivity {
    RatingBar ratingBar;
    TextView hello,data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);

        Intent intent=getIntent();
        teacher  user=(teacher)intent.getSerializableExtra("user");
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        hello = (TextView) findViewById(R.id.name);
        data = (TextView) findViewById(R.id.data);
        ratingBar.setIsIndicator(true);
        ratingBar.setRating(user.getRank());
        hello.setText("Hello "+user.getName()+",");
        data.setText("Name: "+user.getName()+"\n"+"Email: "+user.getEmail()+"\n"+"Password: "+user.getPassword()+"\n"+
                "Profession: "+user.getProfession()+"\n"+"Area: "+user.getArea()+"\n"+"Cost: "+user.getCost()+"\n"+"Age: "+user.getAge()+"\n"+ "Phone: "+user.getPhone()+"\n");




    }
}