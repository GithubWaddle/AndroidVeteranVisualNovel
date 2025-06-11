package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.StoryDatabase;

import java.util.ArrayList;

public class SaveDatabase {
    private static SaveDatabase instance;

    public SaveDatabase() {
        // load save database here
    }

    public static SaveDatabase getInstance() {
        if (instance == null) {
            instance = new SaveDatabase();
        }
        return instance;
    }

    public ArrayList<SaveData> getAllSavesOfStory(String storyId) {
        return new ArrayList<>();
    }
}
