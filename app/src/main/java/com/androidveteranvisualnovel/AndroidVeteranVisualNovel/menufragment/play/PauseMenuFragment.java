package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.R;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.LoadMenuFragment;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.MenuFragment;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.SaveMenuFragment;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.SettingsMenuFragment;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.StorySelectionMenuFragment;

public class PauseMenuFragment extends MenuFragment {
    public static PauseMenuFragment newInstance() {

        Bundle args = new Bundle();

        PauseMenuFragment fragment = new PauseMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // You control the back press behavior here
                menuFragmentManager.removeTopMenuFragment(); // close the overlay
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pause_menu, container, false);

        view.findViewById(R.id.button_to_continue).setOnClickListener(v ->
                menuFragmentManager.removeTopMenuFragment()
        );

        view.findViewById(R.id.button_to_save).setOnClickListener(v ->
                menuFragmentManager.overlayToMenuFragment(new SaveMenuFragment())
        );

        view.findViewById(R.id.button_to_load).setOnClickListener(v ->
                menuFragmentManager.overlayToMenuFragment(new LoadMenuFragment())
        );

        view.findViewById(R.id.button_to_settings).setOnClickListener(v ->
                menuFragmentManager.overlayToMenuFragment(new SettingsMenuFragment())
        );

        view.findViewById(R.id.button_exit_to_selection).setOnClickListener(v ->
                menuFragmentManager.switchToMenuFragment(new StorySelectionMenuFragment())
        );

        return view;
    }
}
