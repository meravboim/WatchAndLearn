package com.example.merav.myapplication;




        import android.content.Context;
        import android.content.Intent;
        import android.os.Parcelable;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.RecyclerView;
        import android.text.TextUtils;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;
        import android.support.annotation.NonNull;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;


        import android.view.ViewGroup;

        import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button login;
    Button sign;
    EditText Email;
    EditText Password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.Button_login);
        sign = (Button)findViewById(R.id.Button_sign);
        Email = findViewById(R.id.mail);
        Password = findViewById(R.id.password);


        login.setOnClickListener(this);
        sign.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==login){
            final String email = Email.getText().toString();
            final String password = Password.getText().toString();
            if (!checkempty(password,email)){
                return;
            }

            final DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users");
            db.addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            boolean login = false;
                            users user=null;

                            if (dataSnapshot.exists()) {
                                user=dataSnapshot.child(email.replace(".", "|")).getValue(users.class);
                                if (password.equals(user.getPassword())) {
                                    login = true;
                                }
                            }
                            if (login) {
                                Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_LONG).show();
                                if(user.getType().equals("teacher")){
                                    teacher tea =dataSnapshot.child(email.replace(".", "|")).getValue(teacher.class);
                                    Intent mIntent = new Intent(getApplicationContext(), teacher_home.class);
                                   // Intent mIntent = new Intent(getApplicationContext(), teacher_page.class);
                                    mIntent.putExtra("user",tea);
                                    startActivity(mIntent);

                                    }
                                else if(user.getType().equals("student")){
                                    startActivity(new Intent(MainActivity.this, student_home.class));
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Either email or password is incorrect.", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    }
            );


        }
        else if(v==sign){
            startActivity(new Intent(this,sign.class));
        }

    }

    public boolean checkempty(String password,String email){

        if ((TextUtils.isEmpty(password))) {
            Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


}