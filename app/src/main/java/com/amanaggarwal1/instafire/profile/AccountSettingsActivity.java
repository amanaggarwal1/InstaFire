package com.amanaggarwal1.instafire.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.amanaggarwal1.instafire.R;
import com.amanaggarwal1.instafire.Utils.SectionsStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AccountSettingsActivity extends AppCompatActivity {

    private static final String TAG = "AccountSettingsActivity";

    private SectionsStatePagerAdapter adapter;
    private ViewPager viewPager;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        ImageView backButton = findViewById(R.id.account_settings_back_button);
        viewPager = findViewById(R.id.container);
        relativeLayout = findViewById(R.id.account_settings_relative_layout);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setupSettingsOptionsList();
        setupFragments();

    }

    private void setupFragments(){
        adapter = new SectionsStatePagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new EditProfileFragment(), getString(R.string.edit_profile_fragment)); // Fragment 0
        adapter.addFragment(new SignOutFragment(), getString(R.string.sign_out_fragment)); // Fragment 1
    }

    private void setViewPager(int fragmentNumber){
        relativeLayout.setVisibility(View.GONE);
        Log.d(TAG, "setViewPager: fragment number chosen : " + fragmentNumber);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(fragmentNumber);
    }

    private void setupSettingsOptionsList(){
        ListView listView = findViewById(R.id.account_settings_listview);
        List<String> options = new ArrayList<>();
        options.add(getString(R.string.edit_profile_fragment));
        options.add(getString(R.string.sign_out_fragment));
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, options);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: item clicked : " + i);
                setViewPager(i);
            }
        });
    }
}
