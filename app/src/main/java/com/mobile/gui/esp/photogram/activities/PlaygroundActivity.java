package com.mobile.gui.esp.photogram.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mobile.gui.esp.photogram.BitmapColourToBWConverter;
import com.mobile.gui.esp.photogram.Field;
import com.mobile.gui.esp.photogram.FieldGLSurfaceView;
import com.mobile.gui.esp.photogram.MyGLSurfaceView;

public class PlaygroundActivity extends AppCompatActivity
{
    private GLSurfaceView mGLView;
    Bitmap sourceImage;
    Bitmap BWImage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        sourceImage = intent.getParcelableExtra("image");
        BWImage = BitmapColourToBWConverter.convert(sourceImage);
        float size=10;
        Bitmap nonogram=Bitmap.createBitmap((int)(BWImage.getWidth()/size), (int)(BWImage.getHeight()/size), Bitmap.Config.ARGB_8888);
        Bitmap test=Bitmap.createBitmap(135, 240, Bitmap.Config.ARGB_8888);
        for(int i=0; i<BWImage.getHeight()-size; i+=size)
            for(int j=0; j<BWImage.getWidth()-size; j+=size)
            {
                long black_count = 0;
                for (int n = 0; n < size; n++)
                    for (int m = 0; m < size; m++)
                    {
                        if (BWImage.getPixel(m+j, n+i) == Color.BLACK)
                            black_count++;
                    }
                if(black_count>size*size/2)
                    nonogram.setPixel((int)(j/size), (int)(i/size), Color.BLACK);
            }
        setContentView(new Field(this, nonogram));

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity

    }

    /*@Override
    protected void onPause() {
        super.onPause();
        // The following call pauses the rendering thread.
        // If your OpenGL application is memory intensive,
        // you should consider de-allocating objects that
        // consume significant memory here.
        mGLView.onPause();
    }*/

    /*@Override
    protected void onResume() {
        super.onResume();
        // The following call resumes a paused rendering thread.
        // If you de-allocated graphic objects for onPause()
        // this is a good place to re-allocate them.
        mGLView.onResume();
    }*/
        /*setContentView(R.layout.activity_playground);

        imageView = (ImageView) findViewById(R.id.imageView);

        Intent intent = getIntent();
        sourceImage = intent.getParcelableExtra("image");

        sourceImage = BitmapColourToBWConverter.convert(sourceImage);

        imageView.setImageBitmap(sourceImage);*/

    /*protected void onPause()
    { super.onPause();
        if (rendererSet)
        {
            glSurfaceView.onPause();
        }
    }
    @Override
    protected void onResume()
    { super.onResume();
        if (rendererSet)
        {
            glSurfaceView.onResume();
        }
    }*/
}