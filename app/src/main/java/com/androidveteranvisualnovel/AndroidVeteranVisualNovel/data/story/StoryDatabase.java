package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.LoadedStoryActors;
import android.content.Context;
import android.content.res.AssetManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StoryDatabase {
    private static final String STORY_DATABASE_ASSET_PATH = "storyDatabase";
    private static StoryDatabase instance;

    private final Context applicationContext;
    private final Map<String, StoryData> allStories = new HashMap<>();
    private boolean isLoaded = false;

    private StoryDatabase(Context context) {
        this.applicationContext = context.getApplicationContext();
    }

    public static synchronized StoryDatabase getInstance() {
        return getInstance(null);
    }


    public static synchronized StoryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new StoryDatabase(context);
        }
        return instance;
    }

    private void loadStoriesIfNeeded() {
        if (isLoaded) return;

        try {
            AssetManager assetManager = applicationContext.getAssets();
            String[] storyFolders = assetManager.list(STORY_DATABASE_ASSET_PATH);

            if (storyFolders != null) {
                for (String folderName : storyFolders) {
                    String fullAssetPath = STORY_DATABASE_ASSET_PATH + "/" + folderName;

                    StoryData storyData = StoryDataFolderLoader.load(fullAssetPath, applicationContext);
                    if (storyData != null && storyData.id != null) {
                        allStories.put(storyData.id, storyData);
                    }
                }
            }

            isLoaded = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, StoryData> getAllStories() {
        loadStoriesIfNeeded();
        return allStories;
    }

    public StoryData getStoryById(String id) {
        loadStoriesIfNeeded();
        return allStories.get(id);
    }
}

