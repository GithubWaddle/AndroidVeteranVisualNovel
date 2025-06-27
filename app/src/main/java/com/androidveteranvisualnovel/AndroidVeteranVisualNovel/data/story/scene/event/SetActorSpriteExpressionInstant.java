package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.LoadedStoryActors;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.StoryScenePlayer;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class SetActorSpriteExpressionInstant extends StorySceneEvent {
    public String actorId;
    public String expression;

    public SetActorSpriteExpressionInstant(String actorId, String expression) {
        this.actorId = actorId;
        this.expression = expression;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, StoryScenePlayer storyScenePlayer, Runnable finished) {
        super.execute(visualNovel, storyScenePlayer, finished);

        visualNovel.setActorSpriteExpressionInstant(
                LoadedStoryActors.getInstance().getActorById(actorId),
                expression
        );
        finished.run();
    }
}
