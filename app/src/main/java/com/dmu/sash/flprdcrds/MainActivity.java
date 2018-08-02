package com.dmu.sash.flprdcrds;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    // my comment
    // alskjdasd
    // Sashe comment
    // sadkhaskdasd
    // Third comment
    private BottomNavigationView mainNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainNav = findViewById(R.id.main_nav);
        mainNav.setOnNavigationItemSelectedListener(MainActivity.this);
        PreferenceManager.setDefaultValues(this, R.xml.preferences,false);
        setFrag(new LearningFragment());
    }
        //Frame stuff here.
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        android.support.v4.app.Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.nav_home:
                fragment = new LearningFragment();
                break;
            case R.id.nav_management:
                fragment = new ManagementFragment();
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
}
