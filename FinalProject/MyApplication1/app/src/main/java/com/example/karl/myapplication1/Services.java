package com.example.karl.myapplication1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Services extends ArrayAdapter<Service> {
    private Activity context;
    List<Service> services;



    public Services(Activity context, List<Service> services) {
         super(context, R.layout.activity_services, services);
         this.context = context;
         this.services = services;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_services, null, true);

        TextView textViewType = (TextView) listViewItem.findViewById(R.id.textViewType);
        TextView textViewHourlySalary = (TextView) listViewItem.findViewById(R.id.textViewHourlySalary);
        TextView textViewRate = (TextView) listViewItem.findViewById(R.id.textRate);

        Service service = services.get(position);
        textViewType.setText(service.getType());
        textViewHourlySalary.setText(String.valueOf(service.getHourlysalary()));
        textViewRate.setText(String.valueOf(service.getRate()));


        return listViewItem;
    }
}
