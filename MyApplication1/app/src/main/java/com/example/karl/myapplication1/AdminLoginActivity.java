package com.example.karl.myapplication1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AdminLoginActivity extends AppCompatActivity {

    TextView textView;
    EditText serviceBox;
    EditText salaryBox;
    DBHandler dbHandler = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        textView = (TextView) findViewById(R.id.serviceType);
        serviceBox = (EditText) findViewById(R.id.serviceType);
        salaryBox = (EditText) findViewById(R.id.hourlySalary);

    }

    public void newProduct (View view) {

        double salary = Double.parseDouble(salaryBox.getText().toString());

        Services service = new Services(serviceBox.getText().toString(), salary);

        // TODO: add to database
        dbHandler.addProduct(service);


        serviceBox.setText("");

        salaryBox.setText("");
    }


    public void lookupProduct (View view) {

        // TODO: get from Database
        Services service = dbHandler.findService(serviceBox.getText().toString());;

        if (service != null) {
            //idView.setText(String.valueOf(service.getID()));
            salaryBox.setText(String.valueOf(service.getHourlysalary()));
        } else {
            textView.setText("No Match Found");
        }
    }


    public void removeProduct (View view) {


        boolean result = dbHandler.deleteProduct(serviceBox.getText().toString());

        if (result) {
            textView.setText("Record Deleted");
            serviceBox.setText("");
            salaryBox.setText("");
        }
        else
            textView.setText("No Match Found");
    }
}
