package com.amanaggarwal1.instafire.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.amanaggarwal1.instafire.R;
import com.amanaggarwal1.instafire.Utils.BottomNavigationViewHelper;
import com.amanaggarwal1.instafire.Utils.UniversalImageLoader;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";

    private BottomNavigationView bottomNavigationView;
    private CircleImageView profilePhoto;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: ");
        bottomNavigationView = findViewById(R.id.bottom_nav_view_bar);
        profilePhoto = findViewById(R.id.profile_photo);
        progressBar = findViewById(R.id.profile_progress_bar);

        BottomNavigationViewHelper.enableNavigation(this, bottomNavigationView, BottomNavigationViewHelper.ACTIVITY_NUMBER_PROFILE);
        setupProfilePhoto();
        setupToolbar();
    }

    private void setupProfilePhoto() {
        String imageURL = "https://images.idgesg.net/images/article/2017/08/android_robot_logo_by_ornecolorada_cc0_via_pixabay1904852_wide-100732483-large.jpg";
        //UniversalImageLoader.setImage(imageURL, profilePhoto, progressBar, "");
        Glide.with(this).load(imageURL).into(profilePhoto);
    }

    private void setupToolbar(){
        Toolbar toolbar = findViewById(R.id.profile_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile_menu :
                        Intent intent = new Intent(getApplicationContext(), AccountSettingsActivity.class);
                        startActivity(intent);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

}
