package com.gjopalaj.r_gj_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class XOGameActivity extends AppCompatActivity {

    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    ImageView img5;
    ImageView img6;
    ImageView img7;
    ImageView img8;
    ImageView img9;
    Button btnAgain;

    private String turn;
    private int result[];
    private int pointsX;
    private int pointsO;

    TextView txtX;
    TextView txtO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_o_game);

        btnAgain=findViewById(R.id.btnAgain);
        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end();
            }
        });
        btnAgain.setEnabled(false);

        turn="x";//default turn
        result=new int[10];
        pointsX=0;
        pointsO=0;

        txtX=findViewById(R.id.txtX);
        txtO=findViewById(R.id.txtO);

        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);
        img3=findViewById(R.id.img3);
        img4=findViewById(R.id.img4);
        img5=findViewById(R.id.img5);
        img6=findViewById(R.id.img6);
        img7=findViewById(R.id.img7);
        img8=findViewById(R.id.img8);
        img9=findViewById(R.id.img9);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result[1]==0)
                {
                    if(turn=="x")
                    {
                        img1.setImageResource(R.drawable._x);
                        turn="o";
                        result[1]=1;
                    }else{
                        img1.setImageResource(R.drawable.o);
                        turn="x";
                        result[1]=2;
                    }
                    gameOver();
                }
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result[2]==0)
                {
                    if(turn=="x")
                    {
                        img2.setImageResource(R.drawable._x);
                        turn="o";
                        result[2]=1;
                    }else{
                        img2.setImageResource(R.drawable.o);
                        turn="x";
                        result[2]=2;
                    }
                    gameOver();
                }
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result[3]==0)
                {
                    if(turn=="x")
                    {
                        img3.setImageResource(R.drawable._x);
                        turn="o";
                        result[3]=1;
                    }else{
                        img3.setImageResource(R.drawable.o);
                        turn="x";
                        result[3]=2;
                    }
                    gameOver();
                }
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result[4]==0)
                {
                    if(turn=="x")
                    {
                        img4.setImageResource(R.drawable._x);
                        turn="o";
                        result[4]=1;
                    }else{
                        img4.setImageResource(R.drawable.o);
                        turn="x";
                        result[4]=2;
                    }
                    gameOver();
                }
            }
        });

        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result[5]==0)
                {
                    if(turn=="x")
                    {
                        img5.setImageResource(R.drawable._x);
                        turn="o";
                        result[5]=1;
                    }else{
                        img5.setImageResource(R.drawable.o);
                        turn="x";
                        result[5]=2;
                    }
                    gameOver();
                }
            }
        });

        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result[6]==0)
                {
                    if(turn=="x")
                    {
                        img6.setImageResource(R.drawable._x);
                        turn="o";
                        result[6]=1;
                    }else{
                        img6.setImageResource(R.drawable.o);
                        turn="x";
                        result[6]=2;
                    }
                    gameOver();
                }
            }
        });

        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result[7]==0)
                {
                    if(turn=="x")
                    {
                        img7.setImageResource(R.drawable._x);
                        turn="o";
                        result[7]=1;
                    }else{
                        img7.setImageResource(R.drawable.o);
                        turn="x";
                        result[7]=2;
                    }
                    gameOver();
                }
            }
        });

        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result[8]==0)
                {
                    if(turn=="x")
                    {
                        img8.setImageResource(R.drawable._x);
                        turn="o";
                        result[8]=1;
                    }else{
                        img8.setImageResource(R.drawable.o);
                        turn="x";
                        result[8]=2;
                    }
                    gameOver();
                }
            }
        });

        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result[9]==0)
                {
                    if(turn=="x")
                    {
                        img9.setImageResource(R.drawable._x);
                        turn="o";
                        result[9]=1;
                    }else{
                        img9.setImageResource(R.drawable.o);
                        turn="x";
                        result[9]=2;
                    }
                    gameOver();
                }
            }
        });
    }

    private int control()
    {
        int aux=0;

        if(result[1]==1 && result[2]==1 && result[3]==1)
        {
            aux=1;
        }else if(result[1]==1 && result[4]==1 && result[7]==1)
        {
            aux=1;
        }else if(result[1]==1 && result[5]==1 && result[9]==1)
        {
            aux=1;
        }else if(result[2]==1 && result[5]==1 && result[8]==1)
        {
            aux=1;
        }else if(result[3]==1 && result[5]==1 && result[7]==1)
        {
            aux=1;
        }else if(result[3]==1 && result[6]==1 && result[9]==1)
        {
            aux=1;
        }else if(result[4]==1 && result[5]==1 && result[6]==2)
        {
            aux=1;
        }else if(result[7]==1 && result[8]==1 && result[9]==2)
        {
            aux=1;////////////////////////////////////  1
        } else if(result[1]==2 && result[2]==2 && result[3]==2)
        {
            aux=2;
        }else if(result[1]==2 && result[4]==2 && result[7]==2)
        {
            aux=2;
        }else if(result[1]==2 && result[5]==2 && result[9]==2)
        {
            aux=2;
        }else if(result[2]==2 && result[5]==2 && result[8]==2)
        {
            aux=2;
        }else if(result[3]==2 && result[5]==2 && result[7]==2)
        {
            aux=2;
        }else if(result[3]==2 && result[6]==2 && result[9]==2)
        {
            aux=2;
        }else if(result[4]==2 && result[5]==2 && result[6]==2)
        {
            aux=2;
        }else if(result[7]==2 && result[8]==2 && result[9]==2)
        {
            aux=2;////////////////////////////////////  2
        }else
        {
            int x=0;
            for(int i=1;i<10;i++)
            {
                if(result[i]!=0)
                {
                    x++;
                }
            }
            if(x==9)
            {
                aux=6;//to set the reset flag
            }else{
                aux=0;
            }
        }

        return aux;
    }

    private  void gameOver()
    {
        Context context = getApplicationContext();

        int duration = Toast.LENGTH_SHORT;
        CharSequence text="";
        Toast toast;

        if(control()==1)
        {
            pointsX++;
            txtX.setText("X: "+pointsX);
            text = "'X' wins!";
            toast = Toast.makeText(context, text, duration);
            toast.show();
            //end();
            btnAgain.setEnabled(true);
        }else if(control()==2)
        {
            pointsO++;
            txtO.setText("O: "+pointsO);
            text = "'O' wins!'";
            toast = Toast.makeText(context, text, duration);
            toast.show();
            //end();
            btnAgain.setEnabled(true);
        }else if(control()==6)
        {
            text = "DRAW!!";
            toast = Toast.makeText(context, text, duration);
            toast.show();
            //end();
            btnAgain.setEnabled(true);
        }

    }
    private void end()
    {
        for(int i=0; i<10;i++)
        {
            result[i]=0;
        }

        try{Thread.sleep(700);}catch (Exception e){}

        img1.setImageResource(R.drawable.clk);
        img2.setImageResource(R.drawable.clk);
        img3.setImageResource(R.drawable.clk);
        img4.setImageResource(R.drawable.clk);
        img5.setImageResource(R.drawable.clk);
        img6.setImageResource(R.drawable.clk);
        img7.setImageResource(R.drawable.clk);
        img8.setImageResource(R.drawable.clk);
        img9.setImageResource(R.drawable.clk);
        btnAgain.setEnabled(false);
    }
}