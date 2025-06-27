package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.StoryScenePlayer;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class SetDialogBoxTransparencyTween extends StorySceneEvent {
    public float transparency;
    public int milliseconds;

    public SetDialogBoxTransparencyTween(float transparency, int milliseconds) {
        this.transparency = transparency;
        this.milliseconds = milliseconds;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, StoryScenePlayer storyScenePlayer, Runnable finished) {
        super.execute(visualNovel, storyScenePlayer, finished);

        visualNovel.setDialogBoxTransparencyTween(
                transparency,
                milliseconds,
                finished
        );
    }
}
