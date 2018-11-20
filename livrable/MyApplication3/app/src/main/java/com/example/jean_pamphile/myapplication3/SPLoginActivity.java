package com.example.jean_pamphile.myapplication3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

public class SPLoginActivity extends AppCompatActivity {

    EditText edtEmail;
    EditText edtAddress;
    EditText edtTel;
    EditText edtCompName;
    private Button addServ;
    private Button addDispo;
    private Button save;
    private DatabaseReference databaseReference;
    final String _email = edtEmail.getText().toString();
    final String _address = edtAddress.getText().toString();
    final String tel = edtTel.getText().toString();
    final String compName = edtCompName.getText().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splogin2);
        edtAddress = (EditText) findViewById(R.id.address);
        edtCompName = (EditText) findViewById(R.id.companyName);
        edtEmail = (EditText) findViewById(R.id.email);
        edtTel = (EditText) findViewById(R.id.tel);
        addServ = (Button) findViewById(R.id.addService);
        addDispo = (Button) findViewById(R.id.editDispo);
        save = (Button) findViewById(R.id.save);

        addServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(SPLoginActivity.this, SignupActivity.class);
//                startActivity(intent);
            }
        });

        addDispo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(SPLoginActivity.this, Calendar.class);
                startActivity(intent2);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
