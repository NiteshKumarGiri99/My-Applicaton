package com.example.vehichlerecognition;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShDetails2 extends AppCompatActivity {
RecyclerView recyclerView;
FirebaseDatabase fd;
DatabaseReference dbr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sh_details2);
        recyclerView=findViewById(R.id.rc12);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dbr=fd.getInstance().getReference().child("Police");
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Police,MyCard2> fr=new FirebaseRecyclerAdapter<Police, MyCard2>(Police.class,R.layout.myd2,MyCard2.class,dbr) {
            @Override
            protected void populateViewHolder(MyCard2 myCard2, Police police, int i) {
                myCard2.setView(getApplicationContext(),police.getName(),police.getBatch_id(),police.getUsername(),police.getPassword());
            }
        };
        recyclerView.setAdapter(fr);
    }
}
