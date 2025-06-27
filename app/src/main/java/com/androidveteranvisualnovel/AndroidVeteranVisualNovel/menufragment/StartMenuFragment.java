package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.R;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.storyselection.StorySelectionMenuFragment;

public class StartMenuFragment extends MenuFragment {

    public static StartMenuFragment newInstance() {
        Bundle args = new Bundle();
        StartMenuFragment fragment = new StartMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void goToStorySelectionMenu() {
        menuFragmentManager.switchToMenuFragment(StorySelectionMenuFragment.newInstance("TheTemplateStory"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_menu, container, false);

        TextView textView = view.findViewById(R.id.tvPrompt);

        AlphaAnimation fade = new AlphaAnimation(0.0f, 1.0f);
        fade.setDuration(1000); // 1 second
        fade.setRepeatMode(Animation.REVERSE);
        fade.setRepeatCount(Animation.INFINITE);

        textView.startAnimation(fade);


        view.setOnTouchListener((v, event) -> {
            goToStorySelectionMenu();
            return true;
        });

        return view;
    }
}
