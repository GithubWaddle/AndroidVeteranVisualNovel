package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save;

import androidx.annotation.Nullable;

import java.util.Map;
import java.util.Objects;

public class SaveData {
    public String storyId;
    public String sceneId;
    public Integer lineNumber;
    public Map<String, Object> storyState;

    public SaveData() {}

    public SaveData(String storyId, String sceneId, Integer lineNumber, Map<String, Object> storyState) {
        this.storyId = storyId;
        this.sceneId = sceneId;
        this.lineNumber = lineNumber;
        this.storyState = storyState;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        SaveData otherSaveData = (SaveData) object;

        if (!Objects.equals(storyId, otherSaveData.storyId))
            return false;
        if (!Objects.equals(sceneId, otherSaveData.sceneId))
            return false;
        if (!Objects.equals(lineNumber, otherSaveData.lineNumber))
            return false;
        if (!Objects.equals(storyState, otherSaveData.storyState))
            return false;

        return true;
    }
}

