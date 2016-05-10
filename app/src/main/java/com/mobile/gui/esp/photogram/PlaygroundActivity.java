package com.mobile.gui.esp.photogram;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class PlaygroundActivity extends AppCompatActivity
{
    ImageView imageView;
    Bitmap sourceImage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground);

        imageView = (ImageView) findViewById(R.id.imageView);

        Intent intent = getIntent();
        sourceImage = intent.getParcelableExtra("image");

        sourceImage = BitmapColourToBWConverter.convert(sourceImage);

        imageView.setImageBitmap(sourceImage);
    }
}
