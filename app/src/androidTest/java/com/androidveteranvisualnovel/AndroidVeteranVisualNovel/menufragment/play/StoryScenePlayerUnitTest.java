package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save.SaveData;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.StoryScene;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.StorySceneFileLoader;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class StoryScenePlayerUnitTest {
    @Test
    public void test() throws IOException {
        final String SCENE_FILE_ASSET_PATH = "storyDatabase/template_story/scenes/starting_scene.storyscene";
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        StoryScene scene = StorySceneFileLoader.load(SCENE_FILE_ASSET_PATH, appContext);

        CommandLineVisualNovel commandLineVisualNovel = new CommandLineVisualNovel();
        StoryScenePlayer storyScenePlayer = new StoryScenePlayer(commandLineVisualNovel);
        storyScenePlayer.storyScene = scene;
        storyScenePlayer.play();
    }
}
