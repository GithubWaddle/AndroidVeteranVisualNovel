package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.StoryData;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.StoryDataFolderLoader;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class StoryDataUnitTest {

    @Test
    public void testLoadMetadataSuccessfully() throws JSONException, IOException {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        StoryData story = StoryDataFolderLoader.load("storyDatabase/template_story", context);

        assertNotNull("Story ID should not be null", story.id);
        assertEquals("TheTemplateStory", story.id);
        assertEquals("The Template Story", story.title);
        assertEquals("The School Reports", story.author);
        assertEquals("A template for story creation.", story.synopsisTagline);
        assertEquals("", story.storyFolderRelativePathToArtCover);
        assertEquals("", story.storyFolderRelativePathToStartMenuBackgroundMusic);
        assertEquals("starting_scene", story.startSceneId);
    }
}
