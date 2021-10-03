package com.example.vehichlerecognition;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyCard2 extends RecyclerView.ViewHolder {
    View view;
    public MyCard2(@NonNull View itemView) {
        super(itemView);
        view=itemView;
    }
    public void setView(Context context,String nm,String btch,String us,String ps)
    {
        TextView snm=view.findViewById(R.id.name1);
        TextView sbtch=view.findViewById(R.id.batch_id1);
        TextView sus=view.findViewById(R.id.username1);
        TextView sps=view.findViewById(R.id.password1);
        snm.setText(nm);
        sbtch.setText(btch);
        sus.setText(us);
        sps.setText(ps);
    }
}
