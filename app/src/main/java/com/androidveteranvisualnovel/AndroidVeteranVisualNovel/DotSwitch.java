package com.androidveteranvisualnovel.AndroidVeteranVisualNovel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Checkable;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DotSwitch extends FrameLayout implements Checkable {
    boolean isChecked = false;

    public DotSwitch(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    private void initialize(@NonNull Context context) {
        LayoutInflater.from(context).inflate(R.layout.dot_switch, this, true);
        setOnClickListener(v -> {toggle();});
    }

    @Override
    public void setChecked(boolean checked) {
        if (this.isChecked == checked) {
            return;
        }

        this.isChecked = checked;
        refreshDrawableState();
    }

    @Override
    public boolean isChecked() {
        return this.isChecked;
    }

    public void toggle() {
        this.setChecked(!isChecked);
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, new int[]{android.R.attr.state_checked});
        }
        return drawableState;
    }
}
