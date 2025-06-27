package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.StoryScenePlayer;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class SetBackgroundTransparencyTween extends StorySceneEvent {
    public float transparency;
    public int milliseconds;

    public SetBackgroundTransparencyTween(float transparency, int milliseconds) {
        this.transparency = transparency;
        this.milliseconds = milliseconds;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, StoryScenePlayer storyScenePlayer, Runnable finished) {
        super.execute(visualNovel, storyScenePlayer, finished);

        visualNovel.setBackgroundTransparencyTween(
                transparency,
                milliseconds,
                finished
        );
    }
}
