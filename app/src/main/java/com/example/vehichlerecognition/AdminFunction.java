package com.example.vehichlerecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class AdminFunction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_function);
    }

    public void addpolice(View view) {
        Intent i=new Intent(this,AddPolice.class);
        startActivity(i);
    }
    public void manage(View view)
    {
        Intent i=new Intent(this,ManagePolice.class);
        startActivity(i);
    }

    public void createDetails(View view) {
        Intent i=new Intent(this,Vehichle_Details.class);
        startActivity(i);
    }

    public void view(View view) {
        Intent i=new Intent(this,ShDetails.class);
        startActivity(i);

    }


}
