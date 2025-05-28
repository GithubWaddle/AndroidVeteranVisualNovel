package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class PlaySoundEffect extends StorySceneEvent {
    private String path;

    public PlaySoundEffect(String path) {
        this.path = path;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, Runnable finished) {
        super.execute(visualNovel, finished);

        visualNovel.playSoundEffect(path);
        finished.run();
    }
}
