package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play;

import android.widget.ImageView;

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

    public ActorSprite(Context context, ImageView actorSprite, StoryActor actor, String expression) {
        this.context = context;
        this.actorSprite = actorSprite;
        this.actor = actor;
        setExpression(expression);
    }

    public void setExpression(String expression) {
        String assetPath = actor.expressionPaths.get(expression);
        if (assetPath == null) {
            return;
        }

        Bitmap bitmap = loadBitmapFromAssets(assetPath);
        if (bitmap == null) {
            actorSprite.setImageResource(android.R.drawable.ic_delete); // Placeholder
            return;
        }

        actorSprite.setImageBitmap(bitmap);
    }

    private Bitmap loadBitmapFromAssets(String assetPath) {
        try (InputStream is = context.getAssets().open(assetPath)) {
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

