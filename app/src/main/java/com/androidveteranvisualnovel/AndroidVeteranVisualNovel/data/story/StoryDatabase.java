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

    private Context context;
    private ArrayList<StoryData> allStories = new ArrayList<>();
    private Map<String, StoryData> storyDataMap = new HashMap<>();
    private boolean isLoaded = false;

    private StoryDatabase(Context context) {
        this.context = context.getApplicationContext();
    }

    public static StoryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new StoryDatabase(context);
        }
        return instance;
    }
    
    // Load semua cerita dari folder assets/storyDatabase
    private void loadStoriesIfNeeded() {
        if (isLoaded) return;

        try {
            AssetManager assetManager = context.getAssets();
            String[] folders = assetManager.list(STORY_DATABASE_ASSET_PATH);

            if (folders != null) {
                for (String folderName : folders) {
                    StoryData data = new StoryData(context, folderName);
                    allStories.add(data);
                    storyDataMap.put(data.id, data);
                }
            }

            isLoaded = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Return semua story yang ada
    public ArrayList<StoryData> getAllStories() {
        loadStoriesIfNeeded();
        return allStories;
    }

    // Return story tertentu berdasarkan ID
    public StoryData getStoryById(String id) {
        loadStoriesIfNeeded();
        return storyDataMap.get(id);
    }
}
