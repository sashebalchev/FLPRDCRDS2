package com.dmu.sash.flprdcrds;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class LearningDataProvider {
    private SharedPreferences sharedPreferences;
    private SessionManager sessionManager;
    private int session;
    private Realm realm;

    public LearningDataProvider(Context context) {
        sessionManager = SessionManager.getInstance(context);
        session = sessionManager.getSession();
        realm = RealmFactory.getRealm();
    }

    public List<Word> getData() {
        List<Word> wordsForSession;
        if (!realm.isEmpty()) {
            wordsForSession = getSessionData();
            while (wordsForSession.size() <= 0) {
                session = sessionManager.nextSession();
                getSessionData();
            }
        } else {
            session = sessionManager.initialSession();
            wordsForSession = new ArrayList<>(0);
        }
        return wordsForSession;
    }

    private List<Word> getSessionData() {
        RealmResults<Word> wordRealmResults = null;
        switch (session) {
            case 1:
                wordRealmResults = realm.where(Word.class).equalTo("score", 1).findAll();
                break;
            case 2:
                wordRealmResults = realm.where(Word.class).lessThanOrEqualTo("score", 2).findAll();
                break;
            case 3:
                wordRealmResults = realm.where(Word.class).equalTo("score", 1).findAll();
                break;
            case 4:
                wordRealmResults = realm.where(Word.class).lessThanOrEqualTo("score", 2).findAll();
                break;
            case 5:
                wordRealmResults = realm.where(Word.class).findAll();
                break;
            default:
                wordRealmResults = realm.where(Word.class).findAll();
                break;
        }
        return realm.copyFromRealm(wordRealmResults);
    }

}
