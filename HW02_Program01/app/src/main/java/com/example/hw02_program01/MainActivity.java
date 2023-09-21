package com.example.hw02_program01;

import androidx.appcompat.app.AppCompatActivity;

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
    }

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



}