package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.R;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save.SaveData;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save.SaveDatabase;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.StoryData;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.StoryDatabase;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.PlayFragment;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.saveload.LoadMenuFragment;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.settings.SettingsMenuFragment;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.storyselection.StorySelectionMenuFragment;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class StoryStartMenuFragment extends MenuFragment {
    static final String ARGUMENT_STORY_ID = "story_id";
    private StoryData storyData;

    public static StoryStartMenuFragment newInstance(String storyId) {
        Bundle args = new Bundle();
        args.putString(ARGUMENT_STORY_ID, storyId);

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

        String storyId = getArguments() != null ? getArguments().getString(ARGUMENT_STORY_ID) : null;
        storyData = StoryDatabase.getInstance().getStoryById(storyId);
        //if (storyData == null) {
        //    throw new RuntimeException("StoryStartMenu cannot be accessed without a story!");
        //}
        TextView title = view.findViewById(R.id.tvTitle);
        TextView subtitle = view.findViewById(R.id.tvSubtitle);
        ImageView thumbnail = view.findViewById(R.id.ivThumbnail);
        TextView description = view.findViewById(R.id.tvDescription);

        title.setText(storyData.title);
        subtitle.setText(String.format("By: %s", storyData.author));
        description.setText(storyData.synopsisTagline);

        try (InputStream inputStream = view.getContext().getAssets().open(storyData.storyFolderRelativePathToArtCover)) {
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            thumbnail.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
            thumbnail.setImageResource(android.R.drawable.ic_menu_report_image);
        }

        Button bContinue = view.findViewById(R.id.bContinue);
        Button bNewGame = view.findViewById(R.id.bNewGame);
        SaveData saveData = SaveDatabase.getInstance().getSave(storyData.id, SaveDatabase.AUTOSAVE_ID);

        if (saveData == null) {
            bContinue.setText(bNewGame.getText());
            bNewGame.setVisibility(View.GONE);

            SaveData newSaveData = new SaveData(
                    storyData.id,
                    storyData.startSceneId,
                    0,
                    new HashMap<>()
            );
            SaveDatabase.getInstance().createSave(
                    newSaveData.storyId,
                    SaveDatabase.AUTOSAVE_ID,
                    newSaveData
            );

            bContinue.setOnClickListener(v -> menuFragmentManager.switchToMenuFragment(PlayFragment.newInstance(
                    storyId,
                    SaveDatabase.AUTOSAVE_ID
            )));
        } else {
            bContinue.setOnClickListener(v -> menuFragmentManager.switchToMenuFragment(PlayFragment.newInstance(
                    storyId,
                    SaveDatabase.AUTOSAVE_ID
            )));
            bNewGame.setOnClickListener(v -> {
                SaveDatabase.getInstance().deleteSave(storyId, SaveDatabase.AUTOSAVE_ID);
                SaveData newSaveData = new SaveData(
                        storyData.id,
                        storyData.startSceneId,
                        0,
                        new HashMap<>()
                );
                SaveDatabase.getInstance().createSave(
                        newSaveData.storyId,
                        SaveDatabase.AUTOSAVE_ID,
                        newSaveData
                );

                menuFragmentManager.switchToMenuFragment(PlayFragment.newInstance(
                        storyId,
                        SaveDatabase.AUTOSAVE_ID
                ));
            });
        }
        view.findViewById(R.id.bLoad).setOnClickListener(v ->
                menuFragmentManager.overlayToMenuFragment(LoadMenuFragment.newInstance(
                        storyId
                ))
        );

        view.findViewById(R.id.bSettings).setOnClickListener(v ->
                menuFragmentManager.overlayToMenuFragment(SettingsMenuFragment.newInstance())
        );

        view.findViewById(R.id.bStorySelection).setOnClickListener(v ->
                menuFragmentManager.overlayToMenuFragment(
                        StorySelectionMenuFragment.newInstance(storyData.id)
                )
        );

        return view;
    }
}
