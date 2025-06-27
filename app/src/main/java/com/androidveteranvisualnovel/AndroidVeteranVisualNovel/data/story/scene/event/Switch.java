package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save.SaveData;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.StoryScenePlayer;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

import org.json.JSONException;

import java.io.IOException;

public class Switch extends StorySceneEvent {
    private String sceneId;

    public Switch(String sceneId) {
        this.sceneId = sceneId;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, StoryScenePlayer storyScenePlayer, Runnable finished) {
        super.execute(visualNovel, storyScenePlayer, finished);

        try {
            storyScenePlayer.switchScene(sceneId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        finished.run();
    }
}
