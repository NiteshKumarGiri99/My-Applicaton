package com.example.vehichlerecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class PoliceLogin extends AppCompatActivity {
    TextView tv;
    EditText e1,e2;
    FirebaseDatabase fd;
    DatabaseReference dbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_login);
        tv=findViewById(R.id.show);
        e1=findViewById(R.id.batchid);
        e2=findViewById(R.id.passs);
        tv.setText("Login "+getIntent().getExtras().getString("police"));

    }
    public void Login(View view) {

        dbr=fd.getInstance().getReference().child("Police");
        dbr=dbr.child(e1.getText().toString());
        if (dbr.getKey().equals(e1.getText().toString()))
        {
            dbr.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists())
                    {
                        List<String> l=new ArrayList<>();
                        for(DataSnapshot d:dataSnapshot.getChildren())
                        {
                            l.add(d.getValue().toString());
                        }
                        if(e2.getText().toString().equals(l.get(2)))
                        {
                            Toast.makeText(PoliceLogin.this, "Successful Login", Toast.LENGTH_LONG).show();
                            Intent i=new Intent(PoliceLogin.this,PoliceFunction.class);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(PoliceLogin.this, "Wrong Password", Toast.LENGTH_LONG).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(PoliceLogin.this, "Batch_id does not exist", Toast.LENGTH_LONG).show();
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

}
