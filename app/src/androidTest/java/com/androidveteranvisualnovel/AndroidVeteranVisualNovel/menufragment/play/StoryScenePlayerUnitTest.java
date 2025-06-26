package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play;

import static org.awaitility.Awaitility.await;

import android.content.Context;
import android.os.Looper;

import androidx.annotation.UiThread;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save.SaveData;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save.SaveDatabase;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.StoryDatabase;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.LoadedStoryActors;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.StoryScene;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.StorySceneFileLoader;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RunWith(AndroidJUnit4.class)
public class StoryScenePlayerUnitTest {
    @Test
    public void test() throws IOException, JSONException {
        final String SCENE_FILE_ASSET_PATH = "storyDatabase/template_story/scenes/starting_scene.storyscene";
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        LoadedStoryActors.getInstance(appContext);
        StoryDatabase.getInstance(appContext);

        StoryScene scene = StorySceneFileLoader.load(SCENE_FILE_ASSET_PATH, appContext);

        CommandLineVisualNovel commandLineVisualNovel = new CommandLineVisualNovel();
        StoryScenePlayer storyScenePlayer = new StoryScenePlayer(commandLineVisualNovel);
        SaveData saveData = new SaveData();
        saveData.storyId = "TheTemplateStory";
        saveData.sceneId = "starting_scene";
        saveData.lineNumber = 0;
        saveData.storyState = new HashMap<>();

        storyScenePlayer.load(saveData);
        storyScenePlayer.play();

        await().atMost(20, TimeUnit.SECONDS).until(() -> storyScenePlayer.currentStatus.equals(StoryScenePlayer.Status.STOP));
    }
}
