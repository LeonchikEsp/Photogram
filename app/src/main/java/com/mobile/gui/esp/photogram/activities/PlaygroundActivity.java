package com.mobile.gui.esp.photogram.activities;

import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mobile.gui.esp.photogram.Field;
import com.mobile.gui.esp.photogram.FieldGLSurfaceView;
import com.mobile.gui.esp.photogram.MyGLSurfaceView;

public class PlaygroundActivity extends AppCompatActivity
{
    private GLSurfaceView mGLView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bitmap image=Bitmap.createBitmap(10, 10, Bitmap.Config.ALPHA_8);
        setContentView(new Field(this, image));

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