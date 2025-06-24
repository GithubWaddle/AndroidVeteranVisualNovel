package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.StoryScene;
import android.content.Context;
import org.json.JSONObject;
import java.io.InputStream;

public class StoryData {
    public String id;
    public String title;
    public String author;
    public String synopsisTagline;
    public String storyFolderRelativePathToArtCover;
    public String storyFolderRelativePathToStartMenuBackgroundMusic;
    public String startSceneId;
    public String storyDataFolderName;

    private Context context;

    //Inisialisasi dan Load Metadata
    public StoryData(Context context, String folderName) {
        this.context = context;
        this.storyDataFolderName = folderName;
        loadMetadata();
    }
    
    private void loadMetadata() {
        try {
            String metadataPath = "storyDatabase/" + storyDataFolderName + "/metadata.json";
            AssetManager assetManager = context.getAssets();
            InputStream is = assetManager.open(metadataPath);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();

            String jsonString = new String(buffer, "UTF-8");
            JSONObject json = new JSONObject(jsonString);

            this.id = json.getString("storyId");
            this.title = json.getString("title");
            this.author = json.getString("author");
            this.synopsisTagline = json.getString("synopsisTagline");
            this.storyFolderRelativePathToArtCover = json.getString("storyFolderRelativePathToArtCover");
            this.storyFolderRelativePathToStartMenuBackgroundMusic = json.getString("storyFolderRelativePathToStartMenuBackgroundMusic");
            this.startSceneId = json.getString("startSceneId");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public StoryScene getSceneById(String id) {
        try {
            String scenePath = "storyDatabase/" + storyDataFolderName + "/scenes/" + id + ".json";
            AssetManager assetManager = context.getAssets();
            InputStream is = assetManager.open(scenePath);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();

            String jsonString = new String(buffer, "UTF-8");
            return new StoryScene(jsonString); // Anggap StoryScene punya constructor dari JSON
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
