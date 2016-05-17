package com.mobile.gui.esp.photogram.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.gui.esp.photogram.R;

public class MainActivity extends AppCompatActivity
{
    TextView info;
    ImageView iv;
    Bitmap image;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info = (TextView) findViewById(R.id.info);
        iv = (ImageView) findViewById(R.id.photo);
    }

    public void takeAPhoto(View view)
    {
        info.setText("Take_a_photo button pressed");

        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, 0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
        {
            image = (Bitmap) data.getExtras().get("data");

            Intent playgroundIntent = new Intent(this, PlaygroundActivity.class);
            playgroundIntent.putExtra("image", image);
            startActivity(playgroundIntent);
        }
    }

    public void settings(View view)
    {
        info.setText("Settings button pressed");

        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }
}
