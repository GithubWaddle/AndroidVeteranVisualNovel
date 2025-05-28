package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.LoadedStoryActors;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class SetDialogBoxTransparencyInstant extends StorySceneEvent {
    private float transparency;

    public SetDialogBoxTransparencyInstant(float transparency) {
        this.transparency = transparency;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, Runnable finished) {
        super.execute(visualNovel, finished);

        visualNovel.setDialogBoxTransparencyInstant(
                transparency
        );
        finished.run();
    }
}
