package com.devspace.scholastic;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class LoginActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText emailET, nameET, passwordET, confPassET, phoneET, signInPW;
    private RadioGroup userType;
    private RadioButton userTypeSelected;
    private Button enterMail, signUpBtn;
    private TextView dateOfBirth, dateOfBirthET, studentYearTV, userProfileTV;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
    private Spinner studentYear;
    private FirebaseAuth.AuthStateListener authStateListener;
    private int enterFlag = 1, userFlag = 1;
    private Date DOBDate, currentDate;
    private Calendar selectedDate;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private ProgressBar progressBar;

    private String UIDEmailID, DOB;

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
        progressBar = findViewById(R.id.emailVerifyProgress);

        currentDate = Calendar.getInstance().getTime();
        selectedDate = Calendar.getInstance();

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        enterMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enterFlag == 1) {
                    progressBar.setVisibility(View.VISIBLE);
                    enterFlag = 2;
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(enterMail.getWindowToken(), 0);
                    String EmailID = emailET.getText().toString().trim();
                    UIDEmailID = EmailID;
                    Toast.makeText(LoginActivity.this, "This gets executed 1.", Toast.LENGTH_SHORT).show();
                    if (isEmailValid(UIDEmailID)) {
                        firebaseAuth.fetchSignInMethodsForEmail(UIDEmailID).addOnSuccessListener(new OnSuccessListener<SignInMethodQueryResult>() {
                            @Override
                            public void onSuccess(SignInMethodQueryResult signInMethodQueryResult) {
                                List<String> signInMethods = signInMethodQueryResult.getSignInMethods();
                                if (signInMethods.size() != 0) {
                                    progressBar.setVisibility(View.GONE);
                                    signInPW.setVisibility(View.VISIBLE);
                                    enterMail.setText("Sign In");
                                } else {
                                    progressBar.setVisibility(View.GONE);
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
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else {
                    progressBar.setVisibility(View.GONE);
                    String password = signInPW.getText().toString().trim();
                    firebaseAuth.signInWithEmailAndPassword(UIDEmailID, password)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                                    updateUI(currentUser, userFlag);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        dateOfBirthET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "DatePicker");
            }
        });
    }

    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void signUp() {
        String password = confPassET.getText().toString().trim();
        firebaseAuth.createUserWithEmailAndPassword(UIDEmailID, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                        userFlag = 2;
                        Toast.makeText(LoginActivity.this, "SignUp gets executed.", Toast.LENGTH_SHORT).show();
                        updateUI(currentUser, userFlag);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String checkUserType(){
        int userTypeSelectedRadioID = userType.getCheckedRadioButtonId();
        userTypeSelected = findViewById(userTypeSelectedRadioID);
        String userTypeSelectedText = userTypeSelected.getText().toString();

        return userTypeSelectedText;
    }

    private void updateUI(FirebaseUser user, int userFlag) {
        if (userFlag == 2) {
            Map<String, Object> usersMap = new HashMap<>();
            usersMap.put("emailID", UIDEmailID);
            usersMap.put("dateOfBirth", DOB);
            usersMap.put("fullName", nameET.getText().toString().trim());
            usersMap.put("phoneNumber", phoneET.getText().toString().trim());
            usersMap.put("userType", checkUserType());
            usersMap.put("grade", studentYear.getSelectedItem());

            Toast.makeText(this, "updateUI 1 gets executed.", Toast.LENGTH_SHORT).show();

            db.collection("Users").document("User " + UIDEmailID).set(usersMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Intent intent = new Intent(LoginActivity.this, WelcomeNavbarActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("userType", checkUserType());
                    startActivity(intent);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Intent intent = new Intent(LoginActivity.this, WelcomeNavbarActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("userType", checkUserType());
            startActivity(intent);
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(YEAR, year);
        c.set(MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        DOBDate = c.getTime();
        selectedDate.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        DOB = DateFormat.getDateInstance(DateFormat.FULL).format(DOBDate);
        dateOfBirthET.setText(DOB);
    }

    private int checkDOBValidity(Date cDate, Date bDate){
        Calendar a = getCalendar(bDate);
        Calendar b = getCalendar(cDate);
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) ||
                (a.get(MONTH) == b.get(MONTH) && a.get(Calendar.DAY_OF_MONTH) > b.get(Calendar.DAY_OF_MONTH))) {
            diff--;
        }

        return diff;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTime(date);
        return cal;
    }
}