package com.example.vehichlerecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ManagePolice extends AppCompatActivity implements View.OnClickListener {
    EditText e;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_police);
        e=findViewById(R.id.bd);
        b=findViewById(R.id.bt);
        b.setOnClickListener(this);

    }
    public void show(View view)
    {
        Intent cpp=new Intent(this,ShDetails2.class);
        startActivity(cpp);
    }

    @Override
    public void onClick(View v) {
        if(e.getText().toString().equals(""))
        {
            Toast.makeText(this, "Please input valid ID", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent i = new Intent(this, SearchSave.class);
            i.putExtra("batch_id", e.getText().toString());
            startActivity(i);
            e.setText("");
        }
    }

}
