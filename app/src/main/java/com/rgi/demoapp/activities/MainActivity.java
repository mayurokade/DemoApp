package com.rgi.demoapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.rgi.demoapp.R;
import com.rgi.demoapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        clickListers();
    }

    private void clickListers() {
        binding.cvFicition.setOnClickListener(v -> {
            startActivity(new Intent(this, BookListActivity.class).putExtra("key", "Fiction"));
        });
        binding.cvAdventure.setOnClickListener(v -> {
            startActivity(new Intent(this, BookListActivity.class).putExtra("key", "Adventure"));
        });
        binding.cvDrama.setOnClickListener(v -> {
            startActivity(new Intent(this, BookListActivity.class).putExtra("key", "Drama"));
        });
        binding.cvHistory.setOnClickListener(v -> {
            startActivity( new Intent(this, BookListActivity.class).putExtra("key", "History"));
        });
        binding.cvHumor.setOnClickListener(v -> {
            startActivity(new Intent(this, BookListActivity.class).putExtra("key", "Humor"));
        });
        binding.cvPhilosophy.setOnClickListener(v -> {
            startActivity( new Intent(this, BookListActivity.class).putExtra("key", "Philosophy"));
        });
        binding.cvPolitics.setOnClickListener(v -> {
            startActivity(new Intent(this, BookListActivity.class).putExtra("key", "Politics"));
        });
    }

}