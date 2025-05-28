package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class SetDialogBoxTransparencyTween extends StorySceneEvent {
    private float transparency;
    private int milliseconds;

    public SetDialogBoxTransparencyTween(float transparency, int milliseconds) {
        this.transparency = transparency;
        this.milliseconds = milliseconds;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, Runnable finished) {
        super.execute(visualNovel, finished);

        visualNovel.setDialogBoxTransparencyTween(
                transparency,
                milliseconds,
                finished
        );
    }
}
