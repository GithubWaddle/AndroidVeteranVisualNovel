package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menu;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.R;

import java.util.Objects;

public class PlayingMenu extends Menu {
    boolean isSkipping = false;
    LinearLayout llAutoSkipSign;
    ImageButton ibSkipButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playing_section, container, false);

        // binding buttons
        ImageButton ibPauseButton = view.findViewById(R.id.ibPauseButton);
        ibSkipButton = view.findViewById(R.id.ibSkipButton);
        llAutoSkipSign = view.findViewById(R.id.llAutoSkipSign);

        ibPauseButton.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.overlayFragment(new PauseMenu());
            }
        });

        llAutoSkipSign.setVisibility(View.GONE);
        ibSkipButton.setOnClickListener(v -> {
            toggleIsSkipping();
        });

        return view;
    }

    public void toggleIsSkipping() {
        isSkipping = !isSkipping;
        if (isSkipping) {
            ibSkipButton.setBackgroundResource(R.drawable.image_button_filled);
            ImageViewCompat.setImageTintList(
                    ibSkipButton,
                    ColorStateList.valueOf(
                            ContextCompat.getColor(
                                    requireContext(), R.color.secondary
                            )));
            llAutoSkipSign.setVisibility(View.VISIBLE);
            return;
        }
        ImageViewCompat.setImageTintList(
                ibSkipButton,
                ColorStateList.valueOf(
                        ContextCompat.getColor(
                                requireContext(), R.color.primary
                        )));
        ibSkipButton.setBackgroundResource(R.drawable.image_button_border);
        llAutoSkipSign.setVisibility(View.GONE);
    }
}
