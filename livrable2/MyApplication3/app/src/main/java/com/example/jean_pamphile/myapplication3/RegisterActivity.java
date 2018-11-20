package com.example.jean_pamphile.myapplication3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText edtUsername;
    EditText edtPassword;
    FirebaseDatabase database;
    private Button signup;
    private Button signupAdmin;
    private Button signupSP;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signup = (Button) findViewById(R.id.loginHO);
        signupAdmin = (Button) findViewById(R.id.loginAdmin);
        signupSP = (Button) findViewById(R.id.loginSP);
        edtUsername = (EditText) findViewById(R.id.userHO);
        edtPassword = (EditText) findViewById(R.id.pwrdHO);

        database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference("Provider");
        databaseReferenceAdmin = database.getReference("Admin");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = edtUsername.getText().toString();
                final String password = edtPassword.getText().toString();
                final String typeO = "Owner";
                Account owner = new Account(username,password,typeO);
                databaseReference.child(username).setValue(owner);
                Intent ownerlog = new Intent(RegisterActivity.this, OwnerloginActivity.class);
                startActivity(ownerlog);

            }
        });

        signupSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = edtUsername.getText().toString();
                final String password = edtPassword.getText().toString();
                final String typeO = "Service";
                Account service = new Account(username,password,typeO);
                databaseReference.child(username).setValue(service);
                Intent ownerlog = new Intent(RegisterActivity.this, SPLoginActivity.class);
                startActivity(ownerlog);

            }
        });

        signupAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = edtUsername.getText().toString();
                final String password = edtPassword.getText().toString();
                final String typeO = "Admin";
                Account admin = new Account(username,password,typeO);
                databaseReferenceAdmin.child(username).setValue(admin);
                Intent ownerlog = new Intent(RegisterActivity.this, AdminloginActivity.class);
                startActivity(ownerlog);

            }
        });
    }
}
