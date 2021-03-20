package com.devspace.scholastic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

public class LabClassesFragment extends Fragment {

    private MaterialButton arLab, joinClass;
    private TextView classStatus;
    private View root;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
    private String grade, meetURL;
    private ArrayList<String> subjects;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.labs_classes, container, false);

        arLab = root.findViewById(R.id.virtualLab);
        joinClass = root.findViewById(R.id.joinClass);
        classStatus = root.findViewById(R.id.classStatusTV);
        meetURL = "https://meet.google.com/oxj-ajue-ywo";

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        findPeriod();

        joinClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(meetURL));
                startActivity(intent);
            }
        });

        arLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish this
            }
        });

        return root;
    }

    private void findPeriod() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm a");
        date.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));

        String localTime = date.format(currentLocalTime);
        int i = 3;

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

                        String statusText = localTime + "\n" + subjects.get(i);
                        classStatus.setText(statusText);
                        classStatus.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
