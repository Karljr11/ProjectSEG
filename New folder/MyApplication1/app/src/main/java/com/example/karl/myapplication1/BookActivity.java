package com.example.karl.myapplication1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;

public class BookActivity extends AppCompatActivity {

    private static final String TAG = "BookActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    TimePickerDialog timePickerDialog;
    EditText chooseTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        mDisplayDate = (TextView) findViewById(R.id.dateView);

        chooseTime = findViewById(R.id.timePickText);
        chooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int currentHour = cal.get(Calendar.HOUR);
                int currentMin = cal.get(Calendar.MINUTE);
                timePickerDialog = new TimePickerDialog(BookActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                        chooseTime.setText(hour+":"+minute);
                    }
                }, currentHour, currentMin, false);
                timePickerDialog.show();
            }
        });
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        BookActivity.this,
                        android.R.style.Theme_Material_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                Log.d(TAG, "onDataSet: mm/dd/yy: "+month+"/"+day+"/"+year);
                String date = month+"/"+day+"/"+year;
               // databaseReference.child("Booking").setValue(date);
                //Toast.makeText(SPLoginActivity.this, "Booking added", Toast.LENGTH_LONG).show();
                mDisplayDate.setText(date);
            }
        };
    }
}
