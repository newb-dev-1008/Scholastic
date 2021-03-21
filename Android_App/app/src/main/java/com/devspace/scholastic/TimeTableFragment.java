package com.devspace.scholastic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Transition;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TimeTableFragment extends Fragment {

    private View root;
    private RecyclerView timetableRV;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;
    private String grade, meetURL;
    private ArrayList<String> subjects;

    private RecyclerView.LayoutManager timeTableLayoutManager;
    private RecyclerView.Adapter timeTableAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.timetable_frag, container, false);
        ArrayList<TimeTable> timeTable = new ArrayList<>();
        // Map<String, Object> dbTimeTable = new HashMap<>();

        timetableRV = root.findViewById(R.id.timetableRV);

        meetURL = "https://meet.google.com/oxj-ajue-ywo";

        String[] timings = {"8:00am", "9:00am", "10:30am", "11:30am", "1:30pm"};

        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        db.collection("Users").document("User " + firebaseAuth.getCurrentUser().getEmail())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        grade = documentSnapshot.get("grade").toString();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        db.collection("Classes").document(grade).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Map<String, Object> timeTableMap = (Map<String, Object>) documentSnapshot.get("timeTable");
                        subjects = (ArrayList<String>) timeTableMap.get(1);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        for (int i = 0; i < timings.length; i++) {
            timeTable.add(new TimeTable(timings[i], subjects.get(i), meetURL));

            timeTableLayoutManager = new LinearLayoutManager(getContext());
            timeTableAdapter = new TimeTableAdapter(timeTable);
            timetableRV.setHasFixedSize(true);
            timetableRV.setLayoutManager(timeTableLayoutManager);
            timetableRV.setAdapter(timeTableAdapter);
        }

        return root;
    }
}
