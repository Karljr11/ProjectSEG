package com.example.jean_pamphile.myapplication3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button admin = (Button) findViewById(R.id.admin);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });

        Button serviceProvider = (Button) findViewById(R.id.serviceProvider);
        serviceProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, ServiceProviderActivity.class);
                startActivity(intent);
            }
        });

        Button homeOwner = (Button) findViewById(R.id.homeOwner);
        homeOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, HomeOwnerActivity.class);
                startActivity(intent);
            }
        });
    }
}
