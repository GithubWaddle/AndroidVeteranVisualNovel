package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class SetVariable extends StorySceneEvent {
    private String name;
    private Object value;

    public SetVariable(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, Runnable finished) {
        super.execute(visualNovel, finished);

        visualNovel.setVariable(name, value);
        finished.run();
    }
}
