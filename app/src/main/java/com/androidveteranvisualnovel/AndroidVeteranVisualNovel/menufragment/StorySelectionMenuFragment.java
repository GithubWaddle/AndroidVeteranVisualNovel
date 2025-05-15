package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment;

import android.os.Bundle;

public class StorySelectionMenuFragment extends MenuFragment {
    public static StorySelectionMenuFragment newInstance(String startingStoryId) {

        Bundle args = new Bundle();

        StorySelectionMenuFragment fragment = new StorySelectionMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
