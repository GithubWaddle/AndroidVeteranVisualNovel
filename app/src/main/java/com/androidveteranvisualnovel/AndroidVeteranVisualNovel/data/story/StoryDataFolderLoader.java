package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StoryDataFolderLoader {

    public static StoryData load(String assetPath, Context context) throws IOException {
        StoryData storyData = new StoryData(context);
        JSONObject metadataJson;

        String metadataAssetPath = assetPath + "/metadata.json";

        try (InputStream inputStream = context.getAssets().open(metadataAssetPath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }

            metadataJson = new JSONObject(jsonBuilder.toString());

        } catch (JSONException e) {
            throw new RuntimeException("Failed to parse metadata at: " + assetPath, e);
        }

        storyData.id = metadataJson.optString("storyId", "unknown");
        storyData.storyVersion = metadataJson.optInt("storyVersion", 1);
        storyData.apiVersion = metadataJson.optString("apiVersion", "1.0");
        storyData.title = metadataJson.optString("title", "Untitled");
        storyData.author = metadataJson.optString("author", "Unknown");
        storyData.synopsisTagline = metadataJson.optString("synopsisTagline", "");
        storyData.storyFolderRelativePathToArtCover = metadataJson.optString("storyFolderRelativePathToArtCover", "");
        storyData.storyFolderRelativePathToStartMenuBackgroundMusic = metadataJson.optString("storyFolderRelativePathToStartMenuBackgroundMusic", "");
        storyData.storyDataAssetPath = assetPath;
        storyData.startSceneId = metadataJson.optString("startSceneId", "");

        return storyData;
    }
}
