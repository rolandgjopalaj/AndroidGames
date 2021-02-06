package com.gjopalaj.r_gj_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;

public class GamesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        Button btnMM=findViewById(R.id.btnMM);
        btnMM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentMM=new Intent(GamesActivity.this,MasterMind.class);
                startActivity(intentMM);
            }
        });

        Button btnSnake=findViewById(R.id.btnSnake);
        btnSnake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSnake=new Intent(GamesActivity.this, SnakeActivity.class);
                startActivity(intentSnake);
            }
        });

        Button btnXOGame=findViewById(R.id.btnXOGame);
        btnXOGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentXOGame=new Intent(GamesActivity.this,XOGameActivity.class);
                startActivity(intentXOGame);
            }
        });

        Button btnDrive=findViewById(R.id.btnDrive);
        btnDrive.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intentDriveGame=new Intent(GamesActivity.this,DriveGame.class);
                startActivity(intentDriveGame);
            }
        });

    }
}