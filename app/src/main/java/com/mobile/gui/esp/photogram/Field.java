package com.mobile.gui.esp.photogram;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by Andrei on 22.05.2016.
 */
public class Field extends View {
    private Paint mPaint = new Paint();
    private Rect rect;
    Bitmap image;
    public Field(Context context, Bitmap image) {
        super(context);
        this.image=image;
    }
    private void drawWhiteSquare(Canvas canvas, float x, float y, float side)
    {
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x, y, x+side, y+side, mPaint);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(x, y, x+side, y+side, mPaint);
    }
    private void drawBlackSquare(Canvas canvas, float x, float y, float side)
    {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x, y, side, side, mPaint);
    }
    @Override
    protected void onDraw(Canvas canvas)
    {
        int down_offset=50;
        float w=getWidth();
        float h=getHeight();
        float iw=image.getWidth();
        float ih=image.getHeight();
        float square_w=getWidth()/(image.getWidth()+image.getWidth()/2+1);
        float square_h=(getHeight()-down_offset)/(image.getHeight()+image.getHeight()/2+1);
        float square_size;
        if(square_h<square_w)
            square_size=square_h;
        else
            square_size=square_w;
        for(int i=0; i<image.getWidth(); i++)
            for(int j=0; j<image.getHeight(); j++)
            {
                float top_left_x=(image.getWidth()/2+1+j)*square_size;
                float top_left_y=(image.getHeight()/2+1+i)*square_size;
                drawWhiteSquare(canvas, top_left_x, top_left_y, square_size);
            }
    }
}
