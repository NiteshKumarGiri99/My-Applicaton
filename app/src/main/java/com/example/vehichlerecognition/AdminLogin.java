package com.example.vehichlerecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class AdminLogin extends AppCompatActivity {
    TextView tv;
    EditText e1,e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        tv=findViewById(R.id.show);
        e1=findViewById(R.id.usname);
        e2=findViewById(R.id.pass);
        tv.setText("Login "+getIntent().getExtras().getString("admin"));

    }

    public void Login(View view) {
        if(e1.getText().toString().equals("admin12345")&&e2.getText().toString().equals("12345"))
        {
            Intent i= new Intent(this,AdminFunction.class);
            startActivity(i);
        }
        else
        {
            Toast.makeText(this, "Wrong Input", Toast.LENGTH_SHORT).show();
        }
    }

}
