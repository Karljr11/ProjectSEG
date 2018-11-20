package com.example.jean_pamphile.myapplication3;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class AdminloginActivity extends AppCompatActivity {

    EditText edtType;
    EditText edtHourlySalary;
    Button buttonAddService;
    //Button buttonDeleteService;
    ListView listViewServices;

    List<Service> services;
    DatabaseReference databaseServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        databaseServices = FirebaseDatabase.getInstance().getReference("services");

        edtType = (EditText) findViewById(R.id.edtType);
        edtHourlySalary = (EditText) findViewById(R.id.edtHourlySalary);
        listViewServices = (ListView) findViewById(R.id.listViewServices);
        buttonAddService = (Button) findViewById(R.id.addButton);
        //buttonDeleteService = (Button) findViewById(R.id.deleteButton);

        services = new ArrayList<>();

        //adding an onclicklistener to button
        buttonAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addService();
            }
        });

        listViewServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Service service = services.get(i);
                showUpdateDeleteDialog(service.getId(), service.getType());
                return true;
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        databaseServices.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                services.clear();

                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Service service = postSnapshot.getValue(Service.class);
                    services.add(service);
                }
                Services serviceAdapter  = new Services(AdminloginActivity.this, services);
                listViewServices.setAdapter(serviceAdapter);
            }

            public void onCancelled(DatabaseError databaseError){

            }
        });
    }

    private void showUpdateDeleteDialog(final String serviceId, String serviceType) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(AdminloginActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText edtType = (EditText) dialogView.findViewById(R.id.edtType);
        final EditText edtHourlySalary  = (EditText) dialogView.findViewById(R.id.edtHourlySalary);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateService);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteService);

        dialogBuilder.setTitle(serviceType);
        final AlertDialog b = dialogBuilder.create();
        b.setTitle(serviceType);
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = edtType.getText().toString().trim();
                double hourlySalary = Double.parseDouble(String.valueOf(edtHourlySalary.getText().toString()));
                if (!TextUtils.isEmpty(type)) {
                    updateService(serviceId, type, hourlySalary);
                    b.dismiss();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteService(serviceId);
                b.dismiss();
            }
        });
    }

    private void updateService(String id, String type, double hourlySalary) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("services").child(id);
        Service service = new Service(id, type, hourlySalary);
        dR.setValue(service);
        Toast.makeText(getApplicationContext(), "Service Updated", Toast.LENGTH_LONG).show();
    }

    private boolean deleteService(String id) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("services").child(id);
        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Service Deleted", Toast.LENGTH_LONG).show();
        return true;
    }

    private void addService() {
        String type = edtType.getText().toString().trim();
        double hourlySalary = Double.parseDouble(String.valueOf(edtHourlySalary.getText().toString()));

        if(!TextUtils.isEmpty(type)){
            String id = databaseServices.push().getKey();
            Service service = new Service(id, type, hourlySalary);
            databaseServices.child(id).setValue(service);
            edtType.setText("");
            edtHourlySalary.setText("");
            Toast.makeText(this, "Service added", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }

    }
}