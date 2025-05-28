package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.LoadedStoryActors;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class SetActorSpritePositionTween extends StorySceneEvent {
    private String actorId;
    private String position;
    private int milliseconds;

    public SetActorSpritePositionTween(String actorId, String position, int milliseconds) {
        this.actorId = actorId;
        this.position = position;
        this.milliseconds = milliseconds;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, Runnable finished) {
        super.execute(visualNovel, finished);

        visualNovel.setActorSpritePositionTween(
                LoadedStoryActors.getInstance().get_actor_by_id(actorId),
                position,
                milliseconds,
                finished
        );
    }
}
