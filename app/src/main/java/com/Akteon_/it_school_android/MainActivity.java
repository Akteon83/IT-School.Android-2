package com.Akteon_.it_school_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.Akteon_.it_school_android.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MySurfaceHolder(this));
    }
}