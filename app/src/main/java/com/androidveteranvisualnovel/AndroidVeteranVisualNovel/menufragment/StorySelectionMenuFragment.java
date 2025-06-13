package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.R;

public class StorySelectionMenuFragment extends MenuFragment {
    public static StorySelectionMenuFragment newInstance(String startingStoryId) {

        Bundle args = new Bundle();

        StorySelectionMenuFragment fragment = new StorySelectionMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                return;
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story_selection_menu, container, false);

        view.findViewById(R.id.button_to_story_start).setOnClickListener(v ->
                menuFragmentManager.switchToMenuFragment(new StoryStartMenuFragment())
        );

        view.findViewById(R.id.button_to_settings).setOnClickListener(v ->
                menuFragmentManager.overlayToMenuFragment(new SettingsMenuFragment())
        );

        return view;
    }
}
