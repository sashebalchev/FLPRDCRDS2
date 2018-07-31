package com.dmu.sash.flprdcrds;


import android.app.Fragment;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    // my comment
    private URLAsyncTask URLAsyncTask;
    public static Realm realm;
    public static TextView data;
    private BottomNavigationView mainNav;
    private FrameLayout mainFrame;
    private FrameLayout something;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFrame = findViewById(R.id.main_frame);
        mainNav = findViewById(R.id.main_nav);
        mainNav.setOnNavigationItemSelectedListener(MainActivity.this);
        setFrag(new HomeFragment());

        //Realm stuff here.

//        RealmResults<Word> words = realm.where(Word.class).findAll();
//        final WordAdapter adapter = new WordAdapter(MainActivity.this, words);
//        ListView listView = findViewById(R.id.word_list);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener((parent, view, position, id) -> {
//            final Word word = (Word) parent.getAdapter().getItem(position);
//            final EditText wordEdit = new EditText(MainActivity.this);
//            wordEdit.setText(word.getWord());
//            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
//                    .setTitle("Edit Entry")
//                    .setView(wordEdit)
//                    .setPositiveButton("Pronunciation", (dialog1, which) -> audioPronunciation(word.getId()))
//                    .setNegativeButton("Delete", (dialog12, which) -> deleteWordEntry(word.getId()))
//                    .create();
//            dialog.show();
//        });

    }

        //Button to add words here.

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(view -> {
//            final EditText taskEditText = new EditText(MainActivity.this);
//            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
//                    .setTitle("Add Word")
//                    .setView(taskEditText)
//                    .setPositiveButton("Add", (dialogInterface, i) -> {
//                        GetWord getWord = new GetWord();
//                        URLAsyncTask = new URLAsyncTask(getWord);
//                        URLAsyncTask.execute(getWord.searchWord(taskEditText));
//                    })
//                    .setNegativeButton("Cancel", null)
//                    .create();
//            dialog.show();
//        });


        //Frame stuff here.





    private void audioPronunciation(String id) {
        AudioPronunciation audio = new AudioPronunciation();
        audio.playPronunciation(id);
    }

    private void deleteWordEntry(String id) {
        realm.executeTransactionAsync((realm) -> {
            Objects.requireNonNull(realm.where(Word.class).equalTo("id", id)
                    .findFirst())
                    .deleteFromRealm();
        });
    }

    //Setting the fragment
    private boolean setFrag(android.support.v4.app.Fragment fragment) {
        if (fragment != null){

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_frame, fragment)
                    .commit();

            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        android.support.v4.app.Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_profile:
                fragment = new ProfileFragment();
                break;
            case R.id.nav_settings:
                fragment = new SettingsFragment();
                break;
        }
        return setFrag(fragment);
    }

//    @Override
//    public void processFinish(String output) {
////        System.out.println(output);
//        getWordDefinition(output);
//    }


}
