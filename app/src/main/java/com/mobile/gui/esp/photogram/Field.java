package com.mobile.gui.esp.photogram;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Andrei on 22.05.2016.
 */
public class Field extends View {
    private Paint mPaint = new Paint();
    public Field(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas){
        // закрашиваем холст белым цветом
        mPaint.setColor(Color.WHITE);
    }
}
