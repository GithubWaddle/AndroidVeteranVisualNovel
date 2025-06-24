package com.androidveteranvisualnovel.AndroidVeteranVisualNovel;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.StoryData;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.StoryDatabase;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class StoryDatabaseUnitTest {

    @Test
    public void testGetAllStories() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        StoryDatabase db = StoryDatabase.getInstance(context);

        ArrayList<StoryData> stories = db.getAllStories();

        assertNotNull("Story list should not be null", stories);
        assertFalse("Story list should not be empty", stories.isEmpty());

        StoryData story = stories.get(0);
        assertNotNull("Story should not be null", story);
        assertNotNull("Story ID should not be null", story.id);
    }

    @Test
    public void testGetStoryById() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        StoryDatabase db = StoryDatabase.getInstance(context);

        StoryData story = db.getStoryById("a_very_quirky_story");

        assertNotNull("Story with ID should be found", story);
        assertEquals("A Very Quirky Story", story.title);
    }

    @Test
    public void testGetInvalidStoryById() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        StoryDatabase db = StoryDatabase.getInstance(context);

        StoryData story = db.getStoryById("invalid_id");

        assertNull("Invalid story ID should return null", story);
    }
}
