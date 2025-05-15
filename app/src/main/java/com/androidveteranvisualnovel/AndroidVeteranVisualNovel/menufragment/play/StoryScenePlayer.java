package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save.SaveData;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.StoryData;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.StoryScene;

public class StoryScenePlayer {
    enum Status {
        RUNNING,
        WAITING,
        STOP
    }
    private SaveData saveData;
    private StoryData storyData;
    private StoryScene storyScene;
    private Status currentStatus = Status.STOP;

    public StoryScenePlayer(VisualNovelInterface visualNovelInterface) {

    }

    public void load(SaveData toSaveData) {

    }

    public void play() {

    }

    public void stop() {

    }
}
