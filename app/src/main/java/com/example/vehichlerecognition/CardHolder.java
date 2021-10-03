package com.example.vehichlerecognition;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CardHolder extends RecyclerView.ViewHolder {

    View v;

    public CardHolder(@NonNull View itemView) {
        super(itemView);
        v = itemView;
    }

    public void setView(Context context, String name, String batch, String username, String password) {
        TextView sname = v.findViewById(R.id.name1);
        TextView sbatch = v.findViewById(R.id.batch_id1);
        TextView susername = v.findViewById(R.id.username1);
        TextView spassword = v.findViewById(R.id.password1);
        sname.setText(name);
        sbatch.setText(batch);
        susername.setText(username);
        spassword.setText(password);


    }
}

