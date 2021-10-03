package com.example.vehichlerecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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


public class SearchSave extends AppCompatActivity implements View.OnClickListener {
    FirebaseDatabase fd,fr;
    Police p;
    DatabaseReference dbr,dr;
    EditText e1,e3,e4;
    TextView e2;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_save);
        e1=findViewById(R.id.pname);
        e2=findViewById(R.id.pbatch_id);
        e3=findViewById(R.id.pusername);
        e4=findViewById(R.id.ppassword);
        b=findViewById(R.id.psearch);
        p=new Police();
        b.setOnClickListener(this);
        dr=fr.getInstance().getReference().child("Police");
        dbr=fd.getInstance().getReference().child("Police");
        dbr=dbr.child(getIntent().getExtras().getString("batch_id"));
        if(dbr!=null)
        {
            dbr.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    List<String> l=new ArrayList<String>();
                    if(dataSnapshot.exists())
                    {
                        for(DataSnapshot d:dataSnapshot.getChildren())
                        {
                            l.add(d.getValue().toString());
                        }
                        e2.setText(l.get(0));
                        e1.setText(l.get(1));
                        e4.setText(l.get(2));
                        e3.setText(l.get(3));
                    }
                    else
                    {
                        Toast.makeText(SearchSave.this, "Id not found", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }
    public void onClick(View v) {
        if(e2.getText().toString().equals(""))
        {
            Toast.makeText(this, "Inavlid Function", Toast.LENGTH_SHORT).show();
            return;
        }
        int pid=0;
        pid=Integer.parseInt(e2.getText().toString());
        p.setName(e1.getText().toString());
        p.setBatch_id(e2.getText().toString());
        p.setUsername(e3.getText().toString());
        p.setPassword(e4.getText().toString());
        dr.child(String.valueOf(pid)).setValue(p);
        Toast.makeText(this, "Data Edited", Toast.LENGTH_SHORT).show();
    }
}
