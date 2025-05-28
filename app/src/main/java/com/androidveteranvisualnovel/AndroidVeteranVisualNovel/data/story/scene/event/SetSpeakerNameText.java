package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class SetSpeakerNameText extends StorySceneEvent {
    private String name;

    public SetSpeakerNameText(String name) {
        this.name = name;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, Runnable finished) {
        super.execute(visualNovel, finished);

        visualNovel.setSpeakerName(name);
        finished.run();
    }
}
