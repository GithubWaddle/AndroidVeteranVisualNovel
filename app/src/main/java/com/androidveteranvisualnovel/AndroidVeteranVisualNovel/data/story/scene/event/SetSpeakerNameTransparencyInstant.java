package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class SetSpeakerNameTransparencyInstant extends StorySceneEvent {
    private float transparency;

    public SetSpeakerNameTransparencyInstant(float transparency) {
        this.transparency = transparency;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, Runnable finished) {
        super.execute(visualNovel, finished);

        visualNovel.setSpeakerNameTransparencyInstant(transparency);
        finished.run();
    }
}
