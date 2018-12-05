package com.example.karl.myapplication1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
        import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class SPLoginActivity extends AppCompatActivity {

    EditText edtEmail;
    EditText edtAddress;
    EditText edtTel;
    EditText edtCompName;
    TextView seeDate;
    TextView seeTime;
    EditText edtService;
    private Button addServ;
    private Button addDispo;
    private Button save;
    private Button date;
    private Button time;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TimePickerDialog.OnTimeSetListener timeSetListener;
    private static final String TAG = "SPLoginActivity";
    private DatabaseReference databaseReference;
    final String _email = edtEmail.getText().toString();
    final String _address = edtAddress.getText().toString();
    final String tel = edtTel.getText().toString();
    final String compName = edtCompName.getText().toString();
    CurrentUser current = new CurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splogin);
        edtAddress = (EditText) findViewById(R.id.address);
        edtCompName = (EditText) findViewById(R.id.companyName);
        edtEmail = (EditText) findViewById(R.id.email);
        edtTel = (EditText) findViewById(R.id.tel);
        seeDate = (TextView) findViewById(R.id.dateView);
        seeTime = (TextView) findViewById(R.id.timeView);
        addServ = (Button) findViewById(R.id.addService);
        addDispo = (Button) findViewById(R.id.editDispo);
        date = (Button) findViewById(R.id.addDate);
        time = (Button) findViewById(R.id.addTime);
        save = (Button) findViewById(R.id.save);
        edtService = (EditText) findViewById(R.id.serviceText);




        databaseReference = FirebaseDatabase.getInstance().getReference("Provider").child(current.getUsername());
        addServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String service = edtService.getText().toString();
                databaseReference.child("Informations").child("Services").setValue(date);
                Toast.makeText(SPLoginActivity.this, "Service added", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(SPLoginActivity.this, SignupActivity.class);
//                startActivity(intent);
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.util.Calendar calendar = java.util.Calendar.getInstance();
                int y = calendar.get(java.util.Calendar.YEAR);
                int m = calendar.get(java.util.Calendar.MONTH);
                int d = calendar.get(java.util.Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(SPLoginActivity.this, dateSetListener, y, m, d);

            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.d(TAG, "onDateSet: "+dayOfMonth+"/"+month+"/"+year);

                String date = dayOfMonth+"/"+month+"/"+year;
                databaseReference.child("Informations").child("Availability").setValue(date);
                Toast.makeText(SPLoginActivity.this, "Availability added", Toast.LENGTH_LONG).show();
                seeDate.setText(date);
            }
        };

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.util.Calendar calendar = java.util.Calendar.getInstance();
                int hour = calendar.get(java.util.Calendar.HOUR);
                int min = calendar.get(java.util.Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(SPLoginActivity.this, timeSetListener, hour, min, false);

            }
        });

        timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Log.v(TAG, "onTimeSet:"+hourOfDay+":"+minute);

                String time = hourOfDay+":"+minute;
                databaseReference.child("Informations").child("Availability").setValue(time);
                Toast.makeText(SPLoginActivity.this, "Availability added", Toast.LENGTH_LONG).show();
                seeTime.setText(time);
            }
        };


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String _email = edtEmail.getText().toString();
                final String _address = edtAddress.getText().toString();
                final String tel = edtTel.getText().toString();
                final String compName = edtCompName.getText().toString();

                if(!TextUtils.isEmpty(_address)&&!TextUtils.isEmpty(_email)&&!TextUtils.isEmpty(tel)&&!TextUtils.isEmpty(compName)){
                Provider provider = new Provider(_email,_address, tel,compName);
                databaseReference.child("Informations").setValue(provider);
                edtAddress.setText("");
                edtCompName.setText("");
                edtEmail.setText("");
                edtTel.setText("");
                Toast.makeText(SPLoginActivity.this, "Info added", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(SPLoginActivity.this, "Please enter a name", Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}