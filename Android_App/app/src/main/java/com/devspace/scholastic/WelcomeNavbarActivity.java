package com.devspace.scholastic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class WelcomeNavbarActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_nav_drawer);

        userType = getIntent().getStringExtra("userTypeUnique");

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new WelcomeScreenFragment()).commit();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.home_drawerlayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*
        try {
            Menu nav_menu = navigationView.getMenu();
            nav_menu.findItem(R.id.plagiarism).setVisible(false);
        } catch (NullPointerException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        */

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_closed);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new WelcomeScreenFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_timetable:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TimeTableFragment()).commit();
                break;
            case R.id.labs_classes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new LabClassesFragment()).commit();
                break;
            case R.id.report_cards:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ReportCardFragment()).commit();
                break;
            case R.id.fee_payment:
                Toast.makeText(this, "Fee Payment.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.voatz:
                Toast.makeText(this, "Voatz.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.help:
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "1234567890"));
                startActivity(intent);
                break;
            case R.id.plagiarism:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PlagiarismCheckFragment()).commit();
                break;
            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new WelcomeScreenFragment()).commit();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
