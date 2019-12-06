package com.example.study_opengl;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class CustomRenderer implements GLSurfaceView.Renderer {

    private final Context context;

    float length = 300f;


    public CustomRenderer(final Context cont){
        context = cont;
    }

    public float[] vert(int x) {
        //n角形
        int n = MainActivity.corners;
        ArrayList<Float> a_vertices = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            float a, b;
                a = length * (float)(Math.cos(Math.toRadians((360/n)*i)));
                b = length * (float)(Math.sin(Math.toRadians((360/n)*i)));
                a_vertices.add(x * a);
                a_vertices.add(x * (b + 300f));
                a_vertices.add(0f);
        }
        float[] verticies = Utils.toArray_f(a_vertices);
        return verticies;
    }

    public short[] inde() {
        int n = MainActivity.corners;
        ArrayList<Short> a_indices = new ArrayList<>();
        for(short s = 0; s < n; s++) {
            a_indices.add(s);
        }
        short[] indices = Utils.toArray_s(a_indices);
        return indices;
    }
    //プログラムのID
    private int mProgramId;
    private float[] mViewAndProjectionMatrix = new float[16];
    //フレームレート
    private long mFrameCount = 0;

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        //画面をクリア
        GLES20.glClearColor(0.5f, 0.8f, 0.3f, 0.5f);

        //シェーダーをコンパイル
        int vertexShader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        GLES20.glShaderSource(vertexShader, Shaders.sVertexShaderSource);
        GLES20.glCompileShader(vertexShader);

        //シェーダーをコンパイル
        int fragmentShader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        GLES20.glShaderSource(fragmentShader, Shaders.sFragmentShaderSource);
        GLES20.glCompileShader(fragmentShader);

        mProgramId = GLES20.glCreateProgram();
        //シェーダーをプログラムにリンクする
        GLES20.glAttachShader(mProgramId, vertexShader);
        GLES20.glAttachShader(mProgramId, fragmentShader);
        GLES20.glLinkProgram(mProgramId);
        GLES20.glUseProgram(mProgramId);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
//        /*
//         * ビューポート変換
//         * @see <a href="http://tkengo.github.io/blog/2015/01/17/opengl-es-2-2d-knowledge-4/">label</a>
//         */
        GLES20.glViewport(0, 0, width, height);

//        /*
//         * ビュー座標変換
//         * @see <a href="http://tkengo.github.io/blog/2015/01/10/opengl-es-2-2d-knowledge-3#view-transformation-matrix">label</a>
//         * 射影変換
//         * @see <a href="http://tkengo.github.io/blog/2015/01/10/opengl-es-2-2d-knowledge-3#projection-transformation-matrix">label</a>
//         */
        float[] projectionMatrix = new float[16];
        float[] viewMatrix       = new float[16];
        Matrix.setLookAtM(viewMatrix, 0, 0f, 0f, 1f, 0f, 0f, 0f, 0f, 1f, 0f);
        Matrix.orthoM(projectionMatrix, 0, -width / 2f, width / 2f, -height / 2, height / 2, 0f, 2f);
        Matrix.multiplyMM(mViewAndProjectionMatrix, 0, projectionMatrix, 0, viewMatrix, 0);
        GLES20.glLineWidth(8);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        FloatBuffer vertexBuffer = Utils.convert(vert(1));
        ShortBuffer indexBuffer = Utils.convert(inde());

        float[] worldMatrix = new float[16];
        Matrix.setIdentityM(worldMatrix, 0);
        Matrix.rotateM(worldMatrix, 0, (float) mFrameCount / 2.0f, 0, 0, 1);

        int attLoc1 = GLES20.glGetAttribLocation(mProgramId, "position");
        int uniLoc1 = GLES20.glGetUniformLocation(mProgramId, "vpMatrix");
        int uniLoc2 = GLES20.glGetUniformLocation(mProgramId, "wMatrix");
        GLES20.glEnableVertexAttribArray(attLoc1);

        GLES20.glVertexAttribPointer(attLoc1, 3, GLES30.GL_FLOAT, false, 0, vertexBuffer);
        GLES20.glUniformMatrix4fv(uniLoc1, 1, false, mViewAndProjectionMatrix, 0);
        GLES20.glUniformMatrix4fv(uniLoc2, 1, false, worldMatrix, 0);

        //描画
        GLES20.glDrawElements(GLES20.GL_TRIANGLE_FAN, indexBuffer.capacity(), GLES20.GL_UNSIGNED_SHORT, indexBuffer);

        GLES20.glDisableVertexAttribArray(attLoc1);


        FloatBuffer vertexBuffe = Utils.convert(vert(-2));

        float[] worldMatrix1 = new float[16];
        Matrix.setIdentityM(worldMatrix1, 0);
        Matrix.rotateM(worldMatrix1, 0, (float) mFrameCount / 2.0f, 0, 0, 1);

        int attLoc11 = GLES20.glGetAttribLocation(mProgramId, "position");
        int uniLoc11 = GLES20.glGetUniformLocation(mProgramId, "vpMatrix");
        int uniLoc21 = GLES20.glGetUniformLocation(mProgramId, "wMatrix");
        GLES20.glEnableVertexAttribArray(attLoc11);

        GLES20.glVertexAttribPointer(attLoc11, 3, GLES30.GL_FLOAT, false, 0, vertexBuffe);
        GLES20.glUniformMatrix4fv(uniLoc11, 1, false, mViewAndProjectionMatrix, 0);
        GLES20.glUniformMatrix4fv(uniLoc21, 1, false, worldMatrix1, 0);

        //描画
        GLES20.glDrawElements(GLES20.GL_TRIANGLE_FAN, indexBuffer.capacity(), GLES20.GL_UNSIGNED_SHORT, indexBuffer);
        GLES20.glDisableVertexAttribArray(attLoc1);

        mFrameCount++;
    }
    }