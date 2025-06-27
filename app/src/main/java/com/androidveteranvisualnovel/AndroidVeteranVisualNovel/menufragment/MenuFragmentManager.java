package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

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
        transaction.setCustomAnimations(
                android.R.anim.fade_in,    // enter
                android.R.anim.fade_out,   // exit
                android.R.anim.fade_in,    // popEnter (when returning)
                android.R.anim.fade_out    // popExit
        );
        transaction.replace(containerId, menuFragment);
        transaction.commit();
    }

    public void overlayToMenuFragment(MenuFragment menuFragment) {
        menuFragment.setMenuFragmentManager(this);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(containerId, menuFragment); // 👈 add, don't replace
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void removeTopMenuFragment() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        }
    }

    public MenuFragment getMenuFragment(int index) {
        List<Fragment> fragments = fragmentManager.getFragments();
        int count = 0;

        for (Fragment fragment : fragments) {
            if (fragment instanceof MenuFragment) {
                if (count == index) {
                    return (MenuFragment) fragment;
                }
                count++;
            }
        }

        return null;
    }

}
