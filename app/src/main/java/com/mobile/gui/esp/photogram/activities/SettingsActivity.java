package com.mobile.gui.esp.photogram.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mobile.gui.esp.photogram.R;

public class SettingsActivity extends AppCompatActivity
{
    Button button;
    int iterator = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        button = (Button) findViewById(R.id.button);
    }
    public void buttonPressed(View view)
    {
        button.setText("Pressed " + ++iterator + " times");
    }
}
