package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment;

import android.os.Bundle;

public class StoryStartMenuFragment extends MenuFragment {
    private String storyId;

    public static StoryStartMenuFragment newInstance(String storyId) {

        Bundle args = new Bundle();

        StoryStartMenuFragment fragment = new StoryStartMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
