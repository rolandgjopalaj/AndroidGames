package com.gjopalaj.r_gj_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class DriveGame extends AppCompatActivity implements  Runnable{

    private Thread th;

    //Cars on the other side
    Queue<ImageView> queue;
    ImageView cars[];
    TextView txt;
    ImageView car;

    //VARIABLES
    private int countEl;//count of the elements(cars)
    private int time;

    int height;
    int width;

    int dif;
    int start;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        cars=new ImageView[10];
        queue= new LinkedList<ImageView>();
        countEl=0;

        th=new Thread(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive_game);

        ///////////////////////////////////////////
        //        GET SCREEN HEIGHT AND WIDTH
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        height=size.y-210;
        width=size.x-10;

        dif=width/4;
        start=width/45;

        //TO DELETE
        txt =findViewById(R.id.textView);

        //DECLARATION OF THE OBJECTS
        car = findViewById(R.id.imgCar);
        car.setX(start+(dif*2));

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
            cars[i].setX(2000);
        }
        //////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////
        //                 CAR CONTROL OVER BUTTONS
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!gameOver())
                {
                    if(car.getX()-dif>0)
                    {
                        car.setX(car.getX()-dif);
                    }
                }
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!gameOver())
                {
                    if(car.getX()<start+(dif*3)-10)
                    {
                        car.setX(car.getX()+dif);
                    }
                }
            }
        });

        th.start();
    }

    public void run()
    {
        time=0;
        while(gameOver()==false)
        {   //---------------------
            time++;
            if(time>400)
            {
                add();
                time=0;
            }
            //--cars moving
            go();
            disappear();
        }
        txt.setText("Game Over");
    }

    private void go()
    {
        if(!queue.isEmpty())
        {
            for(ImageView x : queue)
            {
                x.setY((x.getY()+1));
            }
            try {Thread.sleep(2);} catch (InterruptedException e) {}
        }
    }

    private void disappear()
    {
        int out=(int)(height-car.getHeight());
        if(!queue.isEmpty())
        {
            if(queue.peek().getY()>out)
            {
                queue.peek().setY(3000);
                queue.poll();
            }
        }
    }


    private void add()
    {
        ImageView addC;
        int addP= (int) (width/24.64)-1;
        Random r=new Random();
        int aux;
        do{ aux=r.nextInt(4); } while(aux>3 || aux<0);

        addC=cars[countEl];
        addC.setX((start+(dif*aux))+addP);
        addC.setY(0);
        queue.offer(addC);

        countEl++;
        if(countEl>=10)
        {
            countEl=0;
        }
    }

    private boolean gameOver()
    {
        boolean flag=false;

        if(!queue.isEmpty())
        {
            for(ImageView x : queue)
            {
                if(x.getX()==car.getX() && x.getY()==car.getY()-car.getHeight())
                {
                    flag=true;
                }
            }
        }
        return  flag;
    }

}
