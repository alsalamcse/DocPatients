package com.ayah.docpatients.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ayah.docpatients.R;
import com.ayah.docpatients.data.MyDoctor;
import com.ayah.docpatients.data.Mymedicine;
import com.ayah.docpatients.data.Mypatient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorActivity extends AppCompatActivity {

    private EditText first;
    private EditText last;
    private EditText phone;
    private EditText email;
    private EditText id;
    private Button save;


    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();



        first = (EditText) findViewById(R.id.first);
        last = (EditText) findViewById(R.id.last);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        id = (EditText) findViewById(R.id.id);
        save=findViewById(R.id.save);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataHandler();
            }
        });


    }

    private void dataHandler() {
        boolean isok=true;

        String email1 = email.getText().toString();
        String first1 = first.getText().toString();
        String last1 = last.getText().toString();
        String phone1 = phone.getText().toString();
        String id1 = id.getText().toString();


        if(first1.length()<3)
        {
            first.setError("Title can not be empty");
            isok=false;
        }
        if(last1.length()<3)
        {
            last.setError("Text can not be empty");
            isok=false;
        }
        if(phone1.length()<10)
        {
            phone.setError("Text can not be empty");
            isok=false;
        }
        if(id1.length()!= 9 || id1.length()!= 10)
        {
            id.setError("Text can not be empty");
            isok=false;
        }
        if(email1.length()<7 && email1.indexOf('@')<0 && email1.indexOf('.')<0)
        {
            email.setError("Text can not be empty");
            isok=false;
        }

        if(isok) {
            MyDoctor doctor = new MyDoctor();
            doctor.getEmail(email1);
            doctor.getFirst(first1);
            doctor.getLast(last1);
            doctor.getPhone(phone1);
            doctor.getId(id1);


            //get user email to set is as the owner of this task
            FirebaseAuth auth = FirebaseAuth.getInstance();
            doctor.setOwner(auth.getCurrentUser().getEmail());

            // to get the database root reference

            databaseReference = FirebaseDatabase.getInstance().getReference();

            //to get uid(universal id)


            String key=databaseReference.child("MyDoctors").push().getKey();
            doctor.setKey(email1.replace(' ','_'));

            databaseReference.child("MyDoctors").child(email1).setValue(doctor).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(DoctorActivity.this, "Add Successful", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(DoctorActivity.this, "Add Failed", Toast.LENGTH_LONG).show();

                    }
                }
            });




        }


    }
}

