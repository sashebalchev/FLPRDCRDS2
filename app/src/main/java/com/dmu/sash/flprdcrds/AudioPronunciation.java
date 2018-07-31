package com.dmu.sash.flprdcrds;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

import io.realm.Realm;

public class AudioPronunciation {

    private Realm realm;
    private MediaPlayer mediaPlayer;

    AudioPronunciation() {
        realm = Realm.getDefaultInstance();
        mediaPlayer = new MediaPlayer();
    }

    public void playPronunciation(String wordID) {
        realm.executeTransactionAsync((realm) -> {
            Word word = realm.where(Word.class).equalTo("id", wordID).findFirst();
            String pronunciationURL = null;
            if (word != null) {
                pronunciationURL = word.getAudioPronounciation();
                play(pronunciationURL);
            }
        });
    }

    private void play(String urlAudio) {
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build());
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
