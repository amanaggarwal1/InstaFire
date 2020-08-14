package com.amanaggarwal1.instafire.share;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.amanaggarwal1.instafire.R;
import com.amanaggarwal1.instafire.Utils.BottomNavigationViewHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ShareActivity extends AppCompatActivity {
    private static final String TAG = "ShareActivity";
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: ");
        bottomNavigationView = findViewById(R.id.bottom_nav_view_bar);
        BottomNavigationViewHelper.enableNavigation(this, bottomNavigationView, BottomNavigationViewHelper.ACTIVITY_NUMBER_SHARE);
    }
}
