package com.example.vehichlerecognition;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShDetails extends AppCompatActivity {
RecyclerView recyclerView;
FirebaseDatabase fd;
DatabaseReference dbr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sh_details);
        recyclerView=findViewById(R.id.rc11);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dbr=fd.getInstance().getReference("BlackList");
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<BlackList,MyCard> fr=new FirebaseRecyclerAdapter<BlackList, MyCard>(BlackList.class,R.layout.myd,MyCard.class,dbr) {
            @Override
            protected void populateViewHolder(MyCard myCard, BlackList blackList, int i) {
                myCard.setView(getApplicationContext(),blackList.getV_no(),blackList.getV_type());
            }
        };
        recyclerView.setAdapter(fr);
    }
}
