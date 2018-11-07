package com.example.karl.serviceapp;

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
import android.app.Activity;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    Spinner occupation_spinner;
    EditText editTextPassword;
    EditText editTextUsername;
    Button buttonAddProduct;
    ListView listViewProducts;
    DatabaseReference databaseProducts;

    List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        databaseProducts = FirebaseDatabase.getInstance().getReference("products");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextPassword = (EditText) findViewById(R.id.passwordField);
        editTextUsername = (EditText) findViewById(R.id.editText2);
        buttonAddProduct = (Button) findViewById(R.id.button2);
        listViewProducts = (ListView) findViewById(R.id.listViewProducts);

        products = new ArrayList<>();

        addListenerOnSpinnerItemSelection();

        buttonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProduct();
            }
        });

        listViewProducts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product product = products.get(i);
                showUpdateDeleteDialog(product.getUsername(), product.getPassword());
                return true;
            }
        });
    }

    @Override
    protected void onStart() {

        super.onStart();
        databaseProducts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                products.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Product product = postSnapshot.getValue(Product.class);
                    products.add(product);
                }
                ProductList productAdapter = new ProductList(MainActivity.this, products);
                listViewProducts.setAdapter(productAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
    LayoutInflater inflater = getLayoutInflater();
    final View dialogView = inflater.inflate(R.layout.update_dialog, null);


    private void showUpdateDeleteDialog(String username, final String password) {


        dialogBuilder.setView(dialogView);

        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editText2);
        final EditText editTextPass = (EditText) dialogView.findViewById(R.id.passwordField);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.button2);

                dialogBuilder.setTitle(username);
                final AlertDialog b = dialogBuilder.create();
                b.show();

                buttonUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = editTextName.getText().toString().trim();
                        String password = editTextPass.getText().toString();
                        if (!TextUtils.isEmpty(name)) {
                            updateProduct(name, password);
                            b.dismiss();
                        }
                    }
                });


    }
    private void updateProduct(String username, String password) {

        Toast.makeText(getApplicationContext(), "NOT IMPLEMENTED YET", Toast.LENGTH_LONG).show();
    }
    private void addProduct() {

        dialogBuilder.setView(dialogView);

        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editText2);
        final EditText editTextPass = (EditText) dialogView.findViewById(R.id.passwordField);


        String name = editTextName.getText().toString().trim();
        String password = editTextPass.getText().toString();
        if (!TextUtils.isEmpty(name)) {
            String id = databaseProducts.push().getKey();
            Product product = new Product(name, password);
            databaseProducts.child(id).setValue(product);
            editTextName.setText("");
            editTextPass.setText("");
            Toast.makeText(this, "Product added", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Please enter a name and password", Toast.LENGTH_LONG).show();
        }
    }


    public void addListenerOnSpinnerItemSelection() {
        occupation_spinner = (Spinner) findViewById(R.id.occupation_spinner);
        occupation_spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
}
