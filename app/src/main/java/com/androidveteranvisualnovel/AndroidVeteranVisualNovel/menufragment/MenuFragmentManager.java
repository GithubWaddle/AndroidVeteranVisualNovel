package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MenuFragmentManager {
    private final FragmentManager fragmentManager;
    private final int containerId;

    public MenuFragmentManager(FragmentManager fragmentManager, int containerId) {
        this.fragmentManager = fragmentManager;
        this.containerId = containerId;
    }

    public void switchToMenuFragment(MenuFragment menuFragment) {
        menuFragment.setMenuFragmentManager(this);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId, menuFragment);
        transaction.commit();
    }

    public void overlayToMenuFragment(MenuFragment menuFragment) {
        menuFragment.setMenuFragmentManager(this);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId, menuFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void removeTopMenuFragment() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        }
    }
}
