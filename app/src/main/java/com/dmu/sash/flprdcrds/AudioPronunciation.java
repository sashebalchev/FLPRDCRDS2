package com.dmu.sash.flprdcrds;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.PowerManager;

import com.mapzen.speakerbox.Speakerbox;

import java.io.IOException;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AudioPronunciation extends Application{

    private Realm realm;
    private MediaPlayer mediaPlayer;
    private Context context;
    private static AudioPronunciation instance;

   private AudioPronunciation() {
//        this.context = context;
//        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
//                .name("flprcrds.realm")
//                .schemaVersion(0)
//                .deleteRealmIfMigrationNeeded()
//                .build();
//        realm = Realm.getInstance(realmConfig);
        mediaPlayer = new MediaPlayer();
    }

    public static AudioPronunciation getInstance(){
       if (instance == null){
           instance = new AudioPronunciation();
       }
       return instance;
    }



//    public void playPronunciation(String wordID) {
//        realm.executeTransactionAsync((realm) -> {
//            Word word = realm.where(Word.class).equalTo("id", wordID).findFirst();
//            String pronunciationURL = null;
//            if (word != null) {
//                pronunciationURL = word.getAudioPronounciation();
//                play(pronunciationURL);
//            }
//        });
//    }
    public void play(String urlAudio, Context context) {
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.reset();
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build());
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
