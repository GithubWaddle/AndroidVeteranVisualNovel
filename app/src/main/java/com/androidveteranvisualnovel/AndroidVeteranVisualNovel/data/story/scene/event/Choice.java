package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.StoryScenePlayer;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

import java.util.ArrayList;
import java.util.Map;

public class Choice extends StorySceneEvent {
    public Map<String, String> choiceToLabel;

    public Choice(Map<String, String> choiceToLabel) {
        this.choiceToLabel = choiceToLabel;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, StoryScenePlayer storyScenePlayer, Runnable finished) {
        super.execute(visualNovel, storyScenePlayer, finished);

        visualNovel.askChoice(
                new ArrayList<String>(choiceToLabel.keySet()),
                () -> {
                    Jump jump = new Jump(choiceToLabel.get(visualNovel.getPickedChoice()));
                    jump.execute(
                            visualNovel,
                            storyScenePlayer,
                            finished
                    );
                }
        );
    }
}
