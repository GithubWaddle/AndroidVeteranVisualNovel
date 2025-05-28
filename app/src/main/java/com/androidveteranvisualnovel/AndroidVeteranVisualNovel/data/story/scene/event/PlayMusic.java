package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class PlayMusic extends StorySceneEvent {
    private String path;

    public PlayMusic(String path) {
        this.path = path;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, Runnable finished) {
        super.execute(visualNovel, finished);

        visualNovel.playMusic(path);
        finished.run();
    }
}
