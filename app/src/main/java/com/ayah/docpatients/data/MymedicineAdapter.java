package com.ayah.docpatients.data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ayah.docpatients.Activities.MedicineActivity;
import com.ayah.docpatients.Activities.PatientActivity;
import com.ayah.docpatients.MedicinesListActivity;
import com.ayah.docpatients.R;

public class MymedicineAdapter extends ArrayAdapter<Mymedicine>
{


    public MymedicineAdapter( Context context, int resource) {
        super(context, resource);
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {



        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout. medicine_item, parent, false);


           final Mymedicine mymedicine = getItem(position); // return data object number"pos"

            TextView name = convertView.findViewById(R.id.name);
//            TextView disease = convertView.findViewById(R.id.disease);
//            TextView method = convertView.findViewById(R.id.method);
//            TextView timesInDay = convertView.findViewById(R.id.timesInDay);
//            TextView notes = convertView.findViewById(R.id.notes);

            ImageButton edit = convertView.findViewById(R.id.edit);



            // put the data of the objectr on the view

            name.setText(mymedicine.getName());
//            disease.setText(mymedicine.getDisease());
//            method.setText(mymedicine.getMethod());
//            timesInDay.setText(mymedicine.getTimesInDay());
//            notes.setText(mymedicine.getNotes());



        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MedicineActivity.class);
                getContext().startActivity(intent);
            }
        });


//            delete.setOnClickListener(new View.OnClickListener() {
//                delete.setVisibility(View.GONE);
//            }
    }
        return convertView;}
    }


