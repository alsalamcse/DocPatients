package com.ayah.docpatients.data;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ayah.docpatients.Activities.PatientActivity;
import com.ayah.docpatients.LoginActivity;
import com.ayah.docpatients.MedicinesListActivity;
import com.ayah.docpatients.R;
import com.ayah.docpatients.SignUpActivity;

import java.util.ArrayList;

public class MypatientAdapter extends ArrayAdapter<Mypatient> {


    public MypatientAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.patient_item, parent, false);


            final Mypatient mypatient = getItem(position); // return data object number"pos"
            TextView id = convertView.findViewById(R.id.id);

            ImageButton edit = convertView.findViewById(R.id.edit);
            ImageButton medicines = convertView.findViewById(R.id.medicines);
//            ImageButton info = convertView.findViewById(R.id.info);


            // pout the data of the objectr on the view

            id.setText(mypatient.getId());


            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), PatientActivity.class);
                    getContext().startActivity(intent);
                }
            });

            medicines.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), MedicinesListActivity.class);
                    getContext().startActivity(intent);
                }
            });

//            delete.setOnClickListener(new View.OnClickListener() {
//                delete.setVisibility(View.GONE);
//            }
        }
    return convertView;
    }
}


