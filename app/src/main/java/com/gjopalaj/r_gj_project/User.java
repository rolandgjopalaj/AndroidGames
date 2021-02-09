package com.gjopalaj.r_gj_project;

import android.net.Uri;

public class User {

    private String nickname;
    private String email;
    private String id;
    private int points;
    private String rank;

    public User(String n, String e, String i, int po, String r)
    {
        nickname=n;
        email=e;
        id=i;
        points=po;
        rank=r;
    }

    public String getNickname()
    {
        return nickname;
    }
    public String getEmail()
    {
        return email;
    }
    public String getId()
    {
        return id;
    }
    public int getPoints()
    {
        return points;
    }
    public String getRank(){return rank;}
}
