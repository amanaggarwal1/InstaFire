package com.amanaggarwal1.instafire.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.amanaggarwal1.instafire.R;
import com.amanaggarwal1.instafire.Utils.BottomNavigationViewHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    private static final String TAG = "ProfileFragment";

    private TextView postsTV, followersTV, followingTV, usernameTV, displayNameTV, descriptionTV, websiteTV, editProfileTV;
    private BottomNavigationView bottomNavigationView;
    private CircleImageView profilePhoto;
    private ProgressBar progressBar;
    private GridView gridView;
    private androidx.appcompat.widget.Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        defineWidgets(view);

        BottomNavigationViewHelper.enableNavigation(getActivity(), bottomNavigationView, BottomNavigationViewHelper.ACTIVITY_NUMBER_PROFILE);
        setupToolbar();
        return view;
    }

    private void defineWidgets(View view) {

        postsTV = view.findViewById(R.id.posts_number_tv);
        followersTV = view.findViewById(R.id.followers_number_tv);
        followingTV = view.findViewById(R.id.following_number_tv);
        usernameTV = view.findViewById(R.id.profile_toolbar_username);
        displayNameTV = view.findViewById(R.id.profile_display_name);
        descriptionTV = view.findViewById(R.id.profile_description);
        websiteTV = view.findViewById(R.id.profile_website);
        editProfileTV = view.findViewById(R.id.profile_edit_profile_tv);

        toolbar = view.findViewById(R.id.profile_toolbar);
        bottomNavigationView = view.findViewById(R.id.bottom_nav_view_bar);
        profilePhoto = view.findViewById(R.id.profile_photo);
        gridView = view.findViewById(R.id.profile_grid_view);
        progressBar = view.findViewById(R.id.profile_progress_bar);
    }

    private void setupToolbar(){
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile_menu :
                        Intent intent = new Intent(getActivity(), AccountSettingsActivity.class);
                        startActivity(intent);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
}
