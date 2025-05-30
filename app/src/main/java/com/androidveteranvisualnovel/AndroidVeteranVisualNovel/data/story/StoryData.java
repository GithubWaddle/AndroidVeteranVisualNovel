package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.StoryScene;

public class StoryData {
    public String id;
    public String title;
    public String author;
    public String synopsisTagline;
    public String storyFolderRelativePathToArtCover;
    public String storyFolderRelativePathToStartMenuBackgroundMusic;
    public String startSceneId;
    public String storyDataFolderName;

    public StoryScene getSceneById(String id) {
        return new StoryScene();
    }

}
