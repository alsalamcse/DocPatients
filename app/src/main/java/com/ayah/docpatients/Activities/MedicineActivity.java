package com.ayah.docpatients.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ayah.docpatients.MedicinesListActivity;
import com.ayah.docpatients.PatientsListActivity;
import com.ayah.docpatients.R;
import com.ayah.docpatients.data.Mymedicine;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MedicineActivity extends AppCompatActivity {

    private EditText name;
    private EditText disease;
    private EditText method;
    private EditText timesInDay;
    private EditText notes;
    private Button save;
    Mymedicine mymedicine;

    FirebaseUser user;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        name = findViewById(R.id.name);
        disease = findViewById(R.id.disease);
        method = findViewById(R.id.method);
        timesInDay = findViewById(R.id.timesInDay);
        notes = findViewById(R.id.notes);
        save = findViewById(R.id.save);



        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("details") != null)
        {
            mymedicine = (Mymedicine) intent.getExtras().get("details");
            name.setText(mymedicine.getName());
            disease.setText(mymedicine.getDisease());
            method.setText(mymedicine.getMethod());
            timesInDay.setText(mymedicine.getTimesInDay());
            notes.setText(mymedicine.getNotes());
        }


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataHandler();
            }
        });


    }


    private void dataHandler() {
        boolean isok = true;
        String name1 = name.getText().toString();
        String disease1 = disease.getText().toString();
        String method1 = method.getText().toString();
        String timesInDay1 = timesInDay.getText().toString();
        String notes1 = notes.getText().toString();

        if (name1.length() == 0) {
            name.setError("Title can not be empty");
            isok = false;
        }
        if (disease1.length() == 0) {
            disease.setError("Text can not be empty");
            isok = false;
        }
        if (method1.length() == 0) {
            method.setError("Text can not be empty");
            isok = false;
        }
        if (timesInDay1.length() == 0) {
            timesInDay.setError("Text can not be empty");
            isok = false;
        }
        if (notes1.length() == 0) {
            notes.setError("Text can not be empty");
            isok = false;
        }

        if (isok) {


            if (mymedicine != null)
            {
                databaseReference.child("MyMedicine").child(mymedicine.getKey()).setValue(mymedicine).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MedicineActivity.this, "Add Successful", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getBaseContext(), PatientsListActivity.class);
                            startActivity(i);
                        } else
                            Toast.makeText(MedicineActivity.this, "Add Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            else {
                Intent i = getIntent();
                if (i != null && i.getExtras() != null && i.getExtras().getString("patientId") != null) {
                    String id = i.getExtras().getString("patientId");

                    Mymedicine medicine = new Mymedicine();
                    FirebaseAuth auth = FirebaseAuth.getInstance();

                    medicine.setUidPatient(id);
                    medicine.setDocUid(auth.getCurrentUser().getUid());

                    medicine.setName(name1);
                    medicine.setDisease(disease1);
                    medicine.setMethod(method1);
                    medicine.setTimesInDay(timesInDay1);
                    medicine.setNotes(notes1);

                    //get user email to set is as the owner of this task


                    // to get the database root reference

                    databaseReference = FirebaseDatabase.getInstance().getReference();

                    //to get uid(universal id)


                    String key = databaseReference.child("MyMedicine").push().getKey();
                    medicine.setKey(key);

                    databaseReference.child("MyMedicine").child(key).setValue(medicine).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MedicineActivity.this, "Add Successful", Toast.LENGTH_LONG).show();
                               finish();

                            } else
                                Toast.makeText(MedicineActivity.this, "Add Failed", Toast.LENGTH_LONG).show();

                        }
                    });
                }

            }
        }
    }
}
