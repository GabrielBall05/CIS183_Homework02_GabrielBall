package com.example.hw02_program01;

public class ColorInfo
{
    private int red;
    private int green;
    private int blue;
    private String hex;
    private boolean dark;

    //Constructor
    public ColorInfo()
    {

    }

    //Overloaded Constructor
    public ColorInfo(int r, int g, int b, String h, boolean d)
    {
        red = r;
        green = g;
        blue = b;
        hex = h;
        dark = d;
    }

    //=====Getters=====
    public int getRed()
    {
        return red;
    }

    public int getGreen()
    {
        return green;
    }

    public int getBlue()
    {
        return blue;
    }

    public String getHex()
    {
        return hex;
    }

    public boolean getDark()
    {
        return dark;
    }
    //=====End Getters=====

    //=====Setters=====
    public void setRed(int r)
    {
        red = r;
    }

    public void setGreen(int g)
    {
        green = g;
    }

    public void setBlue(int b)
    {
        blue = b;
    }

    public void setHex(String h)
    {
        hex = h;
    }

    public void setDark(boolean d)
    {
        dark = d;
    }
    //=====End Setters=====
}
