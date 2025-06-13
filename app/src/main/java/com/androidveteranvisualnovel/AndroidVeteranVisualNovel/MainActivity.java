package com.androidveteranvisualnovel.AndroidVeteranVisualNovel;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.MenuFragmentManager;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.StartMenuFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // layout initialization here
        MenuFragmentManager menuFragmentManager = new MenuFragmentManager(getSupportFragmentManager(), R.id.frame_container);
        menuFragmentManager.switchToMenuFragment(StartMenuFragment.newInstance());
    }
}
