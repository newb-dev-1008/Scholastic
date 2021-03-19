package com.devspace.scholastic;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    private EditText emailET, nameET, passwordET, confPassET, phoneET;
    private RadioGroup userType;
    private RadioButton userTypeSelected;
    private Button enterMail, signUpBtn;
    private TextView dateOfBirth,studentYearTV;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
    private Spinner studentYear;
    private DatePickerDialog.OnDateSetListener dateSetListener; // Needs to be completed


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        emailET = findViewById(R.id.signupEmailEditText);
        nameET = findViewById(R.id.signupNameEditText);
        passwordET = findViewById(R.id.signupPasswordEditText);
        confPassET = findViewById(R.id.signupConfPasswordEditText);
        phoneET = findViewById(R.id.phoneNumberEditText);
        userType = findViewById(R.id.userTypeRadioGroup);
        userTypeSelected = findViewById(R.id.userType);
        enterMail = findViewById(R.id.signupEnterEmailButton);
        signUpBtn = findViewById(R.id.signupButton);
        dateOfBirth = findViewById(R.id.dateOfBirth);
        studentYearTV = findViewById(R.id.studentSemTV);
        studentYear = findViewById(R.id.studentYearSpinner);

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
