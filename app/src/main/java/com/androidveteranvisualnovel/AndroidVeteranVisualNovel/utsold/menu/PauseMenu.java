package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.utsold.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.R;

public class PauseMenu extends Menu {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pause_menu, container, false);

        // binding buttons
        Button bContinue = view.findViewById(R.id.bContinue);
        Button bSave = view.findViewById(R.id.bSave);
        Button bLoad = view.findViewById(R.id.bLoad);
        Button bQuit = view.findViewById(R.id.bQuit);

        bContinue.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.removeFragment(this);
            }
        });
        bSave.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.removeFragment(this);
                mListener.replaceCurrentFragment(new SaveMenu());
            }
        });
        bLoad.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.removeFragment(this);
                mListener.replaceCurrentFragment(new LoadMenu());
            }
        });
        bQuit.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.removeFragment(this);
                mListener.replaceCurrentFragment(new TitleMenu());
            }
        });

        return view;
    }
}
