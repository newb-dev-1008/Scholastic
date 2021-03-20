package com.devspace.scholastic;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;


public class PlagiarismCheckFragment extends Fragment {

    private View root;
    private MaterialButton upload1, upload2, simCheckBtn;
    private TextView plagDescTV, upload1TV, upload2TV, simResult;
    private ProgressBar simProg;
    private Uri fileUri;
    private String filePath, a, b;
    private FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;

    public static final int PICKFILE_RESULT_CODE_1 = 1;
    public static final int PICKFILE_RESULT_CODE_2 = 2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = super.onCreateView(inflater, container, savedInstanceState);
        upload1 = root.findViewById(R.id.upload1btn);
        upload2 = root.findViewById(R.id.upload2btn);
        simCheckBtn = root.findViewById(R.id.simScoreBtn);
        plagDescTV = root.findViewById(R.id.plagDescTV);
        upload1TV = root.findViewById(R.id.upload1);
        upload2TV = root.findViewById(R.id.upload2);
        simResult = root.findViewById(R.id.simScoreTV);
        simProg = root.findViewById(R.id.plagProgress);

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        upload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                chooseFile.setType("*/*");
                chooseFile = Intent.createChooser(chooseFile, "Choose a file");
                simProg.setVisibility(View.VISIBLE);
                startActivityForResult(chooseFile, PICKFILE_RESULT_CODE_1);
            }
        });

        upload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                chooseFile.setType("*/*");
                chooseFile = Intent.createChooser(chooseFile, "Choose a file");
                simProg.setVisibility(View.VISIBLE);
                startActivityForResult(chooseFile, PICKFILE_RESULT_CODE_2);
            }
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PICKFILE_RESULT_CODE_1:
                if (resultCode == -1) {
                    fileUri = data.getData();
                    filePath = fileUri.getPath();
                    File file = new File(filePath);
                    a = fileToString(file);
                    simProg.setVisibility(View.GONE);
                }
                break;
            case PICKFILE_RESULT_CODE_2:
                if (resultCode == -1) {
                    fileUri = data.getData();
                    filePath = fileUri.getPath();
                    File file = new File(filePath);
                    b = fileToString(file);
                    simProg.setVisibility(View.GONE);
                }
                break;
        }
    }

    private String fileToString(File file) {
        StringBuilder str = new StringBuilder();
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                str.append(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return str.toString();
    }
}
