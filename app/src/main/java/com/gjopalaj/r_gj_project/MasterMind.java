package com.gjopalaj.r_gj_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MasterMind extends AppCompatActivity {
    ///////////////////////////////
    // VARIABLES
    int n = 0;
    int nProve = 0;
    boolean end;
    int[] numRandom = new int[4];
    int[] numInput = new int[4];
    char[] vettSegno =new char[4];
    int kot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_mind);

        ////////////////////////////////////////////////
        // TEXT DECLARATION
        TextView txtShow=findViewById(R.id.txtShow);
        TextView txtKot=findViewById(R.id.txtKot);
        TextView txtHelp = findViewById(R.id.txtHelp);

        ////////////////////////////////////////////////
        //    BUTTONS DECLARATION
        Button btn0 =findViewById(R.id.btn0);
        Button btn1 =findViewById(R.id.btn1);
        Button btn2 =findViewById(R.id.btn2);
        Button btn3 =findViewById(R.id.btn3);
        Button btn4 =findViewById(R.id.btn4);
        Button btn5 =findViewById(R.id.btn5);
        Button btn6 =findViewById(R.id.btn6);
        Button btn7 =findViewById(R.id.btn7);
        Button btn8 =findViewById(R.id.btn8);
        Button btn9 =findViewById(R.id.btn9);
        /////////////////////////////////////////////////
        //    GENERATES A 4 DIGITS NUMBER
        Carica();
        txtKot.setText(numRandom[0]+""+numRandom[1]+""+numRandom[2]+""+numRandom[3]);

        ////////////////////////////////////////////////
        //    BUTTON LISTENERS
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtShow.setText(txtShow.getText()+"0");
                numInput[n] = 0;
                n++;
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtShow.setText(txtShow.getText()+"1");
                numInput[n] = 1;
                n++;
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtShow.setText(txtShow.getText()+"2");
                numInput[n] = 2;
                n++;
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtShow.setText(txtShow.getText()+"3");
                numInput[n] = 3;
                n++;
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtShow.setText(txtShow.getText()+"4");
                numInput[n] = 4;
                n++;
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtShow.setText(txtShow.getText()+"5");
                numInput[n] = 5;
                n++;
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtShow.setText(txtShow.getText()+"6");
                numInput[n] = 6;
                n++;
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtShow.setText(txtShow.getText()+"7");
                numInput[n] = 7;
                n++;
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtShow.setText(txtShow.getText()+"8");
                numInput[n] = 8;
                n++;
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtShow.setText(txtShow.getText()+"9");
                numInput[n] = 9;
                n++;
            }
        });

        ///////////////////////////////////////////////////////////////////////
        //    THE TRY BUTTON (DOES THE CONTROLL OF THE NUMBER FROM THE INPUT)
        Button btnTry=findViewById(R.id.btnTry);
        btnTry.setEnabled(false);
        btnTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < 4; i++)
                    vettSegno[i] = '_';
                int j = 0;
                for (int i = 0; i < n; i++)
                {
                    if (numInput[i] == numRandom[i])
                    {
                        vettSegno[i] = '\u263a';
                        j++;
                    }
                    ControlloPerTuttiINumeri();
                }
                if (j == 4)
                {
                    Toast.makeText(getApplicationContext(), "Hai vinto!",
                            Toast.LENGTH_LONG).show();
                    txtKot.setText("The number was "+numRandom[0]+""+numRandom[1]+""+numRandom[2]+""+numRandom[3]);

                }

                Toast.makeText(getApplicationContext(), vettSegno[0]+" "+vettSegno[1]+" "+vettSegno[2]+" "+vettSegno[3],
                        Toast.LENGTH_LONG).show();
                txtHelp.setText(txtHelp.getText()+"\n"+txtShow.getText()+"  "+vettSegno[0]+" "+vettSegno[1]+" "+vettSegno[2]+" "+vettSegno[3]);

                n = 0;
                txtShow.setText("");
                nProve++;
                if(nProve==8)
                {
                    Toast.makeText(getApplicationContext(), "Hai perso!",
                            Toast.LENGTH_LONG).show();
                    txtKot.setText("The number was "+numRandom[0]+""+numRandom[1]+""+numRandom[2]+""+numRandom[3]);
                }
                btnTry.setEnabled(false);
                btn0.setEnabled(true);
                btn1.setEnabled(true);
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                btn4.setEnabled(true);
                btn5.setEnabled(true);
                btn6.setEnabled(true);
                btn7.setEnabled(true);
                btn8.setEnabled(true);
                btn9.setEnabled(true);
            }
        });

        ////////////////////////////////////////////////////////
        //    THE NEW GAME BUTTON
        Button btnNewGame=findViewById(R.id.btnNewGame);
        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Carica();
                txtKot.setText(numRandom[0]+""+numRandom[1]+""+numRandom[2]+""+numRandom[3]);
                txtHelp.setText("");
                txtShow.setText("");
                n=0;
                nProve=0;
                
                btnTry.setEnabled(false);
                btn0.setEnabled(true);
                btn1.setEnabled(true);
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                btn4.setEnabled(true);
                btn5.setEnabled(true);
                btn6.setEnabled(true);
                btn7.setEnabled(true);
                btn8.setEnabled(true);
                btn9.setEnabled(true);

            }
        });
        ////////////////////////////////////////////////////////////////////////////////////
        //  THE LISTENER DURING THE INPUT (ENABLES THE TRUE BUTTON AFTER THE COMPLETE INPUT
        txtShow.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(n>=3)
                {
                    btnTry.setEnabled(true);
                    btn0.setEnabled(false);
                    btn1.setEnabled(false);
                    btn2.setEnabled(false);
                    btn3.setEnabled(false);
                    btn4.setEnabled(false);
                    btn5.setEnabled(false);
                    btn6.setEnabled(false);
                    btn7.setEnabled(false);
                    btn8.setEnabled(false);
                    btn9.setEnabled(false);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }


    private void Carica()//GENERATES A 4 DIGITS NUMBER
    {
        Random R = new Random();
        for(int i=0;i<4;i++)
        {
            numRandom[i] = R.nextInt(9);
        }

    }

    private void ControlloPerTuttiINumeri()
    {
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4; j++)
            {
                if (numInput[i] == numRandom[j]&&vettSegno[i]!= '\u263a')
                    vettSegno[i] = '@';
            }
        }
    }
}
