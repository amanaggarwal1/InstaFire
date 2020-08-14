package com.amanaggarwal1.instafire.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amanaggarwal1.instafire.R;
import com.amanaggarwal1.instafire.Utils.BottomNavigationViewHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Log.d(TAG, "onCreate: ");

        bottomNavigationView = findViewById(R.id.bottom_nav_view_bar);
        BottomNavigationViewHelper.enableNavigation(this, bottomNavigationView, BottomNavigationViewHelper.ACTIVITY_NUMBER_HOME);

        setupViewPager();
    }

    private void setupViewPager() {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new CameraFragment());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new MessagesFragment());

        viewPager = findViewById(R.id.container);
        viewPager.setAdapter(adapter);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_app_logo);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_messages);
    }
}
