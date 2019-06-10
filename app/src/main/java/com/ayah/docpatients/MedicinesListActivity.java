package com.ayah.docpatients;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.ayah.docpatients.Activities.MedicineActivity;
import com.ayah.docpatients.data.Mymedicine;
import com.ayah.docpatients.data.MymedicineAdapter;
import com.ayah.docpatients.data.Mypatient;
import com.ayah.docpatients.data.MypatientAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MedicinesListActivity extends AppCompatActivity {

    private ListView medicineList;
    private MymedicineAdapter mymedicineAdapter;
    private FloatingActionButton fabAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines_list);

        medicineList = (ListView) findViewById(R.id.medicineList);
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);


        mymedicineAdapter = new MymedicineAdapter(getBaseContext(), R.layout.patient_item);
        medicineList.setAdapter(mymedicineAdapter);

        getAllPatients();



        //todo  if (..) { fabAdd.setVisibility(View.INVISIBLE); }



        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicinesListActivity.this, MedicineActivity.class);
                Intent i = getIntent();
                if (i != null && i.getExtras() != null && i.getExtras().getString("patientId") != null) {
                    String id = i.getExtras().getString("patientId");
                    intent.putExtra("patientId", id);
                }
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        getAllPatients();
        super.onRestart();
    }

    private void getAllPatients() {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            Intent i = getIntent();
            if (i != null && i.getExtras() != null && i.getExtras().getString("patientId") != null)
            {
                String id = i.getExtras().getString("patientId");
                reference.child("MyMedicine").orderByChild("uidPatient").equalTo(id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        mymedicineAdapter.clear();
                        for (DataSnapshot d : dataSnapshot.getChildren()) {
                            Mymedicine mymedicine = d.getValue(Mymedicine.class);
                            mymedicineAdapter.add(mymedicine);
                        }
                        mymedicineAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(MedicinesListActivity.this, "onCancelled", Toast.LENGTH_SHORT).show();

                    }
                });
            }

        }
}
