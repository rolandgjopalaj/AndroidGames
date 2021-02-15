package com.gjopalaj.r_gj_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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

        //////////////////////////////////////////////////////////////////////
        Button btnSnakeCode=findViewById(R.id.btnSnakeCode);
        btnSnakeCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://gjopalaj.noip.me/games/snake");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        Button btnMMCode=findViewById(R.id.btnMMCode);
        btnMMCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://gjopalaj.noip.me/games/mm");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        Button btnTrisCode=findViewById(R.id.btnTrisCode);
        btnTrisCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://gjopalaj.noip.me/games/tris");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        Button btnDriveCode=findViewById(R.id.btnDriveCode);
        btnDriveCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://gjopalaj.noip.me/games/drive");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }
}