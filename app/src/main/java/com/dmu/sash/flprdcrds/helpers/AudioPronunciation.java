package com.dmu.sash.flprdcrds.helpers;

import android.media.AudioAttributes;
import android.media.MediaPlayer;

import java.io.IOException;

public class AudioPronunciation {

    private static AudioPronunciation instance;
    private MediaPlayer mediaPlayer;

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

    public void play(String urlAudio) {
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(urlAudio);
            mediaPlayer.prepare();
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
