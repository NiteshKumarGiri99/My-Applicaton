package com.example.vehichlerecognition;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

public class CameraDemo extends AppCompatActivity {
    Button click;
    TextView t;
    ImageView iv;
    Bitmap bm1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_demo);
        iv=findViewById(R.id.iv);
        t=findViewById(R.id.rest);
        click=findViewById(R.id.click);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,1234);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1234 && resultCode==RESULT_OK) {
            Bundle b = data.getExtras();
            Bitmap bm = b.getParcelable("data");
            bm1=bm;
            iv.setImageBitmap(bm);
            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
        }

    }

    public void convert(View view) {
        TextRecognizer tr=new TextRecognizer.Builder(getApplicationContext()).build();
        if(!tr.isOperational())
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Frame f=new Frame.Builder().setBitmap(bm1).build();
            SparseArray<TextBlock> sa=tr.detect(f);
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<sa.size();i++)
            {
                TextBlock tb=sa.valueAt(i);
                sb.append(tb.getValue());
                sb.append("\n");
            }
            t.setText(sb.toString());
        }
    }
}
