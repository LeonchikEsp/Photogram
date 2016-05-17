package com.mobile.gui.esp.photogram;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;

/**
 * Created by Andrei on 17.05.2016.
 */
public class Field extends GLSurfaceView
{
    Field(Context context)
    {
        super(context);
    }
    public void draw(Bitmap image)
    {
        int down_offset=50;
        float square_w=getWidth()/(image.getWidth()+image.getWidth()/2+1);
        float square_h=getHeight()/(image.getHeight()+image.getHeight()/2+1+down_offset);
        float square_size;
        if(square_h<square_w)
            square_size=square_h;
        else
            square_size=square_w;
        Square square=new Square();
        for(int i=0; i<image.getWidth(); i++)
            for(int j=0; j<image.getHeight(); j++)
            {
                //square.draw(mMVPMatrix);
            }
    }
}
