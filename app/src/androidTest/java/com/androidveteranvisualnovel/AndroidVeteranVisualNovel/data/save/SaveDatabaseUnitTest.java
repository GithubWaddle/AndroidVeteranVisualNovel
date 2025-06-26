package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;

@RunWith(AndroidJUnit4.class)
public class SaveDatabaseUnitTest {
    @Test
    public void testCRUDOperations() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        final int SAVE_ID = 0;
        SaveData mockSaveData = new SaveData(
                "TheTemplateStory",
                "starting_scene",
                0,
                new HashMap<>()
        );

        SaveDatabase saveDatabase = SaveDatabase.getInstance(context);
        saveDatabase.createSave(
                "TheTemplateStory",
                0,
                mockSaveData
        );

        SaveData mockSaveDataFromDatabase = saveDatabase.getSave(
                mockSaveData.storyId,
                SAVE_ID
        );

        assertEquals(
                "Mock SaveData and READ SaveData should not be different.",
                mockSaveData,
                mockSaveDataFromDatabase
        );

        mockSaveData.storyState.put("myVariable", 1);
        saveDatabase.updateSave(
                mockSaveData.storyId,
                SAVE_ID,
                mockSaveData
        );
        mockSaveDataFromDatabase = saveDatabase.getSave(
                mockSaveData.storyId,
                SAVE_ID
        );

        assertEquals(
                "Updated Mock SaveData and UPDATED READ SaveData should not be different.",
                mockSaveData,
                mockSaveDataFromDatabase
        );

        saveDatabase.deleteSave(
                mockSaveData.storyId,
                SAVE_ID
        );
        mockSaveDataFromDatabase = saveDatabase.getSave(
                mockSaveData.storyId,
                SAVE_ID
        );

        assertNull("DELETED SaveData should not exist", mockSaveDataFromDatabase);
    }

    @Test
    public void testGetAllSavesFromStory() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        final String STORY_ID = "TheTemplateStory";
        SaveData mockSaveData = new SaveData(
                STORY_ID,
                "starting_scene",
                0,
                new HashMap<>()
        );
        SaveDatabase saveDatabase = SaveDatabase.getInstance(context);
        saveDatabase.createSave(
                "TheTemplateStory",
                0,
                mockSaveData
        );

        ArrayList<SaveData> saves = saveDatabase.getAllSavesOfStory(STORY_ID);
        assertNotNull("Save list recieved should not be null", saves);

    }
}
