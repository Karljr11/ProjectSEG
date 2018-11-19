package com.example.jean_pamphile.myapplication3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminActivity extends AppCompatActivity {

    EditText edtUsername;
    EditText edtPassword;
    FirebaseDatabase database;
    private Button signup;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        signup = (Button) findViewById(R.id.loginA);
        edtUsername = (EditText) findViewById(R.id.userA);
        edtPassword = (EditText) findViewById(R.id.pwrdA);

        database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference("admin");
        final String username = edtUsername.getText().toString();
        final String password = edtPassword.getText().toString();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Admin admin = new Admin(username,password);
                databaseReference.push().setValue(admin);
                Intent adminlog = new Intent(AdminActivity.this, AdminloginActivity.class);
                startActivity(adminlog);
            }
        });
    }
}
