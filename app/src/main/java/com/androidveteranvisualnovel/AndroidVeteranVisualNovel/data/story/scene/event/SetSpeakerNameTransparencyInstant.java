package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.StoryScenePlayer;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class SetSpeakerNameTransparencyInstant extends StorySceneEvent {
    public float transparency;

    public SetSpeakerNameTransparencyInstant(float transparency) {
        this.transparency = transparency;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, StoryScenePlayer storyScenePlayer, Runnable finished) {
        super.execute(visualNovel, storyScenePlayer, finished);

        visualNovel.setSpeakerNameTransparencyInstant(transparency);
        finished.run();
    }
}
