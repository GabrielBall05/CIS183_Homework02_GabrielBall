//===========================================================================
//Name: Gabriel Ball
//Date: 09-21-2023
//Desc: RGB Color picker with ability to save colors
//===========================================================================
package com.example.hw02_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    TextView tv_j_redVal;
    TextView tv_j_redText;
    TextView tv_j_greenVal;
    TextView tv_j_greenText;
    TextView tv_j_blueVal;
    TextView tv_j_blueText;
    TextView tv_j_hex;
    TextView tv_j_hexText;
    SeekBar sb_j_red;
    SeekBar sb_j_green;
    SeekBar sb_j_blue;
    Button btn_j_saveColor;
    ListView lv_j_colors;
    String hex;
    ArrayList<ColorInfo> listOfColors;
    ColorListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_j_redVal = findViewById(R.id.tv_v_redVal);
        tv_j_redText = findViewById(R.id.tv_v_red);
        tv_j_greenVal = findViewById(R.id.tv_v_greenVal);
        tv_j_greenText = findViewById(R.id.tv_v_green);
        tv_j_blueVal = findViewById(R.id.tv_v_blueVal);
        tv_j_blueText = findViewById(R.id.tv_v_blue);
        tv_j_hex = findViewById(R.id.tv_v_hex);
        tv_j_hexText = findViewById(R.id.tv_v_hexText);
        sb_j_red = findViewById(R.id.sb_v_red);
        sb_j_green = findViewById(R.id.sb_v_green);
        sb_j_blue = findViewById(R.id.sb_v_blue);
        btn_j_saveColor = findViewById(R.id.btn_v_saveColor);
        lv_j_colors = findViewById(R.id.lv_v_colors);

        //Make new instance of the ArrayList
        listOfColors = new ArrayList<ColorInfo>();

        SaveColorOnClickListener();
        RedSeekBarOnChangeListener();
        GreenSeekBarOnChangeListener();
        BlueSeekBarOnChangeListener();
        ListViewOnSelectListener();
        FillListView();
    }


    //=====SAVE COLOR BUTTON PRESS=====
    public void SaveColorOnClickListener()
    {
        btn_j_saveColor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d("Button Press", "Save Color Button Pressed");
                //Add the color to the list
                AddColor();
                //Reset
                ResetToWhite();
                //Display the colors in the logcat
                LogColors();

                adapter.notifyDataSetChanged();
            }
        });
    }

    //=====ADDS COLOR TO LIST=====
    public void AddColor()
    {
        //Get values of the 3 seekbars to construct a new color
        int red = sb_j_red.getProgress();
        int green = sb_j_green.getProgress();
        int blue = sb_j_blue.getProgress();
        String hex = GetHexVal(red, green, blue);
        boolean dark = isDark(red, green, blue);

        //Make new color to add to list by calling overloaded constructor
        //to set the 3 values and hex value for this new object
        ColorInfo newColor = new ColorInfo(red, green, blue, hex, dark);

        //Add to list
        listOfColors.add(newColor);
    }

    //=====RED SEEK BAR CHANGED=====
    public void RedSeekBarOnChangeListener()
    {
        sb_j_red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                //Get value of the other 2 seekbars to reference
                int green = sb_j_green.getProgress();
                int blue = sb_j_blue.getProgress();

                //Check if too dark
                if (isDark(i, green, blue))
                {
                    //Change text colors to White
                    ChangeTextWhite();
                }
                else
                {
                    //Change text colors (back) to Black
                    ChangeTextBlack();
                }
                //Change corresponding text box to the value of the seekbar (i)
                tv_j_redVal.setText(Integer.toString(i));
                //Call function to set background color
                SetActivityBackgroundColor(i, green, blue);
                //Get hex value
                hex = GetHexVal(i, green, blue);
                //Show hex representation in the text box
                tv_j_hex.setText(hex);
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    //=====GREEN SEEK BAR CHANGED=====
    public void GreenSeekBarOnChangeListener()
    {
        sb_j_green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                //Get value of the other 2 seekbars to reference
                int red = sb_j_red.getProgress();
                int blue = sb_j_blue.getProgress();

                //Check if too dark
                if (isDark(red, i, blue))
                {
                    //Change text colors to White
                    ChangeTextWhite();
                }
                else
                {
                    //Change text colors (back) to Black
                    ChangeTextBlack();
                }
                //Change corresponding text box to the value of the seekbar (i)
                tv_j_greenVal.setText(Integer.toString(i));
                //Call function to set background color
                SetActivityBackgroundColor(red, i, blue);
                //Get hex value
                hex = GetHexVal(red, i, blue);
                //Show hex representation in the text box
                tv_j_hex.setText(hex);
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    //=====BLUE SEEK BAR CHANGED=====
    public void BlueSeekBarOnChangeListener()
    {
        sb_j_blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                //Get value of the other 2 seekbars to reference
                int red = sb_j_red.getProgress();
                int green = sb_j_green.getProgress();

                //Check if too dark
                if (isDark(red, green, i))
                {
                    //Change text colors to White
                    ChangeTextWhite();
                }
                else
                {
                    //Change text colors (back) to Black
                    ChangeTextBlack();
                }
                //Change corresponding text box to the value of the seekbar (i)
                tv_j_blueVal.setText(Integer.toString(i));
                //Call function to set background color
                SetActivityBackgroundColor(red, green, i);
                //Get hex value
                hex = GetHexVal(red, green, i);
                //Show hex representation in the text box
                tv_j_hex.setText(hex);
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    //=====CHANGE BACKGROUND COLOR=====
    public void SetActivityBackgroundColor(int r, int g, int b)
    {
        //Gets background
        View view = this.getWindow().getDecorView();
        //Sets background color to r, g, b
        view.setBackgroundColor(Color.rgb(r, g, b));
    }

    //=====CONVERT TO HEX AND RETURN THE STRING=====
    public String GetHexVal(int r, int g, int b)
    {
        //Returns the hex format of the color
        return String.format("#%02X%02X%02X", r, g, b);
    }

    //=====CHECK TO SEE IF COLOR IS TOO DARK OR NOT=====
    public boolean isDark(int r, int g, int b)
    {
        float luminance = Color.luminance(Color.rgb(r, g, b));
        if (luminance < 0.5)
        {
            //It is dark
            return true;
        }
        else
        {
            return false;
        }
    }

    //=====JUST LOG THE ARRAYLIST FOR TESTING=====
    public void LogColors()
    {
        for(int i = 0; i < listOfColors.size(); i++)
        {
            Log.d("Color in hex value: ", listOfColors.get(i).getHex().toString());
        }
    }

    //=====FILL THE LIST VIEW=====
    public void FillListView()
    {
        adapter = new ColorListAdapter(this, listOfColors);
        //Set listviews adapter
        lv_j_colors.setAdapter(adapter);
    }

    //=====LIST VIEW CLICk=====
    public void ListViewOnSelectListener()
    {
        lv_j_colors.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                //i is already given to me as part of this pre-built function, so that's nice
                Log.d("List View Click", "CLICKED " + listOfColors.get(i).getHex().toString());

                //Change Seekbars to appropriate values/progress
                sb_j_red.setProgress(listOfColors.get(i).getRed());
                sb_j_green.setProgress(listOfColors.get(i).getGreen());
                sb_j_blue.setProgress(listOfColors.get(i).getBlue());
                //Set text boxes to appropriate values
                tv_j_redVal.setText(Integer.toString(listOfColors.get(i).getRed()));
                tv_j_greenVal.setText(Integer.toString(listOfColors.get(i).getGreen()));
                tv_j_blueVal.setText(Integer.toString(listOfColors.get(i).getBlue()));
                tv_j_hex.setText(listOfColors.get(i).getHex());

                //Change background back to the indicated color
                SetActivityBackgroundColor(listOfColors.get(i).getRed(), listOfColors.get(i).getGreen(), listOfColors.get(i).getBlue());

                if (isDark(listOfColors.get(i).getRed(), listOfColors.get(i).getGreen(), listOfColors.get(i).getBlue()))
                {
                    ChangeTextWhite();
                }
                else
                {
                    ChangeTextBlack();
                }
            }
        });
    }

    public void ChangeTextWhite()
    {
        //Labels
        tv_j_redText.setTextColor(Color.WHITE);
        tv_j_greenText.setTextColor(Color.WHITE);
        tv_j_blueText.setTextColor(Color.WHITE);
        tv_j_hexText.setTextColor(Color.WHITE);
        //Values
        tv_j_redVal.setTextColor(Color.WHITE);
        tv_j_greenVal.setTextColor(Color.WHITE);
        tv_j_blueVal.setTextColor(Color.WHITE);
        tv_j_hex.setTextColor(Color.WHITE);
    }

    public void ChangeTextBlack()
    {
        //Labels
        tv_j_redText.setTextColor(Color.BLACK);
        tv_j_greenText.setTextColor(Color.BLACK);
        tv_j_blueText.setTextColor(Color.BLACK);
        tv_j_hexText.setTextColor(Color.BLACK);
        //Values
        tv_j_redVal.setTextColor(Color.BLACK);
        tv_j_greenVal.setTextColor(Color.BLACK);
        tv_j_blueVal.setTextColor(Color.BLACK);
        tv_j_hex.setTextColor(Color.BLACK);
    }

    public void ResetToWhite()
    {
        //Reset labels, sliders, hex, and background
        sb_j_red.setProgress(255);
        sb_j_green.setProgress(255);
        sb_j_blue.setProgress(255);
        SetActivityBackgroundColor(255, 255, 255);
        tv_j_redVal.setText("0");
        tv_j_greenVal.setText("0");
        tv_j_blueVal.setText("0");
        tv_j_hex.setText("FFFFFF");
        ChangeTextBlack();
    }
}