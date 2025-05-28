package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.LoadedStoryActors;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class SetActorSpriteExpressionTween extends StorySceneEvent {
    private String actorId;
    private String expression;
    private int milliseconds;

    public SetActorSpriteExpressionTween(String actorId, String expression, int milliseconds) {
        this.actorId = actorId;
        this.expression = expression;
        this.milliseconds = milliseconds;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, Runnable finished) {
        super.execute(visualNovel, finished);

        visualNovel.setActorSpriteExpressionTween(
                LoadedStoryActors.getInstance().get_actor_by_id(actorId),
                expression,
                milliseconds,
                finished
        );
    }
}
