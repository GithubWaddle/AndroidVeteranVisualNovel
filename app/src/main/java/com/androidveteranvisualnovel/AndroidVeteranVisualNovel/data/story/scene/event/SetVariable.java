package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.StoryScenePlayer;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class SetVariable extends StorySceneEvent {
    public String name;
    public Object value;

    public SetVariable(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, StoryScenePlayer storyScenePlayer, Runnable finished) {
        super.execute(visualNovel, storyScenePlayer, finished);
        storyScenePlayer.saveData.storyState.put(
                name,
                value
        );
        finished.run();
    }
}
