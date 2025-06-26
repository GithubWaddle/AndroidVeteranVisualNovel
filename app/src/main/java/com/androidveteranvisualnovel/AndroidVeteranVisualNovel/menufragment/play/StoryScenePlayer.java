package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play;

import android.util.Log;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save.SaveData;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.StoryData;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.StoryDatabase;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.LoadedStoryActors;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.StoryScene;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.StorySceneEvent;

import org.json.JSONException;

import java.io.IOException;

public class StoryScenePlayer {
    final String TAG = "StoryScenePlayer";
    enum Status {
        PLAYING,
        STOP
    }
    private SaveData saveData = new SaveData();
    private StoryData storyData;
    public StoryScene storyScene; // temporarily made public to unit test StoryScenePlayer (because StoryDatabase is not implemented yet)
    private VisualNovelInterface visualNovelInterface;
    public Status currentStatus = Status.STOP;

    private int currentEventIndex = 0;

    public StoryScenePlayer(VisualNovelInterface visualNovelInterface) {
        this.visualNovelInterface = visualNovelInterface;
    }

    public void load(SaveData toSaveData) throws IOException, JSONException {
        this.saveData = toSaveData;
        this.storyData = StoryDatabase.getInstance().getStoryById(toSaveData.storyId);
        this.storyScene = this.storyData.getSceneById(toSaveData.sceneId);
        LoadedStoryActors.getInstance().loadFolder(this.storyData.getActorsFolderAssetPath());
    }

    public void play() {
        currentStatus = Status.PLAYING;
        
        StoryScenePlayer storyScenePlayer = this;
        Runnable nextEvent = new Runnable() {
            @Override
            public void run() {
                if (currentEventIndex >= storyScenePlayer.storyScene.events.size()) {
                    Log.println(
                            Log.INFO,
                            TAG,
                            "No more events to play. Scene finished!"
                    );
                    stop();
                    return;
                }
                if (currentStatus == Status.STOP) {
                    return;
                }

                StorySceneEvent event = storyScenePlayer.storyScene.events.get(currentEventIndex);
                storyScenePlayer.currentEventIndex++;
                event.execute(
                        storyScenePlayer.visualNovelInterface,
                        this
                );
            };
        };

        nextEvent.run();
    }

    public void stop() {
        currentStatus = Status.STOP;
    }
}
