package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.StoryScenePlayer;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Wait extends StorySceneEvent {
    public int milliseconds;

    public Wait(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, StoryScenePlayer storyScenePlayer, Runnable finished) {
        super.execute(visualNovel, storyScenePlayer, finished);

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(finished, milliseconds, TimeUnit.MILLISECONDS);
    }
}
