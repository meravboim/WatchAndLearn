package com.example.merav.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class teacher_home extends AppCompatActivity {
    teacher user;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);

        Intent intent=getIntent();
        user=(teacher)intent.getSerializableExtra("user");


        name = (TextView) findViewById(R.id.name);

                    System.out.println(user.getType());
                    System.out.println(user.getName());
                    System.out.println(user.getProfession());
                    System.out.println(user.getArea());
                    System.out.println(user.getCost());
                    System.out.println(user.getAge());
                    System.out.println(user.getPhone());



    }
}