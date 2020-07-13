package com.example.news_and_weather_app;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    Bundle bundle=new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer=findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Fragment selectedFragment = new HomeFragment();
        bundle.putString("urlRSS", "https://vnexpress.net/rss/tin-moi-nhat.rss");
        selectedFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragme_container,selectedFragment).commit();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, drawer, toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()){
                        case R.id.nav_home:
                            bundle.putString("urlRSS", "https://vnexpress.net/rss/tin-moi-nhat.rss");
                            selectedFragment = new HomeFragment();
                            selectedFragment.setArguments(bundle);
                            break;
                        case R.id.nav_video:
                            bundle.putString("urlRSS", "https://video.vnexpress.net/");
                            selectedFragment = new FavoriteFragment();
                            selectedFragment.setArguments(bundle);
                            break;
                        case R.id.nav_thoitiet:
                            selectedFragment = new WeatherFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragme_container,selectedFragment).commit();
                    return true;
                }
            };

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment selectedFragment = new HomeFragment();
        bundle = new Bundle(  );
        switch (menuItem.getItemId()){
            case R.id.nav_trangchu:
                bundle.putString("urlRSS", "https://vnexpress.net/rss/tin-moi-nhat.rss");
                break;
            case R.id.nav_thethao:
                bundle.putString("urlRSS", "https://vnexpress.net/rss/the-thao.rss");
                break;
            case R.id.nav_thoisu:
                bundle.putString("urlRSS", "https://vnexpress.net/rss/thoi-su.rss");
                break;
            case R.id.nav_thegioi:
                bundle.putString("urlRSS", "https://vnexpress.net/rss/the-gioi.rss");
                break;
            case R.id.nav_suckhoe:
                bundle.putString("urlRSS", "https://vnexpress.net/rss/suc-khoe.rss");
                break;
            case R.id.nav_giaoduc:
                bundle.putString("urlRSS", "https://vnexpress.net/rss/giao-duc.rss");
                break;
            case R.id.nav_phapluat:
                bundle.putString("urlRSS", "https://vnexpress.net/rss/phap-luat.rss");
                break;
            case R.id.nav_giaitri:
                bundle.putString("urlRSS", "https://vnexpress.net/rss/giai-tri.rss");
                break;
            case R.id.nav_startup:
                bundle.putString("urlRSS", "https://vnexpress.net/rss/startup.rss");
                break;
            case R.id.nav_kinhdoanh:
                bundle.putString("urlRSS", "https://vnexpress.net/rss/kinh-doanh.rss");
                break;
            case R.id.nav_tamsu:
                bundle.putString("urlRSS", "https://vnexpress.net/rss/tam-su.rss");
                break;
            case R.id.nav_ykien:
                bundle.putString("urlRSS", "https://vnexpress.net/rss/y-kien.rss");
                break;
            case R.id.nav_xe:
                bundle.putString("urlRSS", "https://vnexpress.net/rss/oto-xe-may.rss");
                break;
            case R.id.nav_sohoa:
                bundle.putString("urlRSS", "https://vnexpress.net/rss/so-hoa.rss");
                break;
            case R.id.nav_khoahoc:
                bundle.putString("urlRSS", "https://vnexpress.net/rss/khoa-hoc.rss");
                break;
            case R.id.nav_dulich:
                bundle.putString("urlRSS", "https://vnexpress.net/rss/du-lich.rss");
                break;
            case R.id.nav_doisong:
                bundle.putString("urlRSS", "https://vnexpress.net/rss/gia-dinh.rss");
                break;
            case R.id.nav_tinxemnhieu:
                bundle.putString("urlRSS", "https://vnexpress.net/rss/tin-xem-nhieu.rss");
                break;
            case R.id.nav_tinnoibat:
                bundle.putString("urlRSS", "https://vnexpress.net/rss/tin-noi-bat.rss");
                break;
            case R.id.nav_tinmoinhat:
                bundle.putString("urlRSS", "https://vnexpress.net/rss/tin-moi-nhat.rss");
                break;
            case R.id.nav_cuoi:
                bundle.putString("urlRSS", "https://vnexpress.net/rss/cuoi.rss");
                break;
        }
        selectedFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragme_container,selectedFragment).commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}