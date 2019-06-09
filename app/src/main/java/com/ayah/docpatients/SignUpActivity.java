package com.ayah.docpatients;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.ayah.docpatients.Activities.DoctorActivity;
import com.ayah.docpatients.Activities.PatientActivity;
import com.ayah.docpatients.data.MyDoctor;
import com.ayah.docpatients.data.Mypatient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private EditText first;
    private EditText last;
    private EditText phone;
    private EditText email2;
    private EditText password2;
    private EditText password3;
    private Button save;
    private EditText id;
    private CheckBox ifDoctor, ifPatient;

    //1 - add auth to project
    //2
    FirebaseAuth auth;
    // alka2en bsa3dne a3ml sign up w sign in
    FirebaseUser user;
    // alka2en elle ho bbne (user firebase) f2a jahzi

    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ifDoctor = (CheckBox)findViewById(R.id.ifDoctor);
        ifPatient = (CheckBox)findViewById(R.id.ifPatient);

        first = (EditText) findViewById(R.id.first);
        last = (EditText) findViewById(R.id.last);
        phone = (EditText) findViewById(R.id.phone);
        email2 = (EditText) findViewById(R.id.email2);
        password2 = (EditText) findViewById(R.id.password2);
        password3 = (EditText) findViewById(R.id.password3);
        id = (EditText) findViewById(R.id.id);
        save = (Button) findViewById(R.id.save);
        //3
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        // btrj3 al user eza 3amil sign in(aluser al7ali)  aw brj3 null

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataHandler();
            }
        });

    }

    /**
     *  get email and password from the field and try to create new user
     */

    private void dataHandler()
    {
        boolean isOk = true; // if all the fields filled well
        String email22 = email2.getText().toString();
        String password22 = password2.getText().toString();
        String password33 = password3.getText().toString();

        String first2 = first.getText().toString();
        String last2 = last.getText().toString();
        String phone2 = phone.getText().toString();

        String id2 = id.getText().toString();




        if (email22.length()<4 || email22.indexOf('@')<0 || email22.indexOf('.')<0) {
            email2.setError("Wrong Email");
            isOk = false; }

        if (password22.length() < 8) {
            password2.setError("Have to be at least 8 char");
            isOk = false; }

        if (password22.length() < 8) {
            password2.setError("Have to be at least 8 char");
            isOk = false; }

        if (first2.length() < 3){
            first.setError("Set your first name");
            isOk=false;}

        if (last2.length() < 3){
            last.setError("Set your last name");
            isOk=false;}

        if (password22.length() != password33.length() && password33.length()==0) {
            password3.setError("Passwords haven't matched");
            isOk = false;
        }
        if (phone2.length() < 8) {
            phone.setError("Have to be at least 8 char");
            isOk = false; }

        if ((id2.length()!= 9) || (id2.length()!= 9) ) {
            id.setError("set your id");
            isOk = false;
        }

        if (isOk)
            creatAcount(email22,password22);
    }

    /**
     *  create firebase user using email and pasword
     * @param email user email
     * @param passw user password
     */

    //4 - bna2 dala - create acount


    private void creatAcount(String email, String passw) {

        auth.createUserWithEmailAndPassword(email, passw).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Authentication Successful", Toast.LENGTH_SHORT).show();
                    updateUserProfile();
                    finish();
                } else {
                    Toast.makeText(SignUpActivity.this, "Authentication failed." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }

    private void updateUserProfile() {

        String email22 = email2.getText().toString();
        String first2 = first.getText().toString();
        String last2 = last.getText().toString();
        String phone2 = phone.getText().toString();


        String id2 = id.getText().toString();

        MyDoctor doctor = new MyDoctor();
        Mypatient patient = new Mypatient();


        if (ifDoctor.isChecked() && !ifPatient.isChecked())
        {
            doctor.setEmail(email22);
            doctor.setFirst(first2);
            doctor.setLast(last2);
            doctor.setId(id2);
            doctor.setPhone(phone2);

            FirebaseAuth auth=FirebaseAuth.getInstance();
            doctor.setEmail(email22);

            databaseReference= FirebaseDatabase.getInstance().getReference();
            doctor.setKey(email22);
            databaseReference.child("MyDoctor").child(email22.replace('.','*')).setValue(doctor).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getBaseContext(), "Add Successful", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getBaseContext(), PatientsListActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getBaseContext(), "Add failed", Toast.LENGTH_LONG).show();

                    }
                }
            });
        }



        if (ifPatient.isChecked() && !ifDoctor.isChecked())
        {
            patient.setEmaill(email22);
            patient.setFirst(first2);
            patient.setLast(last2);
            patient.setId(id2);
            patient.setPhone(phone2);


            FirebaseAuth auth=FirebaseAuth.getInstance();

            databaseReference = FirebaseDatabase.getInstance().getReference();
            patient.setKey(id2);
            databaseReference.child("MyPatient").child(id2).setValue(patient).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getBaseContext(), "Add Successful", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getBaseContext(), MedicinesListActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getBaseContext(), "Add failed", Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
        else {
            if ((ifPatient.isChecked() && ifDoctor.isChecked()) || (!ifPatient.isChecked() && !ifDoctor.isChecked()))
            {
                Toast.makeText(getBaseContext(), "You have to pick  on of the checkbox in order to sign up", Toast.LENGTH_LONG).show();
            }
        }
    }
}
