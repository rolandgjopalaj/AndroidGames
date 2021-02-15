package com.gjopalaj.r_gj_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

class Direction
{
    static String dir="right";
    static boolean restartFlg=false;
    static int height=690;
    static int width=690;

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

        ///////////////////////////////////////////
        //        GET SCREEN HEIGHT AND WIDTH
        WindowManager windowManager = (WindowManager) snakeMap.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        Direction.height=(size.y)-210;
        Direction.width=(size.x)-10;

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
        Button restart= new Button (this);
        restart.setText("RESTART");

        FrameLayout.LayoutParams paramsUP = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        paramsUP.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;

        paramsUP.topMargin = Direction.height-400;
        addContentView(up, paramsUP);

        FrameLayout.LayoutParams paramsDown = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        paramsDown.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;

        paramsDown.topMargin = Direction.height-200;
        addContentView(down, paramsDown);

        FrameLayout.LayoutParams paramsLeft = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        paramsLeft.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;

        paramsLeft.topMargin = Direction.height-300;
        paramsLeft.rightMargin =200;
        addContentView(left, paramsLeft);

        FrameLayout.LayoutParams paramsRight = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        paramsRight.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;

        paramsRight.topMargin = Direction.height-300;
        paramsRight.leftMargin = 200;
        addContentView(right, paramsRight);

        FrameLayout.LayoutParams paramsRestart = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        paramsRestart.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;

        paramsRestart.topMargin = Direction.height-100;
        paramsRestart.leftMargin = 250;
        addContentView(restart, paramsRestart);
        ////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////
        //   DIRECTION IN BASE OF BUTTON LISTENERS
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Direction.dir!="down")
                {
                    Direction.dir="up";
                }
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Direction.dir!="up")
                {
                    Direction.dir="down";
                }
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Direction.dir!="right")
                {
                    Direction.dir="left";
                }
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Direction.dir!="left")
                {
                    Direction.dir="right";
                }
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Direction.restartFlg=true;
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
    boolean gameover=false;
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
            if(Direction.restartFlg==true)
            {
                reset();
            }
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
        //canvas.drawColor(Color.rgb(139,69,19));
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.map),0,0,null);

        for(int i=0;i<10;i++)
        {
            canvas.drawLine(i+0,i+0,i+0,i+Direction.width, paint);
            canvas.drawLine(i+0,i+0,i+Direction.width,i+0, paint);
            canvas.drawLine(i+0,i+Direction.width,i+Direction.width,i+Direction.width, paint);
            canvas.drawLine(i+Direction.width,i+0,i+Direction.width,i+Direction.width, paint);
        }
        canvas.drawCircle(xFruit, yFruit, 10, paint);
        paint.setColor(Color.GREEN);
        for(int i=0;i<snakeLen;i++)
        {
            canvas.drawCircle(x[i], y[i], 10, paint);
        }
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        canvas.drawText("Score: "+score, 275, 50, paint);

    }

    protected void reDraw() {
        this.invalidate();
    }

    /////////////////////////////////////
    //     GAME OVER CONTROL
    private boolean goControl()
    {
        int map=Direction.width-10;
        if(x[0]>=map || x[0]<=10 || y[0]>=map || y[0]<=10)
        {
            Direction.dir="null";
            gameover=true;
        }
        for(int i=2;i<snakeLen;i++)
        {
            if(x[0]==x[i] && y[0]==y[i])
            {
                Direction.dir="null";
                gameover=true;

            }
        }
        return gameover;
    }

    /////////////////////////////////////
    //     FRUIT CONTROL
    private void fruitControl()
    {
        int aux=(Direction.width/10)-3;
        Random rand=new Random();
        if(x[0]==xFruit && y[0]==yFruit)
        {
            xFruit=(rand.nextInt(aux)+2)*10;
            yFruit=(rand.nextInt(aux)+2)*10;
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
        switch(Direction.dir)
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
    private void reset()
    {
        snakeLen = 5;
        score=0;
        x[0] =20;
        y[0] =20;

        xFruit = 200;
        yFruit = 200;
        gameover=false;
        Direction.dir="right";
        Direction.restartFlg=false;
    }
}

