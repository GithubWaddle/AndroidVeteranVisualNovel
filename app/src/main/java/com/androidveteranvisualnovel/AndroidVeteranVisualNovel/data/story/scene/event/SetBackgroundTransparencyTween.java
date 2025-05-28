package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class SetBackgroundTransparencyTween extends StorySceneEvent {
    private float transparency;
    private int milliseconds;

    public SetBackgroundTransparencyTween(float transparency, int milliseconds) {
        this.transparency = transparency;
        this.milliseconds = milliseconds;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, Runnable finished) {
        super.execute(visualNovel, finished);

        visualNovel.setBackgroundTransparencyTween(
                transparency,
                milliseconds,
                finished
        );
    }
}
