package com.example.pandu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pandu.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        getSupportActionBar().setTitle("Informasi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}