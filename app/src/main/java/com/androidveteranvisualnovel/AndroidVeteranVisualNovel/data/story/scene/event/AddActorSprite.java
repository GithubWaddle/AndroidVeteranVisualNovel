package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.LoadedStoryActors;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.StoryScenePlayer;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class AddActorSprite extends StorySceneEvent {
    public String actorId;
    public String expression;
    public String position;

    public AddActorSprite(String actorId, String expression, String position) {
        this.actorId = actorId;
        this.expression = expression;
        this.position = position;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, StoryScenePlayer storyScenePlayer, Runnable finished) {
        super.execute(visualNovel, storyScenePlayer, finished);

        visualNovel.addActorSprite(
                LoadedStoryActors.getInstance().getActorById(actorId),
                expression,
                position
        );
        finished.run();
    }
}
