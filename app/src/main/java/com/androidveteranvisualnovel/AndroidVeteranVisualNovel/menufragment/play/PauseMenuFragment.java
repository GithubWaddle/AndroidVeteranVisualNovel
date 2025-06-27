package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.R;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save.SaveDatabase;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.StoryDatabase;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.saveload.LoadMenuFragment;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.MenuFragment;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.saveload.SaveMenuFragment;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.settings.SettingsMenuFragment;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.storyselection.StorySelectionMenuFragment;

public class PauseMenuFragment extends MenuFragment {
    final static private String ARGUMENT_STORY_ID = "story_id";
    final static private String ARGUMENT_SAVE_ID = "save_id";

    public static PauseMenuFragment newInstance(String storyId, int saveId) {
        Bundle args = new Bundle();
        args.putString(ARGUMENT_STORY_ID, storyId);
        args.putInt(ARGUMENT_SAVE_ID, saveId);

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

        String storyId = getArguments() != null ? getArguments().getString(ARGUMENT_STORY_ID) : null;
        int saveId = getArguments() != null ? getArguments().getInt(ARGUMENT_SAVE_ID) : SaveDatabase.AUTOSAVE_ID;

        TextView subtitle = view.findViewById(R.id.tvSubtitle);
        subtitle.setText(StoryDatabase.getInstance().getStoryById(storyId).title);

        view.findViewById(R.id.bContinue).setOnClickListener(v ->
                menuFragmentManager.removeTopMenuFragment()
        );

        view.findViewById(R.id.bSave).setOnClickListener(v ->
                menuFragmentManager.overlayToMenuFragment(SaveMenuFragment.newInstance(storyId))
        );

        view.findViewById(R.id.bLoad).setOnClickListener(v ->
                menuFragmentManager.overlayToMenuFragment(LoadMenuFragment.newInstance(storyId))
        );

        view.findViewById(R.id.bSettings).setOnClickListener(v ->
                menuFragmentManager.overlayToMenuFragment(new SettingsMenuFragment())
        );

        view.findViewById(R.id.bQuit).setOnClickListener(v ->
                menuFragmentManager.switchToMenuFragment(StorySelectionMenuFragment.newInstance(storyId))
        );

        return view;
    }
}
