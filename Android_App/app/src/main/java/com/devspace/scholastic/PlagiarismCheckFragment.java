package com.devspace.scholastic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;


public class PlagiarismCheckFragment extends Fragment {

    private View root;
    private MaterialButton upload1, upload2, simCheckBtn;
    private TextView plagDescTV, upload1TV, upload2TV, simResult;
    private ProgressBar simProg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = super.onCreateView(inflater, container, savedInstanceState);

        return root;
    }
}
