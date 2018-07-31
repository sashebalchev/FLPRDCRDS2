package com.dmu.sash.flprdcrds;


import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.MenuItem;
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

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    private Button button;
    private SearchTask searchTask;
    private DefinitionTask definitionTask;
    private static Realm realm;
    public static TextView data;
    private BottomNavigationView mainNav;
    //    private FrameLayout mainFrame;
    private HomeFragment homeFragment;
    private ProfileFragment profileFragment;
    private SettingsFragment settingsFragment;
    public static String parsedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchTask = new SearchTask();
        searchTask.delegate=this;
        definitionTask = new DefinitionTask();
        definitionTask.delegate=this;
        //Realm stuff here.
        realm = Realm.getDefaultInstance();
        RealmResults<Word> words = realm.where(Word.class).findAll();
        final WordAdapter adapter = new WordAdapter(this, words);

        ListView listView = findViewById(R.id.word_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Word word = (Word) parent.getAdapter().getItem(position);
                final EditText wordEdit = new EditText(MainActivity.this);
                wordEdit.setText(word.getWord());
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Edit Entry")
                        .setView(wordEdit)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //TODO add edit word
                            }
                        })
                        .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteWordEntry(word.getId());
                            }
                        })
                        .create();
                dialog.show();
            }
        });

        //Button to add words here.
//        final EditText editText = findViewById(R.id.edit_text);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText taskEditText = new EditText(MainActivity.this);
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Add Word")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                searchTask.execute(findWord(taskEditText));
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            }
        });


//        button = findViewById(R.id.button2);
//        data = findViewById(R.id.fetched_text);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new SearchTask().execute(findWord(editText));
//            }
//        });


        //Frame stuff here.
//        mainFrame = findViewById(R.id.main_frame);
//        mainNav = findViewById(R.id.main_nav);
//        homeFragment = new HomeFragment();
//        profileFragment = new ProfileFragment();
//        settingsFragment = new SettingsFragment();
//        setFrag(homeFragment);
//
//
//        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                switch (item.getItemId()) {
//                    case R.id.nav_home:
//                        setFrag(homeFragment);
//                        return true;
//
//                    case R.id.nav_profile:
//                        setFrag(profileFragment);
//                        return true;
//
//                    case R.id.nav_settings:
//                        setFrag(settingsFragment);
//                        return true;
//
//                    default:
//                        return false;
//                }
//            }
//        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    private void deleteWordEntry(String id) {
        realm.executeTransactionAsync((realm) -> {
            realm.where(Word.class).equalTo("id", id)
                    .findFirst()
                    .deleteFromRealm();
        });
    }

    //Logic goes here
    @NonNull
    private String findWord(EditText text) {
        final String language = "en";
        final String word = text.getText().toString();
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com/api/v1/search/" + language + "?q=" + word_id;
    }

    private void getWordDefinition(String json) {
        final String language = "en";
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            String word = jsonArray.getJSONObject(0).getString("word");
            definitionTask.execute("https://od-api.oxforddictionaries.com/api/v1/entries/" + language + "/" + word);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void results(String json) {
        Gson gson = new Gson();
        Response response = gson.fromJson(json, Response.class);
        final String word = response.getResults().get(0).getWord();
        System.out.println(word);
        // TODO Loop senses over and find the short definitions there.
        // TODO handle exception if word doesn't exist
        String definition = response.getResults().get(0).getLexicalEntries().get(0).getEntries().get(0).getSenses().get(0).getShort_definitions().get(0);

        final Word wordDef = new Word();
        wordDef.setId(UUID.randomUUID().toString());
        wordDef.setWord(word);
        wordDef.setDefinition(definition);
        wordDef.setPronounciation("");
        wordDef.setAudioPronounciation("");
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(wordDef);
            }
        });
    }

    //Setting the fragment
//    private void setFrag(Fragment fragment) {
//        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
//        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.main_frame, fragment);
//        fragmentTransaction.commit();
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void processFinish(String output) {
        System.out.println(output);
        getWordDefinition(output);
    }

    @Override
    public void processFinish2(String output) {
        System.out.println(output);
        results(output);
    }
}
