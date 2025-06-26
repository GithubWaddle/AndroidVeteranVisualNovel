package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor;

import static android.util.Log.DEBUG;

import android.content.Context;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class LoadedStoryActorsUnitTest {
    final String TAG = "LoadedStoryActorsUnitTest";

    @Test
    public void test() throws JSONException, IOException {
        final String ACTOR_FOLDER_ASSET_PATH = "storyDatabase/template_story/actors";
        final String ACTOR_ID = "unique_dude";
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        LoadedStoryActors loadedStoryActors = LoadedStoryActors.getInstance(appContext);

        Log.println(
                DEBUG,
                TAG,
                "LoadedStoryActorsUnitTest: Loading actor folder from path `" + ACTOR_FOLDER_ASSET_PATH + "`!"
        );
        loadedStoryActors.loadFolder(ACTOR_FOLDER_ASSET_PATH);

        Log.println(
                DEBUG,
                TAG,
                "LoadedStoryActorsUnitTest: Getting actor with id `" + ACTOR_ID + "`!"
        );
        StoryActor actor = loadedStoryActors.getActorById(ACTOR_ID);

        Log.println(
                DEBUG,
                TAG,
                "LoadedStoryActorsUnitTest: Done!"
        );
    }
}
