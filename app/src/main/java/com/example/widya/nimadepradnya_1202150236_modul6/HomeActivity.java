package com.example.widya.nimadepradnya_1202150236_modul6;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ViewPager viewp;
    TabLayout tabl;
    AppBarLayout appb;
    Toolbar toolb;
    FirebaseAuth authen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewp = findViewById(R.id.viewpager);
        tabl = findViewById(R.id.tablay);
        appb = findViewById(R.id.appbar);
        toolb = findViewById(R.id.toolbar);
        authen = FirebaseAuth.getInstance();

        setupPager(viewp);
    }
    public void setupPager(ViewPager v) {
        VPAdapter adapter = new VPAdapter(getSupportFragmentManager());
        adapter.addFragment(new fragmentnew(), "NEWEST");
        adapter.addFragment(new fragmentme(), "ME");

        v.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.logout){
            authen.signOut();
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            finish();
        }
        return true;
    }


    public void post(View view) {
        startActivity(new Intent(HomeActivity.this, UploadActivity.class));
    }

    class VPAdapter extends FragmentPagerAdapter {
        private final List<Fragment> listfragment = new ArrayList<>();
        private final List<String> listfragmenttitle = new ArrayList<>();

        public VPAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return listfragment.get(position);
        }
        public void addFragment(Fragment f, String title){
            listfragment.add(f);
            listfragmenttitle.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return listfragmenttitle.get(position);
        }

        @Override
        public int getCount() {
            return listfragment.size();
        }
    }
}

