package com.androidveteranvisualnovel.AndroidVeteranVisualNovel;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save.SaveDatabase;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.StoryDatabase;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.LoadedStoryActors;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.MenuFragmentManager;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.StartMenuFragment;

public class MainActivity extends AppCompatActivity {
    public static final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadedStoryActors.getInstance(getApplicationContext());
        SaveDatabase.getInstance(getApplicationContext());
        StoryDatabase.getInstance(getApplicationContext());

        // layout initialization here
        MenuFragmentManager menuFragmentManager = new MenuFragmentManager(getSupportFragmentManager(), R.id.frame_container);
        menuFragmentManager.switchToMenuFragment(StartMenuFragment.newInstance());
    }
}
