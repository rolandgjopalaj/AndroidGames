package com.gjopalaj.r_gj_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGames=findViewById(R.id.btnGames);
        btnGames.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent gamesIntent=new Intent(MainActivity.this, GamesActivity.class);
                startActivity(gamesIntent);
            }
        });
    }
}