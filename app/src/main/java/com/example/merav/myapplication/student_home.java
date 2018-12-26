package com.example.merav.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class student_home extends AppCompatActivity {

    private ListView mListView;
    private ArrayList<teacher> listTeacher = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        final DatabaseReference db = FirebaseDatabase.getInstance().getReference("Teachers");
        mListView = (ListView) findViewById(R.id.listTea);

        final ArrayAdapter<teacher> arrayAdapter = new ArrayAdapter<teacher>(this, android.R.layout.simple_list_item_1, listTeacher);


        db.addValueEventListener(new ValueEventListener() {
                                     @Override
                                     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                         for (DataSnapshot data:dataSnapshot.getChildren()){
                                             teacher models = data.getValue(teacher.class);
                                             listTeacher.add(models);

                                         }

                                         mListView.setAdapter(arrayAdapter);
                                     }

                                     @Override
                                     public void onCancelled(@NonNull DatabaseError databaseError) {

                                     }
                                 }
        );
    }
}