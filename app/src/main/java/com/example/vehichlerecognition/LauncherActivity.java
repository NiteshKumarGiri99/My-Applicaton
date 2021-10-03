package com.example.vehichlerecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
    }

    public void admin(View view) {
        Intent i=new Intent(this,AdminLogin.class);
        i.putExtra("admin","Admin");
        startActivity(i);
    }

    public void police(View view) {
        Intent i=new Intent(this,PoliceLogin.class);
        i.putExtra("police","Police");
        startActivity(i);

    }

}
