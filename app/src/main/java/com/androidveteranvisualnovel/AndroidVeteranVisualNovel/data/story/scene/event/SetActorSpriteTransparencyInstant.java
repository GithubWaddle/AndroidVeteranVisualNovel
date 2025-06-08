package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.LoadedStoryActors;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class SetActorSpriteTransparencyInstant extends StorySceneEvent {
    private String actorId;
    private float transparency;

    public SetActorSpriteTransparencyInstant(String actorId, float transparency) {
        this.actorId = actorId;
        this.transparency = transparency;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, Runnable finished) {
        super.execute(visualNovel, finished);

        visualNovel.setActorSpriteTransparencyInstant(
                LoadedStoryActors.getInstance().getActorById(actorId),
                transparency
        );
        finished.run();
    }
}
