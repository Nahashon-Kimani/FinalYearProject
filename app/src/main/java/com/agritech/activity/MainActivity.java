package com.agritech.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.agritech.R;
import com.agritech.drawerFragment.Articles;
import com.agritech.drawerFragment.Events;
import com.agritech.drawerFragment.Officer;
import com.agritech.drawerFragment.Processing;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;
    public static long backPressed;

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

            if (backPressed + 2000 > System.currentTimeMillis()) {
                super.onBackPressed();
            } else {
                //Toast.makeText(MainActivity.this, "Press back again to exit", Toast.LENGTH_SHORT).show();
                Snackbar snackbar = Snackbar.make(drawer, "Press back again to exit", Snackbar.LENGTH_SHORT);
                snackbar.show();


                backPressed = System.currentTimeMillis();
            }

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
                        //Toast.makeText(MainActivity.this, "Under Development", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, SettingActivity.class));
                        break;
                    case R.id.share_app:
                        shareApp();
                        break;
                    case R.id.share_feedback:
                        shareFeedback();
                        break;
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    public void shareApp() {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND)
                .putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.app_name));
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent, "This is test"));
    }

    public void shareFeedback() {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "nahashonmbuci@gmail.com", null));
        intent.putExtra(Intent.EXTRA_SUBJECT, "AGRI TECH FEEDBACK");
        //intent.putExtra(Intent.EXTRA_CC, "nahashonmbuci@gmail.com");
        intent.putExtra(Intent.EXTRA_TEXT, "Please explain your issue");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "Send Email"));
        }
    }
}