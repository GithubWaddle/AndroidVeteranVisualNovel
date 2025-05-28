package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.LoadedStoryActors;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class SetActorSpriteTransparencyTween extends StorySceneEvent {
    private String actorId;
    private float transparency;
    private int milliseconds;

    public SetActorSpriteTransparencyTween(String actorId, float transparency, int milliseconds) {
        this.actorId = actorId;
        this.transparency = transparency;
        this.milliseconds = milliseconds;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, Runnable finished) {
        super.execute(visualNovel, finished);

        visualNovel.setActorSpriteTransparencyTween(
                LoadedStoryActors.getInstance().get_actor_by_id(actorId),
                transparency,
                milliseconds,
                finished
        );
    }
}
