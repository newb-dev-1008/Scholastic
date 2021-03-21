package com.devspace.scholastic;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class WelcomeScreenFragment extends Fragment {

    private RecyclerView deadlinesRV, eventsRV, announcementsRV;
    private String userType;
    private TextView userName;
    private View root;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.home_screen, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        userName = root.findViewById(R.id.homeNameTV);
        deadlinesRV = root.findViewById(R.id.homeDeadlinesRecycler);
        eventsRV = root.findViewById(R.id.homeEventsRecycler);
        announcementsRV = root.findViewById(R.id.homeAnnRecycler);

        db.collection("Users").document("User " + firebaseAuth.getCurrentUser().getEmail()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String name = documentSnapshot.get("fullName").toString();
                        userName.setText(name);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        ArrayList<Deadlines> deadlines = new ArrayList<>();
        deadlines.add(new Deadlines("Fee Payment", "Rs. 5000", "Due on 25th of March"));
        deadlines.add(new Deadlines("Submission", "Maths Record", "Due tomorrow, 1:00 AM"));
        deadlinesRV.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new WelcomeAdapter(deadlines);
        deadlinesRV.setLayoutManager(mLayoutManager);
        deadlinesRV.setAdapter(mAdapter);

        ArrayList<Deadlines> events = new ArrayList<>();
        events.add(new Deadlines("Next Period", "Physics Laboratory", ""));
        eventsRV.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new WelcomeAdapter(events);
        eventsRV.setLayoutManager(mLayoutManager);
        eventsRV.setAdapter(mAdapter);

        ArrayList<Deadlines> announcements = new ArrayList<>();
        announcements.add(new Deadlines("Exam", "Chemistry", "23 March, 2021"));
        announcementsRV.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new WelcomeAdapter(announcements);
        announcementsRV.setLayoutManager(mLayoutManager);
        announcementsRV.setAdapter(mAdapter);

        return root;
    }

}
