package com.example.merav.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class sign extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth auth;
    String spinnerSelectedItem = "Student";
    EditText passwords, passwordt, Names,Namet, emails,emailt, ages,aget,areat,costt,professiont;
    Button ok_t,ok_s;
    LinearLayout t_layout,s_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        auth = FirebaseAuth.getInstance();
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        passwords = (EditText) findViewById(R.id.password_s);
        passwordt = (EditText) findViewById(R.id.password_t);
        Names = (EditText) findViewById(R.id.name_s);
        Namet = (EditText) findViewById(R.id.name_t);
        emails = (EditText) findViewById(R.id.email_s);
        emailt = (EditText) findViewById(R.id.email_t);
        ages = (EditText) findViewById(R.id.age_s);
        aget = (EditText) findViewById(R.id.age_t);
        areat = (EditText) findViewById(R.id.area);
        professiont= (EditText) findViewById(R.id.profession);
        costt = (EditText) findViewById(R.id.cost);
        ok_s = (Button) findViewById(R.id.enter2);
        ok_t = (Button) findViewById(R.id.enter);
        t_layout=(LinearLayout)findViewById(R.id.t_layout);
        s_layout=(LinearLayout)findViewById(R.id.s_layout);



        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(sign.this, android.R.layout.simple_list_item_1
                , getResources().getStringArray(R.array.typeSpinner));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerSelectedItem = parent.getItemAtPosition(position).toString(); //this is your selected item
                if (spinnerSelectedItem.equals("Teacher")) {
                   s_layout.setVisibility(view.INVISIBLE);
                   t_layout.setVisibility(view.VISIBLE);

                } else {
                    t_layout.setVisibility(view.INVISIBLE);
                    s_layout.setVisibility(view.VISIBLE);
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ok_s.setOnClickListener(this);
        ok_t.setOnClickListener(this);

    }

    public void onClick(View v) {
        if(v == ok_s||v == ok_t) {
            final DatabaseReference db = FirebaseDatabase.getInstance().getReference();
            final users user;


            if (spinnerSelectedItem.equals("Teacher")) {
                String pass_t = passwordt.getText().toString();
                String name_t = Namet.getText().toString();
                String Mail_t = emailt.getText().toString();
                String age_t = aget.getText().toString();
                String area_t = areat.getText().toString();
                String cost_t = costt.getText().toString();
                String profession_t = professiont.getText().toString();
                if (!checkempty_t(pass_t, name_t, Mail_t, age_t, area_t,cost_t,profession_t))
                    return;
                user = new teacher(Mail_t,pass_t, name_t,  age_t, area_t,cost_t,profession_t);
            } else {
                String pass_s = passwords.getText().toString();
                String name_s = Names.getText().toString();
                String Mail_s = emails.getText().toString();
                String age_s = ages.getText().toString();
                if (!checkempty_s(pass_s, name_s, Mail_s, age_s))
                    return;
                user = new student( Mail_s,pass_s, name_s, age_s);
            }
            db.child("Users").child(user.getEmail().replace(".", "|")).addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                Toast.makeText(sign.this, "User already exists", Toast.LENGTH_LONG).show();
                            } else {
                                if (user instanceof teacher) {
                                    db.child("Users").child(user.getEmail().replace(".", "|")).setValue(user);
                                    db.child("Teachers").child(user.getEmail().replace(".", "|")).setValue(user);
                                }
                                else{
                                    db.child("Users").child(user.getEmail().replace(".", "|")).setValue(user);
                                }
                                Toast.makeText(sign.this, "Registration done.", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(sign.this, MainActivity.class));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
        }
    }

    public boolean checkempty_t(String password,String Name,String email,String age,String area,String cost,String profession){

            if ((TextUtils.isEmpty(password))) {
                Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
                return false;
            }
            if ((TextUtils.isEmpty(Name))) {
                Toast.makeText(getApplicationContext(), "Please enter Name", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                return false;
            }
            if ((TextUtils.isEmpty(age))) {
                Toast.makeText(getApplicationContext(), "Please enter age", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(area)) {
                Toast.makeText(getApplicationContext(), "Please enter area", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(cost)) {
                Toast.makeText(getApplicationContext(), "Please enter cost", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(profession)) {
                Toast.makeText(getApplicationContext(), "Please enter profession", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
    }

    public boolean checkempty_s(String password,String Name,String email,String age){

        if ((TextUtils.isEmpty(password))) {
            Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ((TextUtils.isEmpty(Name))) {
            Toast.makeText(getApplicationContext(), "Please enter Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ((TextUtils.isEmpty(age))) {
            Toast.makeText(getApplicationContext(), "Please enter age", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}


