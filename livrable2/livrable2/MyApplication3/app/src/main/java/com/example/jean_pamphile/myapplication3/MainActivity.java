package com.example.jean_pamphile.myapplication3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText edtUsername;
    EditText edtPassword;
    FirebaseDatabase database;
    private Button login;
    private Button signup;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signUp);
//        EditText userName = (EditText) findViewById(R.id.username);
//        EditText passWord = (EditText) findViewById(R.id.password);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Accounts");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent2);
            }
        });
    }
        private void signIn(){
            EditText userName = (EditText) findViewById(R.id.username);
            EditText passWord = (EditText) findViewById(R.id.password);
            final String usName = userName.getText().toString();
            final String pWord = passWord.getText().toString();

            try {
                databaseReference.child(usName).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Account account = dataSnapshot.getValue(Account.class);
                        if (pWord.equals(account.getPassword())) {
                            Toast.makeText(MainActivity.this, "Login successfull", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, " Password Incorrect.", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }catch (Exception ex){
                Toast.makeText(MainActivity.this, " Username Incorrect.", Toast.LENGTH_LONG).show();

                ex.printStackTrace();
            }
        }
        //Button signup = (Button) findViewById(R.id.signUp);

    }

