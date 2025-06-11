package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor;

import static android.util.Log.DEBUG;

import android.content.Context;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.StoryScene;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.StorySceneFileLoader;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class StoryActorFileLoaderUnitTest {
    final String TAG = "StoryActorFileLoaderUnitTest";

    @Test
    public void test() throws JSONException, IOException {
        final String METADATA_FILE_ASSET_PATH = "storyDatabase/template_story/actors/dude/metadata.json";
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Log.println(
                DEBUG,
                TAG,
                "StoryActorFileLoaderUnitTest: Loading actor from path `" + METADATA_FILE_ASSET_PATH + "`!"
        );
        StoryActor actor = StoryActorFileLoader.load(METADATA_FILE_ASSET_PATH, appContext);
        Log.println(
                DEBUG,
                TAG,
                "StoryActorFileLoaderUnitTest: Done!"
        );
    }
}
