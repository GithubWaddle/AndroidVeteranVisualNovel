package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.utsold.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.R;

public class TitleMenu extends Menu {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_old_title_menu, container, false);

        // bind buttons
        Button bNewGame = view.findViewById(R.id.bNewGame);
        Button bLoadGame = view.findViewById(R.id.bLoadGame);
        Button bSettings= view.findViewById(R.id.bSettings);

        bNewGame.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.replaceCurrentFragment(new PlayingMenu());
            }
        });
        bLoadGame.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.replaceCurrentFragment(new LoadMenu());
            }
        });
        bSettings.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.replaceCurrentFragment(new SettingsMenu());
            }
        });

        return view;
    }
}
