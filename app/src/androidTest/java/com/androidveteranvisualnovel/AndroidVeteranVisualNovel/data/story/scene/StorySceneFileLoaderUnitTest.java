package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene;

import static android.util.Log.DEBUG;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.StoryScene;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.StorySceneFileLoader;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class StorySceneFileLoaderUnitTest {
    final String TAG = "StorySceneFileLoaderUnitTest";
    @Test
    public void test() throws IOException {
        final String SCENE_FILE_ASSET_PATH = "storyDatabase/template_story/scenes/starting_scene.storyscene";
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        Log.println(
                DEBUG,
                TAG,
                "StorySceneFileLoaderUnitTest: Loading scene from path `" + SCENE_FILE_ASSET_PATH + "`!"
        );
        StoryScene scene = StorySceneFileLoader.load(SCENE_FILE_ASSET_PATH, appContext);
        Log.println(
                DEBUG,
                TAG,
                "StorySceneFileLoaderUnitTest: Done!"
        );

    }
}
