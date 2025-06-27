package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play;

import android.widget.TextView;

public class SpeakerNameBox {
    public TextView speakerNameText;
    
    public SpeakerNameBox(TextView speakerNameText) {
        this.speakerNameText = speakerNameText;
    }
    
    public void setName(String name) {
        speakerNameText.setText(name);
    }

    public void setTransparencyInstant(float toTransparency) {

    }
}
