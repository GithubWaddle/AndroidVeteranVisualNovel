package com.androidveteranvisualnovel.AndroidVeteranVisualNovel;

import androidx.fragment.app.Fragment;

public interface OnFragmentInteractionListener {
    void replaceCurrentFragment(Fragment fragment);
    void overlayFragment(Fragment fragment);
    void removeFragment(Fragment fragment);
}
