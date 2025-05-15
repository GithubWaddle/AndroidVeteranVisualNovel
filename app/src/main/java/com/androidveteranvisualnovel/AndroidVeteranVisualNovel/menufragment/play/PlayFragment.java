package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play;

import android.os.Bundle;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save.SaveData;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.MenuFragment;

public class PlayFragment extends MenuFragment {
    StoryScenePlayer storyScenePlayer;

    public static PlayFragment newInstance(SaveData saveData) {

        Bundle args = new Bundle();

        PlayFragment fragment = new PlayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void play() {

    }

    public void stop() {

    }

}
