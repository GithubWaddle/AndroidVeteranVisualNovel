package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.LoadedStoryActors;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.StoryScenePlayer;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class SetActorSpriteExpressionTween extends StorySceneEvent {
    public String actorId;
    public String expression;
    public int milliseconds;

    public SetActorSpriteExpressionTween(String actorId, String expression, int milliseconds) {
        this.actorId = actorId;
        this.expression = expression;
        this.milliseconds = milliseconds;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, StoryScenePlayer storyScenePlayer, Runnable finished) {
        super.execute(visualNovel, storyScenePlayer, finished);

        visualNovel.setActorSpriteExpressionTween(
                LoadedStoryActors.getInstance().getActorById(actorId),
                expression,
                milliseconds,
                finished
        );
    }
}
