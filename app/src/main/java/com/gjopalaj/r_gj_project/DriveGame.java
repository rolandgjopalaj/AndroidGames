package com.gjopalaj.r_gj_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class DriveGame extends AppCompatActivity implements  Runnable{

    private Thread th;

    //Cars on the other side
    LinkedList<ImageView> fifo = new LinkedList<ImageView>();
    ImageView cars[];
    TextView txt;
    ImageView none;

    //VARIABLES
    private int countEl;//count of the elements(cars)
    private int time;
    private int next;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        cars=new ImageView[10];
        countEl=0;
        next=0;

        th=new Thread(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive_game);

        //TO DELETE
        txt =findViewById(R.id.textView);
        //DECLARATION OF THE OBJECTS
        ImageView car = findViewById(R.id.imgCar);
        Button left=findViewById(R.id.btnLeft);
        Button right=findViewById(R.id.btnRight);

        //////////////////////////////////////////////////
        cars[0]=findViewById(R.id.imgc1);
        cars[1]=findViewById(R.id.imgc2);
        cars[2]=findViewById(R.id.imgc3);
        cars[3]=findViewById(R.id.imgc4);
        cars[4]=findViewById(R.id.imgc5);
        cars[5]=findViewById(R.id.imgc6);
        cars[6]=findViewById(R.id.imgc7);
        cars[7]=findViewById(R.id.imgc8);
        cars[8]=findViewById(R.id.imgc9);
        cars[9]=findViewById(R.id.imgc10);

        cars[0].setImageResource(R.drawable.c1);
        cars[1].setImageResource(R.drawable.c2);
        cars[2].setImageResource(R.drawable.c3);
        cars[3].setImageResource(R.drawable.c4);
        cars[4].setImageResource(R.drawable.c1);
        cars[5].setImageResource(R.drawable.c2);
        cars[6].setImageResource(R.drawable.c3);
        cars[7].setImageResource(R.drawable.c4);
        cars[8].setImageResource(R.drawable.c1);
        cars[9].setImageResource(R.drawable.c2);

        for(int i=0;i<10;i++)
        {
            cars[i].setX(1000);
        }

        /*for(ImageView x : fifo)
        {
            int aux=0; 
            x=cars[aux];
            aux++;
        }*/

        //////////////////////////////////////////////////////////////
        //CAR CONTROL OVER BUTTONS
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(car.getX()>16)
                {
                    car.setX(car.getX()-180);
                }
                txt.setText(car.getX()+" "+car.getY());
                //add();
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(car.getX()<556)
                {
                    car.setX(car.getX()+180);
                }
                txt.setText(car.getX()+" "+car.getY());
            }
        });

        th.start();
    }

    public void run()
    {
        time=0;
        while (true)
        {   //---------------------
            time++;
            txt.setText(""+countEl);
            if(time>400)
            {
                if(countEl<8)
                {
                    add();
                }
                time=0;
            }
            //-----------------------

            //--cars moving
            go();
            disappear();

        }
    }

    private void go()
    {
        if(countEl>0)
        {
            for(int i=0;i<countEl;i++)
            {
                cars[i].setY(cars[i].getY()+1);
            }
            try {Thread.sleep(5);} catch (InterruptedException e) {}
        }
    }

    private void disappear()
    {
        if(countEl>0)
        {
            int index=0;
            if(cars[index].getY()>1000)
            {
                cars[index].setY(2000);
                for(int j=0;j<countEl-1;j++)
                {
                    cars[j]=cars[j+1];
                }
                countEl--;
            }
        }
    }
    /*
    for(int i=0;i<countEl;i++)
        {
            if(cars[i].getY()>1000)
            {
                cars[i].setY(2000);
                for(int j=0;j<countEl;j++)
                {
                    cars[j]=cars[j+1];
                }
                countEl--;
            }
        }
     */


    private void add()
    {
        Random r=new Random();
        int aux;
        do{ aux=r.nextInt(4); } while(aux>3 || aux<0);

        switch(aux)
        {
            case 0:
                cars[countEl].setX(556);
                break;
            case 1:
                cars[countEl].setX(376);
                break;
            case 2:
                cars[countEl].setX(196);
                break;
            case 3:
                cars[countEl].setX(16);
                break;
        }
        cars[countEl].setY(0);

        countEl++;
    }

}

/*
switch (countEl)
        {
            case 0: cars[countEl]=findViewById(R.id.imgc1);
                break;
            case 1: cars[countEl]=findViewById(R.id.imgc2);
                break;
            case 2: cars[countEl]=findViewById(R.id.imgc3);
                break;
            case 3: cars[countEl]=findViewById(R.id.imgc4);
                break;
            case 4: cars[countEl]=findViewById(R.id.imgc5);
                break;
            case 5: cars[countEl]=findViewById(R.id.imgc6);
                break;
            case 6: cars[countEl]=findViewById(R.id.imgc7);
                break;
            case 7: cars[countEl]=findViewById(R.id.imgc8);
                break;
            case 8: cars[countEl]=findViewById(R.id.imgc9);
                break;
            case 9: cars[countEl]=findViewById(R.id.imgc10);
                break;
        }

        switch(aux)
        {
            case 0:
                cars[countEl].setImageResource(R.drawable.c1);
                cars[countEl].setX(556);
                break;
            case 1:
                cars[countEl].setImageResource(R.drawable.c2);
                cars[countEl].setX(376);
                break;
            case 2:
                cars[countEl].setImageResource(R.drawable.c3);
                cars[countEl].setX(196);
                break;
            case 3:
                cars[countEl].setImageResource(R.drawable.c4);
                cars[countEl].setX(16);
                break;
        }
        cars[countEl].setY(0);

        countEl++;
 */