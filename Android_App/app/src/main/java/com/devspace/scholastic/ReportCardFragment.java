package com.devspace.scholastic;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ReportCardFragment extends Fragment {

    private MaterialButton resultsBtn;
    private View root;
    private EditText emailET, passwordET;
    private String emailID, password, rcLink;
    private ProgressBar progressBar;
    private int userFlag = 0;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.report_card_frag, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        progressBar = root.findViewById(R.id.rcProgress);
        emailET = root.findViewById(R.id.rcEmailEditText);
        passwordET = root.findViewById(R.id.rcPassword);
        resultsBtn = root.findViewById(R.id.rcSignIn);

        resultsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userFlag == 0) {
                    progressBar.setVisibility(View.VISIBLE);
                    userFlag = 1;
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(resultsBtn.getWindowToken(), 0);
                    String EmailID = emailET.getText().toString().trim();
                    emailID = EmailID;
                    firebaseAuth.fetchSignInMethodsForEmail(emailID).addOnSuccessListener(new OnSuccessListener<SignInMethodQueryResult>() {
                        @Override
                        public void onSuccess(SignInMethodQueryResult signInMethodQueryResult) {
                            List<String> signInMethods = signInMethodQueryResult.getSignInMethods();
                            if (signInMethods.size() != 0) {
                                progressBar.setVisibility(View.GONE);
                                passwordET.setVisibility(View.VISIBLE);
                                resultsBtn.setText("View Result");
                            } else {
                                Toast.makeText(getActivity(), "Incorrect credential.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    db.collection("Report Cards").document("Grade 10").get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    rcLink = documentSnapshot.get(firebaseAuth.getCurrentUser().getEmail()).toString();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    rcLink = "https://bafybeihyuyvitbirc3usojlaqpzghqd4zsmllmnwnasostq4gairx26zpa.ipfs.infura-ipfs.io/";
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(rcLink));
                    startActivity(intent);
                }
            }
        });

        return root;
    }
}
