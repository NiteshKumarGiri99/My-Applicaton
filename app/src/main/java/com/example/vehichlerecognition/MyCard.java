package com.example.vehichlerecognition;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyCard extends RecyclerView.ViewHolder {
    View view;
    public MyCard(@NonNull View itemView) {
        super(itemView);
        view=itemView;
    }
public void setView(Context context,String m_no,String m_type)
{
    TextView sm=view.findViewById(R.id.vehichle_no1);
    TextView stype=view.findViewById(R.id.vehichle_type1);
    sm.setText(m_no);
    stype.setText(m_type);
}
}
