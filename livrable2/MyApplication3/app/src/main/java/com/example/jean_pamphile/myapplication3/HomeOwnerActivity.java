package com.example.jean_pamphile.myapplication3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeOwnerActivity extends AppCompatActivity {
    EditText edtUsername;
    EditText edtPassword;
    FirebaseDatabase database;
    private Button signup;
    private DatabaseReference databaseReference;
    final String password = edtPassword.getText().toString();
    final String type = "Home Owner";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner);
        signup = (Button) findViewById(R.id.loginHO);
        edtUsername = (EditText) findViewById(R.id.userHO);
        edtPassword = (EditText) findViewById(R.id.pwrdHO);

        database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference("owners");
        final String username = edtUsername.getText().toString();
        final String password = edtPassword.getText().toString();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeOwner owner = new HomeOwner(username,password,type);
                databaseReference.push().setValue(owner);
                Intent ownerlog = new Intent(HomeOwnerActivity.this, OwnerloginActivity.class);
                startActivity(ownerlog);

            }
        });
    }
}
