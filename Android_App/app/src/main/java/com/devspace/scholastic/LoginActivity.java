package com.devspace.scholastic;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    private EditText emailET, nameET, passwordET, confPassET, phoneET;
    private RadioGroup userType;
    private RadioButton userTypeSelected;
    private Button enterMail, signUpBtn;
    private TextView dateOfBirth, studentYearTV;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
    private Spinner studentYear;
    private FirebaseAuth.AuthStateListener authStateListener;
    private DatePickerDialog.OnDateSetListener dateSetListener; // Needs to be completed

    private String UIDEmailID;

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
        enterMail = findViewById(R.id.signupEnterEmailButton);
        signUpBtn = findViewById(R.id.signupButton);
        dateOfBirth = findViewById(R.id.dateOfBirth);
        studentYearTV = findViewById(R.id.studentSemTV);
        studentYear = findViewById(R.id.studentYearSpinner);

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        enterMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(enterMail.getWindowToken(), 0);
                String EmailID = emailET.getText().toString().trim();
                UIDEmailID = EmailID;
                if (isEmailValid(UIDEmailID)) {

                }
            }
        });
    }

    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}