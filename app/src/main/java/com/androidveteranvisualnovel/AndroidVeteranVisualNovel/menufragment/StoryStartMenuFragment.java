package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.R;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.PlayFragment;

public class StoryStartMenuFragment extends MenuFragment {
    private String storyId;

    public static StoryStartMenuFragment newInstance(String storyId) {

        Bundle args = new Bundle();

        StoryStartMenuFragment fragment = new StoryStartMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                menuFragmentManager.switchToMenuFragment(new StorySelectionMenuFragment());
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story_start_menu, container, false);

        view.findViewById(R.id.button_to_play).setOnClickListener(v ->
                menuFragmentManager.switchToMenuFragment(new PlayFragment())
        );

        view.findViewById(R.id.button_to_settings).setOnClickListener(v ->
                menuFragmentManager.overlayToMenuFragment(new SettingsMenuFragment())
        );

        return view;
    }
}
