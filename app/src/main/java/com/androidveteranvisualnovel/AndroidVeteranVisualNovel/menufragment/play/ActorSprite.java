package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.StoryData;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.StoryActor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class ActorSprite {
    ImageView actorSprite;
    private StoryActor actor;
    private Context context;
    private StoryData storyData;

    public ActorSprite(Context context, ImageView actorSprite, StoryData storyData, StoryActor actor, String expression) {
        this.context = context;
        this.actorSprite = actorSprite;
        this.storyData = storyData;
        this.actor = actor;
        setExpression(expression);
    }

    public void setExpression(String expression) {
        String storyPath = actor.expressionPaths.get(expression);
        if (storyPath == null) {
            return;
        }

        try {
            InputStream inputStream = context.getAssets().open(
                    storyData.storyDataAssetPath + "/" + storyPath
            );
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            actorSprite.setImageDrawable(drawable);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

