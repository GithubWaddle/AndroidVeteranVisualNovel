package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.LoadedStoryActors;

import java.util.ArrayList;

public class StoryDatabase {
    final String STORY_DATABASE_ASSET_PATH = "storyDatabase";
    private static StoryDatabase instance;

    public StoryDatabase() {
        // load story database here
    }

    public static StoryDatabase getInstance() {
        if (instance == null) {
            instance = new StoryDatabase();
        }
        return instance;
    }

    public ArrayList<StoryData> getAllStories() {
        return new ArrayList<>();
    }

    public StoryData getStoryById(String id) {
        return new StoryData();
    }
}
