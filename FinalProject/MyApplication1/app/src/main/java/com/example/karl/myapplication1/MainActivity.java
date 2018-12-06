package com.example.karl.myapplication1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();;
    private Button login;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceProv;
    EditText userName;
    EditText passWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = (EditText) findViewById(R.id.username);
        passWord = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);


        databaseReference = database.getReference().child("Admin");
        databaseReferenceProv = database.getReference().child("Provider");


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        Button signup = (Button) findViewById(R.id.signUp);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent2);
            }
        });
    }
    private void signIn() {

        final String usName = userName.getText().toString();
        final String pWord = passWord.getText().toString();

        if (usName.matches("")||pWord.matches("")) {
            Toast.makeText(MainActivity.this, "Enter a Username or Password",Toast.LENGTH_SHORT).show();
        }
        else if (usName.matches(" ") || pWord.matches(" ")) {
            Toast.makeText(MainActivity.this, "Enter a Username or Password", Toast.LENGTH_SHORT).show();
        }
        else {
            databaseReference.child(usName).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Account accountA = dataSnapshot.getValue(Account.class);
                        if (!accountA.getPassword().equals(pWord)) {
                            Toast.makeText(MainActivity.this, " Password Incorrect.", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(MainActivity.this, "Login successfull", Toast.LENGTH_LONG).show();
                            CurrentUser current = new CurrentUser();
                            current.setUser(usName);
                            Intent intent = new Intent(MainActivity.this, AdminLoginActivity.class);
                            startActivity(intent);
                        }
                    } else {
                        signInP();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });
        }

    }
        private void signInP() {
            final String usNameP = userName.getText().toString().trim();
            final String pWordP = passWord.getText().toString();
            final String type1 = "Service";

            Log.e("ADADFA", usNameP);
            if (usNameP.matches("") || pWordP.matches("")) {
                Toast.makeText(MainActivity.this, "Enter a Username or Password", Toast.LENGTH_SHORT).show();
            }
            else if (usNameP.matches(" ") || pWordP.matches(" ")) {
                Toast.makeText(MainActivity.this, "Enter a Username or Password", Toast.LENGTH_SHORT).show();
            } else {

                databaseReferenceProv.child(usNameP).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Account account = dataSnapshot.getValue(Account.class);
                            if (!account.getPassword().equals(pWordP)) {
                                Toast.makeText(MainActivity.this, " Password Incorrect.", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(MainActivity.this, "Login successfull", Toast.LENGTH_LONG).show();
                                CurrentUser currentP = new CurrentUser();
                                currentP.setUser(usNameP);
                                if (account.getType().equals(type1)) {
                                    Intent intent0 = new Intent(MainActivity.this, SPLoginActivity.class);
                                    startActivity(intent0);

                                } else {
                                    Intent intent = new Intent(MainActivity.this, BookActivity.class);
                                    startActivity(intent);
                                }
                            }
                        } else {
                            Toast.makeText(MainActivity.this, " Username Incorrect.", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        }
}
