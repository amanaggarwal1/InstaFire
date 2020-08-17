package com.amanaggarwal1.instafire.profile;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.amanaggarwal1.instafire.R;

import java.util.ArrayList;
import java.util.List;

public class AccountSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        ImageView backButton = findViewById(R.id.account_settings_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setupSettingsOptionsList();
    }

    private void setupSettingsOptionsList(){
        ListView listView = findViewById(R.id.account_settings_listview);
        List<String> options = new ArrayList<>();
        options.add(getString(R.string.edit_profile));
        options.add(getString(R.string.log_out));
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapter);
    }
}
