package com.example.mobileprogramming_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void Move_to_Login(View view) {
        Intent intent = new Intent(MainActivity2.this, LoginActivity.class);
        startActivity(intent);
    }

    public void Move_to_Register(View view) {
        Intent intent = new Intent(MainActivity2.this, RegisterActivity.class);
        startActivity(intent);
    }
}