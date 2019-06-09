package com.ayah.docpatients;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayah.docpatients.Activities.PatientActivity;
import com.ayah.docpatients.data.Mypatient;
import com.ayah.docpatients.data.MypatientAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientsListActivity extends AppCompatActivity {
    private Button btnSearch;
    private EditText etSearch;
    private ListView patientList;
    private ImageButton addPatient;
    private MypatientAdapter mypatientAdapter;
    private FloatingActionButton fabAdd;
    FirebaseAuth auth2;
    FirebaseUser user2;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_list);


        patientList = (ListView) findViewById(R.id.patientList);
        fabAdd = (FloatingActionButton)findViewById(R.id.fabAdd);

        mypatientAdapter = new MypatientAdapter(getBaseContext(),R.layout.patient_item);
        patientList.setAdapter(mypatientAdapter);
        getAllPatients();

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientsListActivity.this, PatientActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getAllPatients() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("MyPatient").addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mypatientAdapter.clear();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Mypatient mypatient = d.getValue(Mypatient.class);
                    mypatientAdapter.add(mypatient);
                }
                mypatientAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(PatientsListActivity.this, "onCancelled", Toast.LENGTH_SHORT).show();

            }

        });
    }

//    public void searchMethod(){
//        String searchPlain=etSearch.getText().toString();
//        databaseReference.child("MyPatient").orderByChild("id").equalTo(searchPlain).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                mypatientAdapter.clear();
//                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
//                    String id=dataSnapshot1.getValue().toString();
//                    mypatientAdapter.add();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(PatientsListActivity.this, "searching failed", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//    }

}
