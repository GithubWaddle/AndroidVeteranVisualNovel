package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class MenuFragment extends Fragment {
    protected MenuFragmentManager menuFragmentManager;

    public void setMenuFragmentManager(MenuFragmentManager manager) {
        this.menuFragmentManager = manager;
    }
    public static MenuFragment newInstance() {

        Bundle args = new Bundle();

        MenuFragment fragment = new MenuFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
