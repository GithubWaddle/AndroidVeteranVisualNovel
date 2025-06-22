package com.androidveteranvisualnovel.AndroidVeteranVisualNovel;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.StoryData;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class StoryDataUnitTest {

    @Test
    public void testLoadMetadataSuccessfully() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        StoryData story = new StoryData(context, "template_story");

        assertNotNull("Story ID should not be null", story.id);
        assertEquals("a_very_quirky_story", story.id);
        assertEquals("A Very Quirky Story", story.title);
        assertEquals("Waddle", story.author);
        assertEquals("80% of the world is now quirky.", story.synopsisTagline);
        assertEquals("asset/image/art_cover.png", story.storyFolderRelativePathToArtCover);
        assertEquals("asset/sound/music/start_menu_background_music.ogg", story.storyFolderRelativePathToStartMenuBackgroundMusic);
        assertEquals("my_first_scene", story.startSceneId);
    }
}
