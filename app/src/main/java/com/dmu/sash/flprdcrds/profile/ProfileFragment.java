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
import com.dmu.sash.flprdcrds.database.Word;

import java.util.ArrayList;

import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 */
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
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

        String forSessionsTV = "Learning sessions finished: ";
        String forMasteredWordsTV = "Words mastered: ";
        String forKnownWordsTV = "Best known words: ";
        String forStruggleWordsTV = "Words you struggle with: ";

        ArrayList<Word> masteredWords = (ArrayList<Word>)realm.copyFromRealm(realm.where(Word.class)
                .greaterThan("proficiency", 5).findAll());

        for(int i = 0; i < masteredWords.size();i++){
            if (i == masteredWords.size()-1){
                forMasteredWordsTV += masteredWords.get(i).getWord();
            } else {
                forMasteredWordsTV += masteredWords.get(i).getWord() + ", ";
            }
        }

        ArrayList<Word> bestKnownWords = (ArrayList<Word>)realm.copyFromRealm(realm.where(Word.class)
                .greaterThan("consecutiveKnownSessions", 5).findAll());

        for(int i = 0; i < bestKnownWords.size();i++){
            if (i == bestKnownWords.size()-1){
                forKnownWordsTV += bestKnownWords.get(i).getWord();
            } else {
                forKnownWordsTV += bestKnownWords.get(i).getWord() + ", ";
            }
        }

        ArrayList<Word> struggleWords = (ArrayList<Word>)realm.copyFromRealm(realm.where(Word.class)
                .greaterThan("consecutiveNotKnownSessions", 5).findAll());

        for(int i = 0; i < struggleWords.size();i++){
            if (i == struggleWords.size()-1){
                forStruggleWordsTV += struggleWords.get(i).getWord();
            } else {
                forStruggleWordsTV += struggleWords.get(i).getWord() + ", ";
            }
        }


        sessionsTextView.setText(forSessionsTV);
        masteredWordsTextView.setText(forMasteredWordsTV);
        bestKnownWordsTextView.setText(forKnownWordsTV);
        struggleWordsTextView.setText(forStruggleWordsTV);
    }
}
