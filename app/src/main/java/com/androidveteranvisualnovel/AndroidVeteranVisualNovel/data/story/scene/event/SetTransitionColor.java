package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import android.graphics.Color;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class SetTransitionColor extends StorySceneEvent {
    private int color;

    public SetTransitionColor(int color) {
        this.color = color;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, Runnable finished) {
        super.execute(visualNovel, finished);

        visualNovel.setTransitionColor(color);
        finished.run();
    }
}
