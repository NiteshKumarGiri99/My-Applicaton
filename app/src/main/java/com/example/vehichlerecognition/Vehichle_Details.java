package com.example.vehichlerecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Vehichle_Details extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    EditText e1;
    Spinner sp;
    Button b;
    BlackList bl;
    FirebaseDatabase fd;
    DatabaseReference dbr;
    static String bid="";
    ArrayAdapter<String> ad;
    String[] s={"Car","Bike","Bus","Truck","other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehichle__details);
        e1 = findViewById(R.id.vhno);
        sp = findViewById(R.id.sp);
        b = findViewById(R.id.addblacklist);
        b.setOnClickListener(this);
        ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, s);
        sp.setAdapter(ad);
        sp.setOnItemSelectedListener(this);
        bl = new BlackList();
        dbr = fd.getInstance().getReference().child("BlackList");
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    //bid = (int) dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

        @Override
        public void onClick(View v) {

            bl.setV_no(e1.getText().toString());
            bid=e1.getText().toString();
            dbr.child(bid).setValue(bl);
            Toast.makeText(this, "Data Added", Toast.LENGTH_SHORT).show();
            e1.setText("");
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            bl.setV_type(s[position]);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

    }

