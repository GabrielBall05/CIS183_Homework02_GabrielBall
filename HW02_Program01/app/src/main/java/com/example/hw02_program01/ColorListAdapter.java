package com.example.hw02_program01;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ColorListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<ColorInfo> listOfColors;

    //Overloaded Constructor
    public ColorListAdapter(Context c, ArrayList<ColorInfo> ls)
    {
        //Passed to the class from MainActivity.java
        context = c;
        listOfColors = ls;
    }

    @Override
    public int getCount()
    {
        //Return size of the ArrayList
        return listOfColors.size();
    }

    @Override
    public Object getItem(int i)
    {
        //Get the object (color) at that position i in the ArrayList
        return listOfColors.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        //Get the position
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (view == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.custom_cell, null);
        }

        //Find GUI elements in my custom_cell
        TextView tv_j_cc_redText = view.findViewById(R.id.tv_v_customCell_redText);
        TextView tv_j_cc_greenText = view.findViewById(R.id.tv_v_customCell_greenText);
        TextView tv_j_cc_blueText = view.findViewById(R.id.tv_v_customCell_blueText);
        TextView tv_j_cc_hexText = view.findViewById(R.id.tv_v_customCell_hexText);

        TextView tv_j_cc_red = view.findViewById(R.id.tv_v_customCell_red);
        TextView tv_j_cc_green = view.findViewById(R.id.tv_v_customCell_green);
        TextView tv_j_cc_blue = view.findViewById(R.id.tv_v_customCell_blue);
        TextView tv_j_cc_hex = view.findViewById(R.id.tv_v_customCell_hex);

        //Get the color at i
        ColorInfo color = listOfColors.get(i);

        //Set the text for the cell
        tv_j_cc_red.setText(Integer.toString(color.getRed()));
        tv_j_cc_green.setText(Integer.toString(color.getGreen()));
        tv_j_cc_blue.setText(Integer.toString(color.getBlue()));
        tv_j_cc_hex.setText(color.getHex());

        //Change background of cell
        view.setBackgroundColor(Color.rgb(color.getRed(), color.getGreen(), color.getBlue()));

        //Change text color of all text boxes to either white or black depending on darkness
        if (color.getDark())
        {
            Log.d("Dark", "Dark");
            //LABELS
            tv_j_cc_redText.setTextColor(Color.WHITE);
            tv_j_cc_greenText.setTextColor(Color.WHITE);
            tv_j_cc_blueText.setTextColor(Color.WHITE);
            tv_j_cc_hexText.setTextColor(Color.WHITE);
            //VALUES
            tv_j_cc_red.setTextColor(Color.WHITE);
            tv_j_cc_green.setTextColor(Color.WHITE);
            tv_j_cc_blue.setTextColor(Color.WHITE);
            tv_j_cc_hex.setTextColor(Color.WHITE);
        }
        else
        {
            Log.d("Not Dark", "Not Dark");
            //LABELS
            tv_j_cc_redText.setTextColor(Color.BLACK);
            tv_j_cc_greenText.setTextColor(Color.BLACK);
            tv_j_cc_blueText.setTextColor(Color.BLACK);
            tv_j_cc_hexText.setTextColor(Color.BLACK);
            //VALUES
            tv_j_cc_red.setTextColor(Color.BLACK);
            tv_j_cc_green.setTextColor(Color.BLACK);
            tv_j_cc_blue.setTextColor(Color.BLACK);
            tv_j_cc_hex.setTextColor(Color.BLACK);
        }

        return view;
    }
}