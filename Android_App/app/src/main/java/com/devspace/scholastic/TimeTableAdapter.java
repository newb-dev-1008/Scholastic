package com.devspace.scholastic;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.TTViewHolder> {
    private ArrayList<TimeTable> timeTable;
    private Context context;

    public static class TTViewHolder extends RecyclerView.ViewHolder {
        public TextView timeTV, subjectTV;
        public MaterialButton joinClass;
        public TTViewHolder(View itemView) {
            super(itemView);
            timeTV = itemView.findViewById(R.id.timeTV);
            subjectTV = itemView.findViewById(R.id.subjectTV);
            joinClass = itemView.findViewById(R.id.joinClass);
        }
    }
    public TimeTableAdapter(ArrayList<TimeTable> timeTableList) {
        timeTable = timeTableList;
    }
    @Override
    public TTViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tt_card, parent, false);
        TTViewHolder evh = new TTViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(TTViewHolder holder, int position) {
        TimeTable currentItem = timeTable.get(position);
        holder.timeTV.setText(currentItem.getTime());
        holder.subjectTV.setText(currentItem.getSubject());
        holder.joinClass.setText("Join Class");
        String URL = currentItem.getClassLink();
        holder.joinClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(URL));
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return timeTable.size();
    }
}
