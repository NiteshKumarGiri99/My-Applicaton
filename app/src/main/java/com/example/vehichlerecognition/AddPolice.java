package com.example.vehichlerecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddPolice extends AppCompatActivity implements View.OnClickListener {

    EditText name,username,password;
    Button adddetails;
    DatabaseReference dbr;
    FirebaseDatabase fd;
    static int  pid=0;
    Police s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_police);
        name=findViewById(R.id.nm);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        adddetails=findViewById(R.id.adddetails);
        s=new Police();
        adddetails.setOnClickListener(this);
        dbr=fd.getInstance().getReference().child("Police");
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    pid=(int)dataSnapshot.getChildrenCount();
                }
                else
                {

                }

            }


        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
}
    void reset()
    {
        name.setText("");
        username.setText("");
        password.setText("");
    }
    @Override
    public void onClick(View v) {
        Toast.makeText(this, "DATA ADDED", Toast.LENGTH_SHORT).show();
        s.setName(name.getText().toString());
        s.setBatch_id(String.valueOf(pid+1));
        s.setUsername(username.getText().toString());
        s.setPassword(password.getText().toString());
        dbr.child(String.valueOf(pid+1)).setValue(s);
        reset();
    }

}
