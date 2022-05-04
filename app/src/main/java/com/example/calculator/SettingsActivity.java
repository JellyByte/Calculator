package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ShareActionProvider;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    SharedPreferences sp;
    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sp = getSharedPreferences("save", Context.MODE_PRIVATE);

        level = 0;
    }


    public void reset(View view) {
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("level", level);
        edit.commit();
        Toast.makeText(this,"Level reset",Toast.LENGTH_SHORT).show();
    }


    public void startGame(View view){
        Intent intent = new Intent(SettingsActivity.this, GameActivity.class);
        startActivity(intent);
    }

    public void exit(View view){
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);
    }


}