package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.LoadedStoryActors;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.StoryScenePlayer;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class Talk extends StorySceneEvent {
    final float CHARACTERS_PER_SECOND = 18; // replace with configuration reference later
    public String actor_id = null;
    public String dialog = "";

    public Talk(String actor_id, String dialog) {
        this.actor_id = actor_id;
        this.dialog = dialog;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, StoryScenePlayer storyScenePlayer, Runnable finished) {
        super.execute(visualNovel, storyScenePlayer, finished);

        visualNovel.setSpeakerName(LoadedStoryActors.getInstance().getActorById(actor_id).name);
        visualNovel.typeDialogText(this.dialog, CHARACTERS_PER_SECOND, () -> visualNovel.waitForContinue(finished));
    }
}
