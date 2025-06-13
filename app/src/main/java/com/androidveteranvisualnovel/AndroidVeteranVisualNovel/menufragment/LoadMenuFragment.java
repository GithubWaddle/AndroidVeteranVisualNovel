package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.R;

public class LoadMenuFragment extends MenuFragment {
    public static LoadMenuFragment newInstance() {
        
        Bundle args = new Bundle();
        
        LoadMenuFragment fragment = new LoadMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                menuFragmentManager.removeTopMenuFragment();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_load_menu, container, false);

        view.findViewById(R.id.button_back).setOnClickListener(v ->
                menuFragmentManager.removeTopMenuFragment()
        );

        return view;
    }
}
