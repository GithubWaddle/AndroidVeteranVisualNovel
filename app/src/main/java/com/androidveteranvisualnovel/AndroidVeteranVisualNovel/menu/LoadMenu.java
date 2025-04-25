package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.R;

public class LoadMenu extends Menu {
    final boolean[] isDragging = {false};
    final float[] lastY = {0f};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_load_menu, container, false);

        // binding buttons
        ImageButton ibBack = view.findViewById(R.id.ibBack);

        ibBack.setOnClickListener(v -> {
            requireActivity().getOnBackPressedDispatcher().onBackPressed();
        });

        // binding scrolling and scrollbar
        ScrollView svContent = view.findViewById(R.id.svContent);
        View vScrollbarTrack = view.findViewById(R.id.vScrollbarTrack);
        View vScrollbarThumb = view.findViewById(R.id.vScrollbarThumb);

        vScrollbarThumb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int trackHeight = vScrollbarTrack.getHeight();
                int thumbHeight = vScrollbarThumb.getHeight();
                int contentHeight = svContent.getChildAt(0).getHeight();
                int scrollViewHeight = svContent.getHeight();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isDragging[0] = true;
                        lastY[0] = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        if (isDragging[0]) {
                            float dy = event.getRawY() - lastY[0];
                            lastY[0] = event.getRawY();

                            float newY = vScrollbarThumb.getTranslationY() + dy;
                            newY = Math.max(0f, Math.min(newY, trackHeight - thumbHeight));
                            vScrollbarThumb.setTranslationY(newY);

                            float proportion = newY / (float)(trackHeight - thumbHeight);
                            int scrollToY = (int) (proportion * (contentHeight - scrollViewHeight));
                            svContent.scrollTo(0, scrollToY);
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        isDragging[0] = false;
                        return true;
                }
                return false;
            }
        });

        svContent.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (isDragging[0]) {
                    return;
                }

                int scrollY = svContent.getScrollY();
                int trackHeight = vScrollbarTrack.getHeight();
                int thumbHeight = vScrollbarThumb.getHeight();
                int contentHeight = svContent.getChildAt(0).getHeight();
                int scrollViewHeight = svContent.getHeight();

                float proportion = scrollY / (float)(contentHeight - scrollViewHeight);
                float thumbY = proportion * (trackHeight - thumbHeight);
                vScrollbarThumb.setTranslationY(thumbY);
            }
        });

        return view;
    }
}
