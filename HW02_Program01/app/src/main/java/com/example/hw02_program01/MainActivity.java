package com.example.hw02_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    TextView tv_j_redVal;
    TextView tv_j_greenVal;
    TextView tv_j_blueVal;
    TextView tv_j_hex;
    SeekBar sb_j_red;
    SeekBar sb_j_green;
    SeekBar sb_j_blue;
    Button btn_j_saveColor;
    ListView lv_j_colors;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_j_redVal = findViewById(R.id.tv_v_redVal);
        tv_j_greenVal = findViewById(R.id.tv_v_greenVal);
        tv_j_blueVal = findViewById(R.id.tv_v_blueVal);
        tv_j_hex = findViewById(R.id.tv_v_hex);
        sb_j_red = findViewById(R.id.sb_v_red);
        sb_j_green = findViewById(R.id.sb_v_green);
        sb_j_blue = findViewById(R.id.sb_v_blue);
        btn_j_saveColor = findViewById(R.id.btn_v_saveColor);


        SaveColorOnClickListener();
        RedSeekBarOnChangeListener();
        GreenSeekBarOnChangeListener();
        BlueSeekBarOnChangeListener();
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

            }
        });
    }

    //=====RED SEEK BAR CHANGED=====
    public void RedSeekBarOnChangeListener()
    {
        sb_j_red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                int green = sb_j_green.getProgress();
                int blue = sb_j_blue.getProgress();
                //Change corresponding text box to the value of the seekbar (i)
                tv_j_redVal.setText(Integer.toString(i));
                SetActivityBackgroundColor(i, green, blue);
                GetHexVal(i, green, blue);


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
                int red = sb_j_red.getProgress();
                int blue = sb_j_blue.getProgress();
                //Change corresponding text box to the value of the seekbar (i)
                tv_j_greenVal.setText(Integer.toString(i));
                SetActivityBackgroundColor(red, i, blue);
                GetHexVal(red, i, blue);

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
                int red = sb_j_red.getProgress();
                int green = sb_j_green.getProgress();
                //Change corresponding text box to the value of the seekbar (i)
                tv_j_blueVal.setText(Integer.toString(i));
                SetActivityBackgroundColor(red, green, i);
                GetHexVal(red, green, i);


            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    //=====CHANGE BACKGROUND COLOR=====
    public void SetActivityBackgroundColor(int r, int g, int b)
    {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.rgb(r, g, b));
    }

    public void GetHexVal(int r, int g, int b)
    {
        String hex = String.format("#%02X%02X%02X", r, g, b);
        tv_j_hex.setText(hex);
    }


}