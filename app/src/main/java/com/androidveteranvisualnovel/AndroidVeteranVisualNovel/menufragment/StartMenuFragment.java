package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.R;

public class StartMenuFragment extends MenuFragment {

    public static StartMenuFragment newInstance() {
        Bundle args = new Bundle();
        StartMenuFragment fragment = new StartMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void goToStorySelectionMenu() {
        menuFragmentManager.switchToMenuFragment(StorySelectionMenuFragment.newInstance());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_menu, container, false);

        view.setOnTouchListener((v, event) -> {
            goToStorySelectionMenu();
            return true;
        });

        return view;
    }
}
