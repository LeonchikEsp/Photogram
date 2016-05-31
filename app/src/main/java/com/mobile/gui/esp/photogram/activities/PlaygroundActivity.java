package com.mobile.gui.esp.photogram.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.mobile.gui.esp.photogram.BitmapColourToBWConverter;
import com.mobile.gui.esp.photogram.Field;
import com.mobile.gui.esp.photogram.FieldGLSurfaceView;
import com.mobile.gui.esp.photogram.MyGLSurfaceView;

public class PlaygroundActivity extends AppCompatActivity implements View.OnTouchListener
{
    private GLSurfaceView mGLView;
    Bitmap sourceImage;
    Bitmap BWImage;
    Field field;
    Bitmap nonogram;
    Bitmap user_image;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        sourceImage = intent.getParcelableExtra("image");
        BWImage = BitmapColourToBWConverter.convert(sourceImage);
        float size=10;
        nonogram=Bitmap.createBitmap((int)(BWImage.getWidth()/size), (int)(BWImage.getHeight()/size), Bitmap.Config.ARGB_8888);
        for(int i=0; i<=BWImage.getHeight()-size; i+=size)
            for(int j=0; j<=BWImage.getWidth()-size; j+=size)
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
        user_image=Bitmap.createBitmap(nonogram.getWidth(), nonogram.getHeight(), Bitmap.Config.ARGB_8888);
        for (int i = 0; i < nonogram.getHeight(); i++)
            for (int j = 0; j < nonogram.getWidth(); j++)
            {
                if (nonogram.getPixel(j, i) == Color.BLACK)
                    user_image.setPixel(j, i, Color.BLACK);
            }
        field=new Field(this, nonogram, "nonogram");
        field.setOnTouchListener(this);
        setContentView(field);
        for (int i = 0; i < user_image.getHeight(); i++)//поле
            for (int j = 0; j < user_image.getWidth(); j++)
            {
                user_image.setPixel(j, i, Color.WHITE);
            }
        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity
    }
    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY();
        Canvas canvas=new Canvas();
        float square_size=field.getSize();
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN: //нажатие
                if(x<(user_image.getWidth()/2+1)*square_size)//левее поля
                    break;
                if(x>=(user_image.getWidth()/2+1+user_image.getWidth())*square_size)//правее поля
                    break;
                int square_x=0;
                for(int i=1; i<user_image.getWidth()+1; i++)
                    if(x<(user_image.getWidth()/2+1+i)*square_size)
                    {
                        square_x=i-1;
                        break;
                    }
                if(y<(user_image.getHeight()/2+1)*square_size)//ниже поля
                    break;
                if(y>=(user_image.getHeight()/2+1+user_image.getHeight())*square_size)//выше поля
                    break;
                int square_y=0;
                for(int i=1; i<=user_image.getHeight()+1; i++)
                    if(y<(user_image.getHeight()/2+1+i)*square_size)
                    {
                        square_y=i-1;
                        break;
                    }
                if(user_image.getPixel(square_x, square_y) == Color.BLACK)
                    user_image.setPixel(square_x, square_y, Color.WHITE);
                else
                    user_image.setPixel(square_x, square_y, Color.BLACK);
                field.setImage(user_image);
                field.setType("user_image");
                field.setOnTouchListener(this);
                field.invalidate();
                setContentView(field);
                break;
            /*case MotionEvent.ACTION_MOVE: // движение
                sMove = "Move: " + x + "," + y;
                break;
            case MotionEvent.ACTION_UP: // отпускание
            case MotionEvent.ACTION_CANCEL:
                sMove = "";
                sUp = "Up: " + x + "," + y;
                break;*/
        }
        return true;
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