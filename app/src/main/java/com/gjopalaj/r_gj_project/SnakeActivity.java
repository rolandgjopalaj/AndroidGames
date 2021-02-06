package com.gjopalaj.r_gj_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Random;

class Direstion
{
    static String dir="right";
}

public class SnakeActivity extends AppCompatActivity {

    private Object Canvas;
    CustomViewSnake snakeMap;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        snakeMap=new CustomViewSnake(this);
        setContentView(snakeMap);


        ////////////////////////////////////////////////////////////////////////////////////////////
        //     SET OF THE BUTTONS WITHOUT LAYOUT
        Button up= new Button (this);
        up.setText("UP");
        Button down= new Button (this);
        down.setText("DOWN");
        Button left= new Button (this);
        left.setText("LEFT");
        Button right= new Button (this);
        right.setText("RIGHT");

        FrameLayout.LayoutParams paramsUP = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        paramsUP.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;

        paramsUP.topMargin = 850;
        addContentView(up, paramsUP);

        FrameLayout.LayoutParams paramsDown = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        paramsDown.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;

        paramsDown.topMargin = 1050;
        addContentView(down, paramsDown);

        FrameLayout.LayoutParams paramsLeft = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        paramsLeft.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;

        paramsLeft.topMargin = 950;
        paramsLeft.rightMargin =200;
        addContentView(left, paramsLeft);

        FrameLayout.LayoutParams paramsRight = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        paramsRight.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;

        paramsRight.topMargin = 950;
        paramsRight.leftMargin = 200;
        addContentView(right, paramsRight);
        ////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////
        //   DIRECTION IN BASE OF BUTTON LISTENERS
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Direstion.dir!="down")
                {
                    Direstion.dir="up";
                }
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Direstion.dir!="up")
                {
                    Direstion.dir="down";
                }
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Direstion.dir!="right")
                {
                    Direstion.dir="left";
                }
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Direstion.dir!="left")
                {
                    Direstion.dir="right";
                }
            }
        });

    }
    private Paint paint = new Paint();

    private void init() {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1f);
    }


}

///////////////////////////////////////////////////////////////////////////////////////////////////
class CustomViewSnake extends View implements Runnable{

    private Paint paint;
    private Thread th;

    private int score=0;
    //////////////
    // Snake (x,y)
    private int x[] =new  int[1000];
    private int y[] =new int[1000];

    ///////////////
    //  FRUIT (x,y)
    private int xFruit = 200;
    private int yFruit = 200;
    private int snakeLen = 5;//the length of the snake

    @SuppressLint("ResourceType")
    public CustomViewSnake(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.BLACK);

        x[0] =20;
        y[0] =20;
        th=new Thread(this);
        th.start();
    }

    public void run()
    {
        while (true) {
            if (goControl() != true)
            {
                headMove();
                fruitControl();
                bodyMove();
                reDraw();
                try{Thread.sleep(100);}catch (Exception x){}
            }
        }
    }


    @SuppressLint("ResourceType")
    @Override
    protected void onDraw(Canvas canvas) {
        ////////////////////////////////////////
        // DRAW THE MAP
        paint.setColor(Color.RED);
        canvas.drawColor(Color.WHITE);

        for(int i=0;i<10;i++)
        {
            canvas.drawLine(i+0,i+0,i+0,i+700, paint);
            canvas.drawLine(i+0,i+0,i+700,i+0, paint);
            canvas.drawLine(i+0,i+700,i+700,i+700, paint);
            canvas.drawLine(i+700,i+0,i+700,i+700, paint);
        }
        canvas.drawCircle(xFruit, yFruit, 10, paint);
        paint.setColor(Color.GREEN);
        for(int i=0;i<snakeLen;i++)
        {
            canvas.drawCircle(x[i], y[i], 10, paint);
        }


    }

    protected void reDraw() {
        this.invalidate();
    }

    /////////////////////////////////////
    //     GAME OVER CONTROL
    private boolean goControl()
    {
        boolean gameover=false;

        if(x[0]>=690 || x[0]<=10 || y[0]>=690 || y[0]<=10)
        {
            Direstion.dir="null";
            gameover=true;
        }
        for(int i=2;i<snakeLen;i++)
        {
            if(x[0]==x[i] && y[0]==y[i])
            {
                Direstion.dir="null";
                gameover=true;

            }
        }
        return gameover;
    }

    /////////////////////////////////////
    //     FRUIT CONTROL
    private void fruitControl()
    {
        Random rand=new Random();
        if(x[0]==xFruit && y[0]==yFruit)
        {
            xFruit=(rand.nextInt(67)+2)*10;
            yFruit=(rand.nextInt(67)+2)*10;
            snakeLen++;
            score++;
        }
    }

    /////////////////////////////////////
    //      THE BODY MOVE
    private void bodyMove()
    {
        int auxAX =x[0];
        int auxAY =y[0];

        for(int i=0;i<snakeLen-1;i++)
        {
            int auxBX = x[i+1];
            int auxBY = y[i+1];

            x[i+1] = auxAX;
            y[i+1] = auxAY;

            auxAX = auxBX;
            auxAY = auxBY;
        }
    }

    /////////////////////////////////////
    //      The HEAD MOVE
    private void headMove()
    {
        switch(Direstion.dir)
        {
            case "up": y[0]-=10;
                break;
            case "down": y[0]+=10;
                break;
            case "left": x[0]-=10;
                break;
            case "right": x[0]+=10;
                break;
            case "null":;
                break;
        }
    }
}

