package com.ayah.docpatients;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.ayah.docpatients.Activities.MedicineActivity;
import com.ayah.docpatients.data.MyDoctor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button signup;
    private Button signin;
//    private CheckBox ifDoctor, ifPatient;

    private DatabaseReference databaseReference;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        ifDoctor = (CheckBox)findViewById(R.id.ifDoctor);
//        ifPatient = (CheckBox)findViewById(R.id.ifPatient);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        signin = (Button) findViewById(R.id.signin);
        signup = (Button) findViewById(R.id.signup);

        auth = FirebaseAuth.getInstance();

//        if (auth.getCurrentUser()!=null&&auth.getCurrentUser().getEmail()!=null)
//        {
//            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
//
//        }
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i2 = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i2);
            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if
                dataHandler();
            }
        });
    }



    private void dataHandler() {
        boolean isOk = true; // if all the fields filled well
        String email1 = email.getText().toString();
        String password1 = password.getText().toString();

        if (email1.length() < 4 || email1.indexOf('@') < 0 || email1.indexOf('.') < 0) {
            email.setError("Wrong Email");
            isOk = false;
        }

        if (email1.length() < 4 || email1.indexOf('@') < 0 || email1.indexOf('.') < 0) {
            email.setError("Wrong Email");
            isOk = false;
        }

        if (password1.length() < 8) {
            password.setError("wrong password");
            isOk = false;
        }

        if (isOk)
            signIn(email1, password1);
    }

    private void signIn(final String email, String password) {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    //todo check if doctor logs in or patient

//                    if ()

                    if(email.equals("roza@gmail.com") || email.equals("aya.a@gmail.com")) {
                        Toast.makeText(LoginActivity.this, "signIn Successful.", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(LoginActivity.this, PatientsListActivity.class);
                        startActivity(intent1);
                        finish();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "signIn Successful.", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(LoginActivity.this, MedicinesListActivity.class);
                    }



//                    if (ifDoctor.isChecked() && !ifPatient.isChecked()) {
//                        Intent intent1 = new Intent(LoginActivity.this, PatientsListActivity.class);
//                        startActivity(intent1);
//                        finish();
//
//                    }
//
//                    if (!ifDoctor.isChecked() && ifPatient.isChecked()) {
//                        Intent intent2 = new Intent(LoginActivity.this, MedicinesListActivity.class);
//                        startActivity(intent2);
//                        finish();
//                    }
//
//                    if ((ifPatient.isChecked() && ifDoctor.isChecked()) || (!ifPatient.isChecked() && !ifDoctor.isChecked())) {
//                        Toast.makeText(getBaseContext(), "You have to pick  on of the checkbox in order to sign up", Toast.LENGTH_LONG).show();
//                    }


                }

                else {
                    Toast.makeText(LoginActivity.this, " signIn failed." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });

    }
}
