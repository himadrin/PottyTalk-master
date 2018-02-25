package com.andrewogren.pottytalk;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.design.widget.TabLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    public ViewPager viewPager;
    FPAdapter fragmentPageAdapter;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private MainDisplayFragment mainDisplayFragment;
    private ProfileFragment profileFragment;
    private LeaderboardFragment leaderboardFragment;
    private ArrayList<Fragment> fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser == null) {
            loadLogInView();
        }

        mainDisplayFragment = new MainDisplayFragment();
        profileFragment = new ProfileFragment();
        leaderboardFragment = new LeaderboardFragment();

        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewpager);

        fragments = new ArrayList<Fragment>();
        fragments.add(mainDisplayFragment);
        fragments.add(leaderboardFragment);
        fragments.add(profileFragment);

        fragmentPageAdapter = new FPAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(fragmentPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        loadLogInView();
        return true;
    }


    private void loadLogInView() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
