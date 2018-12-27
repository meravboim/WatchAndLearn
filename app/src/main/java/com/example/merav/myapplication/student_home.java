package com.example.merav.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class student_home extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("hi");

        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseDatabase.getInstance().getReference("Teachers");

        Query firebaseSearchQuery = db.orderByChild("rank").limitToFirst(10);

        FirebaseRecyclerOptions<teacher> options = new FirebaseRecyclerOptions.Builder<teacher>()
                .setQuery(firebaseSearchQuery, teacher.class)
                .build();

        FirebaseRecyclerAdapter<teacher, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<teacher, ViewHolder>(options) {
                    @NonNull
                    @Override
                    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                        View view=getLayoutInflater().from(viewGroup.getContext()).inflate(R.layout.teacherdat,viewGroup,false);
                        ViewHolder viewHolder =new ViewHolder(view);
                        return viewHolder;
                    }

                    protected void onBindViewHolder(@NonNull final ViewHolder holder, int position, @NonNull final teacher model) {
                        holder.setdetails(getApplicationContext(),model, model.getName(), model.getProfession(),model.getArea(),
                                model.getImage(),model.getRank());

                    }


                };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }
}