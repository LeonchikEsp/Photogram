package com.mobile.gui.esp.photogram;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Andrei on 22.05.2016.
 */
public class Field extends View
{
    private Paint mPaint = new Paint();
    private Rect rect;
    Bitmap image;
    Bitmap nonogram;
    private float square_size=1;
    private Canvas canvas=null;
    String type;
    public void setImage(Bitmap image)
    {
        this.image = image;
    }
    public void setType(String type)
    {
        this.type = type;
    }
    public Field(Context context, Bitmap image, String type)
    {
        super(context);
        this.image=image;
        this.type=type;
    }
    public float getSize()
    {
        return square_size;
    }
    public Canvas getCanvas()
    {
        return canvas;
    }
    public Bitmap getImage()
    {
        return image;
    }
    public void drawWhiteSquare(Canvas canvas, float x, float y, float side)
    {
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x, y, x+side, y+side, mPaint);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(x, y, x+side, y+side, mPaint);
    }
    public void drawBlackSquare(Canvas canvas, float x, float y, float side)
    {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x, y, x+side, y+side, mPaint);
    }
    private void drawNumberSquare(Canvas canvas, float x, float y, float side, int number)
    {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(x, y, x + side, y + side, mPaint);
        mPaint.setTextSize(0.9f * side);
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(Integer.toString(number), x + side / 2, y + 3 * side / 4, mPaint);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        if(type=="completed")
        {
            mPaint.setTextSize(50);
            mPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("Нонограмма решена!", (getWidth()) / 2, 50, mPaint);
            mPaint.setTextSize(30);
            canvas.drawText("Нажмите на экран, чтобы выйти в главное меню", (getWidth())/2, 100, mPaint);
        }
        if (type == "nonogram") {
            nonogram = Bitmap.createBitmap(image.getWidth(), image.getHeight(), Bitmap.Config.ARGB_8888);
            for (int i = 0; i < image.getHeight(); i++)
                for (int j = 0; j < image.getWidth(); j++) {
                    if (image.getPixel(j, i) == Color.BLACK)
                        nonogram.setPixel(j, i, Color.BLACK);
                }
        }
        int down_offset = 150;
        float w = getWidth();
        float h = getHeight();
        float iw = image.getWidth();
        float ih = image.getHeight();
        float square_w = getWidth() / (image.getWidth() + image.getWidth() / 2 + 1);
        float square_h = (getHeight() - down_offset) / (image.getHeight() + image.getHeight() / 2 + 1);
        if (square_h < square_w)
            square_size = square_h;
        else
            square_size = square_w;
        for (int i = 0; i < image.getHeight(); i++)//поле
            for (int j = 0; j < image.getWidth(); j++) {
                float top_left_x = (image.getWidth() / 2 + 1 + j) * square_size;
                float top_left_y = (image.getHeight() / 2 + 1 + i) * square_size;
                if ((image.getPixel(j, i) == Color.BLACK) && (type != "nonogram"))
                    drawBlackSquare(canvas, top_left_x, top_left_y, square_size);
                else
                    drawWhiteSquare(canvas, top_left_x, top_left_y, square_size);
            }
        if (type != "completed") {
            int black_row_number = 0;
            boolean isPreviousBlack = false;
            int square_number = 1;
            for (int i = nonogram.getHeight() - 1; i >= 0; i--)//числа слева от поля
            {
                for (int j = nonogram.getWidth() - 1; j >= 0; j--) {
                    if (nonogram.getPixel(j, i) == Color.BLACK) {
                        black_row_number++;
                        isPreviousBlack = true;
                    } else {
                        if (isPreviousBlack) {
                            float top_left_x = (nonogram.getWidth() / 2 + 1 - square_number) * square_size;
                            float top_left_y = (nonogram.getHeight() / 2 + 1 + i) * square_size;
                            drawNumberSquare(canvas, top_left_x, top_left_y, square_size, black_row_number);
                            square_number++;
                        }
                        black_row_number = 0;
                        isPreviousBlack = false;
                    }
                }
                if (isPreviousBlack) {
                    float top_left_x = (nonogram.getWidth() / 2 + 1 - square_number) * square_size;
                    float top_left_y = (nonogram.getHeight() / 2 + 1 + i) * square_size;
                    drawNumberSquare(canvas, top_left_x, top_left_y, square_size, black_row_number);
                }
                black_row_number = 0;
                square_number = 1;
                isPreviousBlack = false;
            }
            black_row_number = 0;
            isPreviousBlack = false;
            square_number = 1;
            for (int j = nonogram.getWidth() - 1; j >= 0; j--)//числа сверху от поля
            {
                for (int i = nonogram.getHeight() - 1; i >= 0; i--) {
                    if (nonogram.getPixel(j, i) == Color.BLACK) {
                        black_row_number++;
                        isPreviousBlack = true;
                    } else {
                        if (isPreviousBlack) {
                            float top_left_x = (nonogram.getWidth() / 2 + 1 + j) * square_size;
                            float top_left_y = (nonogram.getHeight() / 2 + 1 - square_number) * square_size;
                            drawNumberSquare(canvas, top_left_x, top_left_y, square_size, black_row_number);
                            square_number++;
                        }
                        black_row_number = 0;
                        isPreviousBlack = false;
                    }
                }
                if (isPreviousBlack) {
                    float top_left_x = (nonogram.getWidth() / 2 + 1 + j) * square_size;
                    float top_left_y = (nonogram.getHeight() / 2 + 1 - square_number) * square_size;
                    drawNumberSquare(canvas, top_left_x, top_left_y, square_size, black_row_number);
                }
                black_row_number = 0;
                square_number = 1;
                isPreviousBlack = false;
            }
        }
    }
}