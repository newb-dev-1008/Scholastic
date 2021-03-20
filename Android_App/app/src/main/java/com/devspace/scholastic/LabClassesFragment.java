package com.devspace.scholastic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

public class LabClassesFragment extends Fragment {

    private MaterialButton arLab, joinClass;
    private TextView classStatus;
    private View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.labs_classes, container, false);

        arLab = root.findViewById(R.id.virtualLab);
        joinClass = root.findViewById(R.id.joinClass);
        classStatus = root.findViewById(R.id.classStatusTV);

        return root;
    }
}
