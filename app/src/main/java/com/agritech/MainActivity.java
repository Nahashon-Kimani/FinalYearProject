package com.agritech;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.agritech.activity.Forum;
import com.agritech.drawerFragment.Articles;
import com.agritech.drawerFragment.Events;
import com.agritech.drawerFragment.Officer;
import com.agritech.drawerFragment.Processing;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);


        navigationView.setItemIconTintList(null);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, drawer, toolbar,
                R.string.navigation_open, R.string.navigation_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        setONavigationViewClickListener();//This method is used to setOnItem Selected listener to navigation View
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Articles()).commit();
            navigationView.setCheckedItem(R.id.article_drawer);
        }

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void setONavigationViewClickListener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.article_drawer:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Articles()).commit();
                        break;
                    case R.id.processing_drawer:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Processing()).commit();
                        break;
                    case R.id.officer_drawer:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Officer()).commit();
                        break;
                    case R.id.event_drawer:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Events()).commit();
                        break;
                    case R.id.forum_drawer:
                        startActivity(new Intent(MainActivity.this, Forum.class));
                        break;
                    case R.id.app_setting:
                        Toast.makeText(MainActivity.this, "Under Development", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.share_app:
                        Toast.makeText(MainActivity.this, "Share App", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.share_feedback:
                        Toast.makeText(MainActivity.this, "Share Feedback", Toast.LENGTH_SHORT).show();
                        break;
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
}
