package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class SetTransitionTransparencyInstant extends StorySceneEvent {
    private float transparency;

    public SetTransitionTransparencyInstant(float transparency) {
        this.transparency = transparency;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, Runnable finished) {
        super.execute(visualNovel, finished);

        visualNovel.setTransitionTransparencyInstant(
                transparency
        );
        finished.run();
    }
}
