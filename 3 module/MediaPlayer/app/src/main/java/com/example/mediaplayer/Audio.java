package com.example.mediaplayer;

import android.content.Context;
import android.media.MediaPlayer;

public class Audio {
    private final int AUDIO = R.raw.h;

    private MediaPlayer mPlayer;
    private boolean isPlaying;

    public Audio() {
        isPlaying = false;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void play(Context context) {
        stop();
        if (!isPlaying) {
            mPlayer = MediaPlayer.create(context, AUDIO);
            mPlayer.setOnCompletionListener(mp -> {
                stop();
                mPlayer.release();
                mPlayer = null;
            });
            mPlayer.start();
            isPlaying = true;
        } else {
            isPlaying = false;
        }
    }

    public void stop() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
}
