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
import com.ayah.docpatients.data.Mypatient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PatientActivity extends AppCompatActivity {

    private EditText first;
    private EditText last;
    private EditText id;
    private EditText email;
    private EditText phone;
    private Button save;
    Mypatient mypatient;

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);


        first=findViewById(R.id.first);
        last=findViewById(R.id.last);
        id=findViewById(R.id.id);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        save=findViewById(R.id.save);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("details") != null)
        {
            mypatient = (Mypatient) intent.getExtras().get("details");
            first.setText(mypatient.getFirst());
            last.setText(mypatient.getLast());
            id.setText(mypatient.getId());
            email.setText(mypatient.getEmaill());
            phone.setText(mypatient.getPhone());

        }



     save.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             dataHandler();
             }
     });
    }

            private void dataHandler() {

                boolean isok = true;
                String first1 = first.getText().toString();
                String last1 = last.getText().toString();
                String id1 = id.getText().toString();
                String email1 = email.getText().toString();
                String phone1 = phone.getText().toString();


                if (first1.length() == 0) {
                    first.setError("Title can not be empty");
                    isok = false;
                }
                if (last1.length() == 0) {
                    last.setError("Text can not be empty");
                    isok = false;
                }
                if (id1.length() == 0) {
                    id.setError("Text can not be empty");
                    isok = false;
                }
                if (email1.length() == 0) {
                    email.setError("Title can not be empty");
                    isok = false;
                }
                if (id1.length() == 0) {
                    id.setError("Title can not be empty");
                    isok = false;
                }

                if (isok) {

                    if (mypatient != null)
                    {
                         databaseReference.child("MyPatient").child(mypatient.getId()).setValue(mypatient).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(PatientActivity.this, "Add Successful", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getBaseContext(), PatientsListActivity.class);
                                    startActivity(i);
                                } else
                                    Toast.makeText(PatientActivity.this, "Add Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else {


                        Mypatient patient = new Mypatient();

                        String uidDoc = auth.getCurrentUser().getUid();
                        patient.setUidDoc(uidDoc);


                        patient.setFirst(first1);
                        patient.setLast(last1);
                        patient.setId(id1);
                        patient.setEmaill(email1);
                        patient.setPhone(phone1);

                        //get user email to set is as the owner of this task
                        FirebaseAuth auth = FirebaseAuth.getInstance();
                        patient.setOwner(auth.getCurrentUser().getEmail());

                        // to get the database root reference
                        databaseReference = FirebaseDatabase.getInstance().getReference();

                        String key = databaseReference.child("MyPatient").push().getKey();
                        patient.setKey(id1);

                        //to get uid(universal id)
                        databaseReference.child("MyPatient").child(id1).setValue(patient).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(PatientActivity.this, "Add Successful", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getBaseContext(), PatientsListActivity.class);
                                    startActivity(i);
                                } else
                                    Toast.makeText(PatientActivity.this, "Add Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }
            }

}