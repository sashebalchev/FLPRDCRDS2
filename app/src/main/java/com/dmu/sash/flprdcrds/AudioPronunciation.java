package com.dmu.sash.flprdcrds;

import android.app.Application;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.PowerManager;

import java.io.IOException;

public class AudioPronunciation extends Application {

    private MediaPlayer mediaPlayer;
    private static AudioPronunciation instance;

    private AudioPronunciation() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build());
    }

    public static AudioPronunciation getInstance() {
        if (instance == null) {
            instance = new AudioPronunciation();
        }
        return instance;
    }

    public void play(String urlAudio, Context context) {
        mediaPlayer.reset();

        try {
            mediaPlayer.setDataSource(urlAudio);
            mediaPlayer.prepare();
            mediaPlayer.setWakeMode(context, PowerManager.PARTIAL_WAKE_LOCK);
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
