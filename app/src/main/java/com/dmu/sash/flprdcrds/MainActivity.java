package com.dmu.sash.flprdcrds;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.dmu.sash.flprdcrds.learning.LearningFragment;
import com.dmu.sash.flprdcrds.management.ManagementFragment;
import com.dmu.sash.flprdcrds.notification.NotificationAlarmSetter;
import com.dmu.sash.flprdcrds.profile.ProfileFragment;
import com.dmu.sash.flprdcrds.helpers.PreferencesManager;
import com.dmu.sash.flprdcrds.settings.SettingsFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView mainNav = findViewById(R.id.main_nav);
        mainNav.setOnNavigationItemSelectedListener(MainActivity.this);
        PreferencesManager preferencesManager = new PreferencesManager(this);
        setFrag(new LearningFragment());
        if (!preferencesManager.getNotificationFlag()) {
            NotificationAlarmSetter notificationAlarmSetter = new NotificationAlarmSetter(this);
            notificationAlarmSetter.setAlarm();
        }
    }

    //Frame stuff here.
    //Setting the fragment
    private boolean setFrag(android.support.v4.app.Fragment fragment) {
        if (fragment != null) {
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
        switch (item.getItemId()) {
            case R.id.nav_home:
                fragment = new LearningFragment();
                break;
            case R.id.nav_management:
                fragment = ManagementFragment.getInstance();
                break;
            case R.id.nav_profile:
                fragment = ProfileFragment.getInstance();
                break;
            case R.id.nav_settings:
                fragment = new SettingsFragment();
                break;
        }
        return setFrag(fragment);
    }
}
