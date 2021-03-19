package com.devspace.scholastic;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailET, nameET, passwordET, confPassET, phoneET;
    private RadioGroup userType;
    private Button enterMail, signUpBtn;
    private TextView dateOfBirth,studentYearTV;
    private Spinner studentYear;
    private DatePickerDialog.OnDateSetListener dateSetListener; // Needs to be completed


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }
}
