package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;


import android.util.Log;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.StoryScenePlayer;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class Jump extends StorySceneEvent {
    public String label;

    public Jump(String label) {
        this.label = label;
    }

    @Override
    public void execute(
            VisualNovelInterface visualNovel,
            StoryScenePlayer storyScenePlayer,
            Runnable finished
    ) {
        super.execute(visualNovel, storyScenePlayer, finished);
        int toLabelIndex = findLabelEventIndex(storyScenePlayer, label) + 1;
        if (toLabelIndex != -1) {
            storyScenePlayer.setCurrentEventIndex(toLabelIndex);
        }
        finished.run();
    }

    public static int findLabelEventIndex(StoryScenePlayer storyScenePlayer, String label) {
        for (int i = 0; i < storyScenePlayer.storyScene.events.size(); i++) {
            StorySceneEvent event = storyScenePlayer.storyScene.events.get(i);
            if (!(event instanceof Label)) {
                continue;
            }

            Label labelEvent = (Label) event;
            if (!labelEvent.label.equals(label)) {
                continue;
            }
            return i;
        }

        return -1;
    }
}
