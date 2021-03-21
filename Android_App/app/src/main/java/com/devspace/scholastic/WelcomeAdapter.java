package com.devspace.scholastic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WelcomeAdapter extends RecyclerView.Adapter<WelcomeAdapter.WelcomeViewHolder> {

    private ArrayList<Deadlines> mWelcomeList;

    public static class WelcomeViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mSubtitle;
        public TextView mBody;

        public WelcomeViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.welcomeTitle);
            mSubtitle = itemView.findViewById(R.id.welcomeSubtitle);
            mBody = itemView.findViewById(R.id.welcomeBody);
        }
    }

    public WelcomeAdapter(ArrayList<Deadlines> exampleList) {
        mWelcomeList = exampleList;
    }

    @Override
    public WelcomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.welcome_card, parent, false);
        WelcomeViewHolder evh = new WelcomeViewHolder(v);
        return evh;
    }
    @Override
    public void onBindViewHolder(WelcomeViewHolder holder, int position) {
        Deadlines currentItem = mWelcomeList.get(position);
        holder.mTitle.setText(currentItem.getTitle());
        holder.mSubtitle.setText(currentItem.getSubheading());
        holder.mBody.setText(currentItem.getBody());

    }
    @Override
    public int getItemCount() {
        return mWelcomeList.size();
    }
}
