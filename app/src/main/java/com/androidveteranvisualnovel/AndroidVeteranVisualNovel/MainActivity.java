package com.androidveteranvisualnovel.AndroidVeteranVisualNovel;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.MenuFragmentManager;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.StartMenuFragment;

public class MainActivity extends AppCompatActivity {
    private MenuFragmentManager menuFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // layout initialization here
        menuFragmentManager = new MenuFragmentManager();
        menuFragmentManager.switchToMenuFragment(StartMenuFragment.newInstance());
    }
}
