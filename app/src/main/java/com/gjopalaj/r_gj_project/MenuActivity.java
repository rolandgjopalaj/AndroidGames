package com.gjopalaj.r_gj_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MenuActivity extends AppCompatActivity {

    TextView txtHello;
    TextView txtDB;
    Button btnGo;
    EditText txtNickname;

    GoogleSignInAccount acc;
    GoogleSignInClient mGoogleSignInClient;

    FirebaseDatabase database;
    DatabaseReference dbRef;

    ///////////////////////////
    //  VARIABLES
    User user;
    int points;
    boolean exist=false;
    boolean same=false;
    boolean show=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        txtHello=findViewById(R.id.txtHello);
        txtDB=findViewById(R.id.txtDB);
        btnGo=findViewById(R.id.btnNickname);
        txtNickname=findViewById(R.id.txtNickname);
        acc =GoogleSignIn.getLastSignedInAccount(this);
        database=FirebaseDatabase.getInstance();
        dbRef=database.getReference("users");
        points=0;

        //////////////////////////////////////////////////
        //
        txtNickname.setText("");
        btnGo.setEnabled(false); 
        txtNickname.setEnabled(false);

        String id=acc.getId();// gets the id from the sign in
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot aux : snapshot.getChildren())
                {
                    if(aux.child("id").getValue().toString().equals(id))
                    {//it controls if  the user is already registered or not
                        exist=true;
                        txtHello.setText("Welcome back ");
                        getFromDB();// get the data from database
                    }
                    if(!exist)
                    {
                        txtNickname.setEnabled(true);
                        txtHello.setText("So you are new? Choose a Nickname!!");
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        txtNickname.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!txtNickname.getText().toString().equals(""))
                {
                    btnGo.setEnabled(true);
                }
                    same = false;
            }
        });

        //////////////////////////////////////////////////
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot aux : snapshot.getChildren())//for each
                        {
                            if(aux.child("nickname").getValue().toString().equals(txtNickname.getText().toString()) && show)
                            {// does the control if the nickname is already picked by someone else
                                txtHello.setText("This nickname does already exists!!");
                                same=true;
                            }
                            if(!same)
                            {//if not it will get the data from the input and save it on the database
                                user=new User(txtNickname.getText().toString(),acc.getEmail(),acc.getId(),points,"newUser");
                                dbRef.child(user.getId()).setValue(user);
                                txtHello.setText("You now are registered as "+txtNickname.getText().toString());
                                getFromDB();
                                show=false;
                                btnGo.setEnabled(false);
                                txtNickname.setEnabled(false);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
            }
        });

        Button btnGames=findViewById(R.id.btnGames);
        btnGames.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                points+=10;
                editDB();
                Intent gamesIntent=new Intent(MenuActivity.this, GamesActivity.class);
                startActivity(gamesIntent);
            }
        });

        Button btnSignOut=findViewById(R.id.btnSignOut);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleSignInClient.signOut();
                Intent mainIntent=new Intent(MenuActivity.this,MainActivity.class);
                startActivity(mainIntent);
            }
        });

        Button btnContact=findViewById(R.id.btnContact);
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:"+"mail.javascript.server@gmail.com"));
                startActivity(mailIntent);
            }
        });

    }

    private void getFromDB()
    {
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot aux : snapshot.getChildren())
                {
                    if(aux.child("id").getValue().toString().equals(acc.getId()))
                    {
                        txtDB.setText("\n             USER" +
                                "\n  -> Nickname: "+aux.child("nickname").getValue().toString()+
                                "\n\n  -> Email: "+acc.getEmail()+
                                "\n\n  -> Points: "+aux.child("points").getValue().toString()+
                                "\n\n  -> Rank: "+aux.child("rank").getValue().toString());
                        points=Integer.parseInt(aux.child("points").getValue().toString());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    private void editDB()
    {
        FirebaseDatabase.getInstance().getReference().child("users").child(acc.getId()).child("points").setValue(points);

        String rank="";
        if(points>0&&points<1000)
            rank="newUser";
        else if(points>=1000&&points<5000)
            rank="pro";
        else
            rank="legend";

        FirebaseDatabase.getInstance().getReference().child("users").child(acc.getId()).child("rank").setValue(rank);
    }
}