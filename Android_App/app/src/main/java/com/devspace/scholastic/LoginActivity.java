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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText emailET, nameET, passwordET, confPassET, phoneET, signInPW;
    private RadioGroup userType;
    private RadioButton userTypeSelected;
    private Button enterMail, signUpBtn;
    private TextView dateOfBirth, dateOfBirthET, studentYearTV, userProfileTV;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
    private Spinner studentYear;
    private FirebaseAuth.AuthStateListener authStateListener;
    private int enterFlag = 1;
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
        dateOfBirthET = findViewById(R.id.dateOfBirthET);
        studentYearTV = findViewById(R.id.studentSemTV);
        studentYear = findViewById(R.id.studentYearSpinner);
        signInPW = findViewById(R.id.signinPassword);
        userProfileTV = findViewById(R.id.userType);

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        enterMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enterFlag == 1) {
                    enterFlag = 2;
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(enterMail.getWindowToken(), 0);
                    String EmailID = emailET.getText().toString().trim();
                    UIDEmailID = EmailID;
                    if (isEmailValid(UIDEmailID)) {
                        firebaseAuth.fetchSignInMethodsForEmail(UIDEmailID).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                            @Override
                            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                                if (task.isSuccessful()) {
                                    List<String> signInMethods = task.getResult().getSignInMethods();
                                    if (signInMethods.size() != 0) {
                                        signInPW.setVisibility(View.VISIBLE);
                                        enterMail.setText("Sign In");
                                    } else {
                                        Toast.makeText(LoginActivity.this, "You haven't signed up yet. Sign up now!", Toast.LENGTH_SHORT).show();
                                        emailET.setVisibility(View.GONE);
                                        enterMail.setVisibility(View.GONE);
                                        nameET.setVisibility(View.VISIBLE);
                                        passwordET.setVisibility(View.VISIBLE);
                                        confPassET.setVisibility(View.VISIBLE);
                                        phoneET.setVisibility(View.VISIBLE);
                                        dateOfBirth.setVisibility(View.VISIBLE);
                                        dateOfBirthET.setVisibility(View.VISIBLE);
                                        userProfileTV.setVisibility(View.VISIBLE);
                                        userType.setVisibility(View.VISIBLE);
                                        studentYearTV.setVisibility(View.VISIBLE);
                                        studentYear.setVisibility(View.VISIBLE);
                                        signUpBtn.setVisibility(View.VISIBLE);
                                    }
                                }
                            }
                        });
                    }
                } else {

                }
            }
        });
    }

    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}