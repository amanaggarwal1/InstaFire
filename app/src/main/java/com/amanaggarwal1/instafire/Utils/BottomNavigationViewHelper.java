package com.amanaggarwal1.instafire.Utils;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.amanaggarwal1.instafire.R;
import com.amanaggarwal1.instafire.home.HomeActivity;
import com.amanaggarwal1.instafire.profile.ProfileActivity;
import com.amanaggarwal1.instafire.search.SearchActivity;
import com.amanaggarwal1.instafire.share.ShareActivity;
import com.amanaggarwal1.instafire.updates.UpdatesActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationViewHelper {
    private static final String TAG = "BottomNavigationViewHel";
    public static final int ACTIVITY_NUMBER_HOME = 0;
    public static final int ACTIVITY_NUMBER_SEARCH = 1;
    public static final int ACTIVITY_NUMBER_SHARE = 2;
    public static final int ACTIVITY_NUMBER_UPDATES = 3;
    public static final int ACTIVITY_NUMBER_PROFILE = 4;


    public static void enableNavigation(final Context context, BottomNavigationView view, int ACTIVITY_NUMBER){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:
                        //Index 0
                        Intent intent0 = new Intent(context, HomeActivity.class);
                        context.startActivity(intent0);
                        return true;
                    case R.id.ic_search:
                        //Index 1
                        Intent intent1 = new Intent(context, SearchActivity.class);
                        context.startActivity(intent1);
                        return true;
                    case R.id.ic_share:
                        //Index 2
                        Intent intent2 = new Intent(context, ShareActivity.class);
                        context.startActivity(intent2);
                        return true;
                    case R.id.ic_updates:
                        //Index 3
                        Intent intent3 = new Intent(context, UpdatesActivity.class);
                        context.startActivity(intent3);
                        return true;
                    case R.id.ic_profile:
                        //Index 4
                        Intent intent4 = new Intent(context, ProfileActivity.class);
                        context.startActivity(intent4);
                        return true;
                    default:
                        return false;

                }
            }
        });
        view.getMenu().getItem(ACTIVITY_NUMBER).setChecked(true);

    }
}
