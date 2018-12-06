package com.example.karl.myapplication1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class BookActivity extends AppCompatActivity {

    private static final String TAG = "BookActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatabaseReference databaseReference;
    TimePickerDialog timePickerDialog;
    EditText chooseTime;
    CurrentUser current = new CurrentUser();
    ListView listViewServices;
    List<Service> services;



    @Override
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        mDisplayDate = (TextView) findViewById(R.id.dateView);
        services = new ArrayList<>();

        chooseTime = findViewById(R.id.timePickText);
        databaseReference = FirebaseDatabase.getInstance().getReference("Provider").child(current.getUsername());

        listViewServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Service service = services.get(i);
                showUpdateDeleteDialog("l", service.getType(), service.getHourlysalary());
                return true;
            }
        });

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
                String dateS = month+"/"+day+"/"+year;
                DateOrTime date = new DateOrTime(month, day, year);
               databaseReference.child("Bookings").setValue(date);
               Toast.makeText(BookActivity.this, "Booking added", Toast.LENGTH_LONG).show();
                mDisplayDate.setText(dateS);
            }
        };
    }
    private void showUpdateDeleteDialog(final String user, String serviceType, final double hourlySalary) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(BookActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.rate_dialog, null);
        final EditText edtRate = (EditText) dialogView.findViewById(R.id.rateText);
        final EditText edtComment = (EditText) dialogView.findViewById(R.id.commentText);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.updateRatebutton);

        dialogBuilder.setView(dialogView);
        dialogBuilder.setTitle(serviceType);
        final AlertDialog b = dialogBuilder.create();
        b.setTitle(serviceType);
        b.show();
        final String serv = serviceType;


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rate1 = edtRate.getText().toString().trim();
                String comment = edtComment.getText().toString().trim();
                if (!TextUtils.isEmpty(rate1)) {
                    double rate = Double.parseDouble(String.valueOf(edtRate.getText().toString()));
                    updateService(user,serv, rate, hourlySalary, comment);
                    b.dismiss();
                }
            }
        });
    }
        private void updateService( String user, String serv, double rate, double hourlySalary, String comment) {
            DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Provider").child(user).child(serv);
            Service service = new Service(serv, hourlySalary, rate);
            dR.setValue(service);
            dR.setValue(comment);
            Toast.makeText(getApplicationContext(), "Service Updated", Toast.LENGTH_LONG).show();
        }
}
