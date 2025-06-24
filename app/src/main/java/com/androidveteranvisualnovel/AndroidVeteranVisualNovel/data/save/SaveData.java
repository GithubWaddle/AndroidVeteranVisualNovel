package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save;

import java.util.Map;

public class SaveData {
    public String storyId;
    public String sceneId;
    public Integer lineNumber;
    public Map<String, Object> storyState;

    public SaveData() {
    }

    public SaveData(String storyId, String sceneId, Integer lineNumber, Map<String, Object> storyState) {
        this.storyId = storyId;
        this.sceneId = sceneId;
        this.lineNumber = lineNumber;
        this.storyState = storyState;
    }
}
