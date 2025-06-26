package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.StoryScene;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.StorySceneFileLoader;

import android.content.Context;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class StoryData {
    private static final String SCENES_FOLDER_NAME = "scenes";

    public String id;
    public String title;
    public String author;
    public String synopsisTagline;
    public String storyFolderRelativePathToArtCover;
    public String storyFolderRelativePathToStartMenuBackgroundMusic;
    public String startSceneId;
    public String storyDataAssetPath;
    public int storyVersion;
    public String apiVersion;

    private final Context context;

    public StoryData(Context context) {
        this.context = context.getApplicationContext();
    }

    public StoryScene getSceneById(String sceneId) throws IOException {
        String scenesFolderPath = storyDataAssetPath + "/" + SCENES_FOLDER_NAME;
        String[] sceneFiles = context.getAssets().list(scenesFolderPath);

        if (sceneFiles == null || sceneFiles.length == 0) {
            return null;
        }

        for (String sceneFile : sceneFiles) {
            if (sceneFile.equals(sceneId + ".storyscene")) {
                return StorySceneFileLoader.load(
                        scenesFolderPath + "/" + sceneFile,
                        context
                );
            }
        }

        return null;
    }

    public String getActorsFolderAssetPath() {
        return storyDataAssetPath + "/actors";
    }
}
