package com.mobile.gui.esp.photogram;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Andrei on 22.05.2016.
 */
public class FieldGLRenderer implements GLSurfaceView.Renderer
{
    private Context context;
    private FieldGLSurfaceView field;
    public FieldGLRenderer(Context context, FieldGLSurfaceView view)
    {
        this.context=context;
        field=view;
    }
    @Override
    public void onDrawFrame(GL10 unused)
    {
        // Draw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        Bitmap image=Bitmap.createBitmap(100, 100, Bitmap.Config.ALPHA_8);
        field.draw(image);
    }
    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config)
    {

        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

    }
    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height)
    {
        // Adjust the viewport based on geometry changes,
        // such as screen rotation
        GLES20.glViewport(0, 0, width, height);
    }
}