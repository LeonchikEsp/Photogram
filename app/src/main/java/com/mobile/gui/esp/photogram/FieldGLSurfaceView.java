package com.mobile.gui.esp.photogram;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

/**
 * Created by Andrei on 22.05.2016.
 */
public class FieldGLSurfaceView extends GLSurfaceView
{
    private final FieldGLRenderer mRenderer;
    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    private final float[] mRotationMatrix = new float[16];
    public FieldGLSurfaceView(Context context)
    {
        super(context);

        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        mRenderer = new FieldGLRenderer(context, this);
        setRenderer(mRenderer);

        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
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
        float[] mMVPMatrix = new float[16];
        float[] mProjectionMatrix = new float[16];
        float[] mViewMatrix = new float[16];
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
        for(int i=0; i<image.getWidth(); i++)
            for(int j=0; j<image.getHeight(); j++)
            {
                float top_left_x=getWidth()/2-image.getWidth()/2-1-j*square_size;
                float top_left_y=getHeight()/2-image.getHeight()/2-1-i*square_size;
                square.setCoordinates(top_left_x, top_left_y, top_left_x, top_left_y-square_size,
                        top_left_x-square_size, top_left_y-square_size, top_left_x-square_size, top_left_y);
                square.draw(mMVPMatrix);
            }
    }
}