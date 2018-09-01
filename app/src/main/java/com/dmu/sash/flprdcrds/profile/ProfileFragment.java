package com.dmu.sash.flprdcrds.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dmu.sash.flprdcrds.R;
import com.dmu.sash.flprdcrds.database.RealmFactory;
import com.dmu.sash.flprdcrds.database.entities.Word;
import com.dmu.sash.flprdcrds.helpers.PreferencesManager;

import java.util.ArrayList;

import io.realm.Realm;

public class ProfileFragment extends Fragment {
    private static ProfileFragment instance;
    Realm realm;

    public ProfileFragment() {
    }

    public static ProfileFragment getInstance() {
        if (instance == null) {
            instance = new ProfileFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = RealmFactory.getRealm();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView sessionsTextView = view.findViewById(R.id.number_sessions);
        TextView masteredWordsTextView = view.findViewById(R.id.mastered_words);
        TextView bestKnownWordsTextView = view.findViewById(R.id.known_words);
        TextView struggleWordsTextView = view.findViewById(R.id.struggled_words);

        String forSessionsTV = getResources().getString(R.string.sessions_finished_prompt) + getTotalSessions();
        String forMasteredWordsTV = getText(R.string.words_mastered_prompt, "proficiency");
        String forKnownWordsTV = getText(R.string.best_known_words_prompt, "consecutiveKnownSessions");
        String forStruggleWordsTV = getText(R.string.words_you_struggle_with_prompt, "consecutiveNotKnownSessions");

        sessionsTextView.setText(forSessionsTV);
        masteredWordsTextView.setText(forMasteredWordsTV);
        bestKnownWordsTextView.setText(forKnownWordsTV);
        struggleWordsTextView.setText(forStruggleWordsTV);
    }

    private int getTotalSessions() {
        PreferencesManager preferencesManager = new PreferencesManager(getContext());
        return preferencesManager.getTotalSessions();
    }

    @NonNull
    private String getText(int strId, String fieldName) {
        ArrayList<Word> words = (ArrayList<Word>) realm.copyFromRealm(realm.where(Word.class)
                .greaterThan(fieldName, 5).findAll());
        StringBuilder result = new StringBuilder();
        result.append(getResources().getString(strId));
        for (int i = 0; i < words.size(); i++) {
            result.append(words.get(i).getWord());
            if (i < (words.size() - 1)) {
                result.append(", ");
            }
        }
        return result.toString();
    }
}
