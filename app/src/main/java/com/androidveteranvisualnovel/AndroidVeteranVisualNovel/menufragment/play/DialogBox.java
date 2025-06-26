package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play;

import android.os.Looper;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class DialogBox {
    private View dialogPanel = null;
    private TextView dialogText = null;

    public DialogBox(View dialogPanel, TextView dialogText) {
        this.dialogPanel = dialogPanel;
        this.dialogText = dialogText;
    }

    public void setText(String text) {
        dialogText.setText(text);
    }

    public void typeText(final String text, final float charactersPerSecond, final Runnable finished) {
        final Handler handler = new Handler(Looper.getMainLooper());
        final int totalLength = text.length();
        final long delayMilliseconds = (long) (1000 / charactersPerSecond);

        dialogText.setText(""); // Clear current text

        final Runnable[] characterAdder = new Runnable[1];
        characterAdder[0] = new Runnable() {
            int index = 0;

            @Override
            public void run() {
                if (index <= totalLength) {
                    dialogText.setText(text.substring(0, index));
                    index++;
                    handler.postDelayed(this, delayMilliseconds);
                } else {
                    if (finished != null) {
                        finished.run();
                    }
                }
            }
        };

        handler.post(characterAdder[0]);
    }
}
