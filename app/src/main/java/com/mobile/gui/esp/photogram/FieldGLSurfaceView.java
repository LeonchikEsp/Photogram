package com.mobile.gui.esp.photogram;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

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
    //------------------------------------------------------------
    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float mPreviousX;
    private float mPreviousY;
    private final String vertexShaderCode =
            // This matrix member variable provides a hook to manipulate
            // the coordinates of the objects that use this vertex shader
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    // The matrix must be included as a modifier of gl_Position.
                    // Note that the uMVPMatrix factor *must be first* in order
                    // for the matrix multiplication product to be correct.
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";
    private int mPositionHandle;
    private int mColorHandle;
    private int mMVPMatrixHandle;
    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
    float squareCoords[] = {
            //-0.5f,  0.5f, 0.0f,   // top left
            //-0.5f, -0.5f, 0.0f,   // bottom left
            //0.5f, -0.5f, 0.0f,   // bottom right
            //0.5f,  0.5f, 0.0f }; // top right
            0.0f,  0.1f, 0.0f,   // top left
            0.0f, 0.5f, 0.0f,   // bottom left
            0.5f, 0.5f, 0.0f,   // bottom right
            0.5f,  0.0f, 0.0f }; // top right

    private final short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; // order to draw vertices

    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    float color[] = { 0.2f, 0.709803922f, 0.898039216f, 1.0f };
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
    public void draw(Bitmap image, float[] mvpMatrix)
    {
        /*int down_offset=50;
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
                square.draw(mvpMatrix);
            }*/
        Square mSquare=new Square();
        //mSquare.setCoordinates(0, 0, 1, 1, 2, 3, 3, 4);
        float[] scratch = new float[16];

        // Draw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        // Set the camera position (View matrix)
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        // Draw square
        mSquare.draw(mMVPMatrix);
        //Square mSquare=new Square();
        //mSquare.draw(mvpMatrix);
    }
}