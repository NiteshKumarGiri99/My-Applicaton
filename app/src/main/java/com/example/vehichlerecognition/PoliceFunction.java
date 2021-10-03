package com.example.vehichlerecognition;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class PoliceFunction extends AppCompatActivity {
    ImageView iv;
    Bitmap bm1;
    EditText e;
    FirebaseDatabase fd;
    DatabaseReference dbr;
    Notification n;
    NotificationManager nm;
    NotificationChannel nc;
    NotificationCompat.Builder nb;
    final static String channelId="mynotification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_function);
        iv=findViewById(R.id.iv);
        e=findViewById(R.id.rest);
        nm=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }

    public void scan(View view) {
        Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,1234);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1234&&resultCode==RESULT_OK)
        {
            Bitmap bm=data.getExtras().getParcelable("data");
            bm1=bm;
            iv.setImageBitmap(bm);
            Toast.makeText(this, "Image Captured", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Camera is not working", Toast.LENGTH_SHORT).show();
        }
    }

    public void serach1(View view) {
        TextRecognizer tr=new TextRecognizer.Builder(getApplicationContext()).build();
        if(!tr.isOperational())
        {
            Toast.makeText(this, "Error!!!  Can't work", Toast.LENGTH_SHORT).show();
          return;
        }
        if(bm1==null)
        {
            return;
        }
        StringBuilder sb=new StringBuilder();
        Frame f=new Frame.Builder().setBitmap(bm1).build();
        SparseArray<TextBlock> sr=tr.detect(f);
        for(int i=0;i<sr.size();i++)
        {
            TextBlock tb=sr.valueAt(i);
            sb.append(tb.getValue());
            sb.append("\n");
        }
        e.setText(sb.toString());
        Toast.makeText(this, "Text Scanned!!!", Toast.LENGTH_SHORT).show();
    }
    private void createChannel()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        { CharSequence name=getString(R.string.ChannelName);
          String desc=getString(R.string.chnannelDescription);
          int imp=NotificationManager.IMPORTANCE_DEFAULT;
            nc=new NotificationChannel(channelId,name,imp);
            nc.setDescription(desc);
            nm.createNotificationChannel(nc);

        }
    }

    public void show1(View view) {
        Intent i=new Intent(this,ShDetails.class);
        startActivity(i);
    }

    public void checkList(View view) {
        dbr=fd.getInstance().getReference().child("BlackList");
        dbr=dbr.child(e.getText().toString());
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    if(e.getText().toString().equals(""))
                    {
                        Toast.makeText(PoliceFunction.this, "Scan a number", Toast.LENGTH_LONG).show();
                        return;
                    }

                    Toast.makeText(PoliceFunction.this, "BlackListed", Toast.LENGTH_SHORT).show();
                 nb=new NotificationCompat.Builder(PoliceFunction.this,channelId);
                 nb.setSmallIcon(android.R.drawable.star_on);
                 nb.setContentTitle("Red Alert");
                 nb.setContentText("This is a blacklisted vehichle");
                 nb.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                 createChannel();
                 Intent i=new Intent(PoliceFunction.this,Msg.class);
                 PendingIntent pi=PendingIntent.getActivity(PoliceFunction.this,0,i,0);
                 nb.setContentIntent(pi);
                 nb.setAutoCancel(true);
                 NotificationManagerCompat nmc=NotificationManagerCompat.from(PoliceFunction.this);
                 nmc.notify(0,nb.build());

                }
                else
                {
                    if(e.getText().toString().length()!=8)
                    {
                        Toast.makeText(PoliceFunction.this, "Enter a valid no", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(PoliceFunction.this, "WhiteListed!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
