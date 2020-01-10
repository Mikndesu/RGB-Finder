package com.example.study_opengl;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.GL_BLEND;
import static android.opengl.GLES20.GL_ONE;

public class CustomRenderer implements GLSurfaceView.Renderer {

    private final Context context;

    float length = 300f;


    public CustomRenderer(final Context cont){
        context = cont;
    }

    public float[] vert(int x,int y) {
        //n角形
        int n = 72;
        ArrayList<Float> a_vertices = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            float a, b;
            a = length * (float)(Math.cos(Math.toRadians((360/n)*i)));
            b = length * (float)(Math.sin(Math.toRadians((360/n)*i)));
            a_vertices.add(x + a);
            a_vertices.add(y + b);
            a_vertices.add(0f);
        }
        float[] verticies = Utils.toArray_f(a_vertices);
        return verticies;
    }

    public short[] inde() {
        int n = 72;
        ArrayList<Short> a_indices = new ArrayList<>();
        for(short s = 0; s < n; s++) {
            a_indices.add(s);
        }
        short[] indices = Utils.toArray_s(a_indices);
        return indices;
    }
    //プログラムのID
    private int program1, program2, program3;
    private float[] mViewAndProjectionMatrix = new float[16];
    //フレームレート
    private long mFrameCount = 0;

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        //画面をクリア
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        //シェーダーをコンパイル
        int vertexShader1 = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        GLES20.glShaderSource(vertexShader1, Shaders.sVertexShaderSource);
        GLES20.glCompileShader(vertexShader1);
        int fragmentShader1 = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        GLES20.glShaderSource(fragmentShader1, Shaders.createOne());
        GLES20.glCompileShader(fragmentShader1);
        program1 = GLES20.glCreateProgram();
        GLES20.glAttachShader(program1, vertexShader1);
        GLES20.glAttachShader(program1, fragmentShader1);
        GLES20.glLinkProgram(program1);
        GLES20.glDeleteShader(vertexShader1);
        GLES20.glDeleteShader(fragmentShader1);

        int vertexShader2 = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        GLES20.glShaderSource(vertexShader2, Shaders.sVertexShaderSource);
        GLES20.glCompileShader(vertexShader2);
        int fragmentShader2 = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        GLES20.glShaderSource(fragmentShader2, Shaders.createTwo());
        GLES20.glCompileShader(fragmentShader2);
        program2 = GLES20.glCreateProgram();
        GLES20.glAttachShader(program2, vertexShader2);
        GLES20.glAttachShader(program2, fragmentShader2);
        GLES20.glLinkProgram(program2);
        GLES20.glDeleteShader(vertexShader2);
        GLES20.glDeleteShader(fragmentShader2);

        int vertexShader3 = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        GLES20.glShaderSource(vertexShader3, Shaders.sVertexShaderSource);
        GLES20.glCompileShader(vertexShader3);
        int fragmentShader3 = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        GLES20.glShaderSource(fragmentShader3, Shaders.createThree());
        GLES20.glCompileShader(fragmentShader3);
        program3 = GLES20.glCreateProgram();
        GLES20.glAttachShader(program3, vertexShader3);
        GLES20.glAttachShader(program3, fragmentShader3);
        GLES20.glLinkProgram(program3);
        GLES20.glDeleteShader(vertexShader3);
        GLES20.glDeleteShader(fragmentShader3);
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
    }

    @Override
    public void onDrawFrame(GL10 gl10) {

        int vertexShader1 = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        GLES20.glShaderSource(vertexShader1, Shaders.sVertexShaderSource);
        GLES20.glCompileShader(vertexShader1);
        int fragmentShader1 = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        GLES20.glShaderSource(fragmentShader1, Shaders.createOne());
        GLES20.glCompileShader(fragmentShader1);
        program1 = GLES20.glCreateProgram();
        GLES20.glAttachShader(program1, vertexShader1);
        GLES20.glAttachShader(program1, fragmentShader1);
        GLES20.glLinkProgram(program1);

        int vertexShader2 = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        GLES20.glShaderSource(vertexShader2, Shaders.sVertexShaderSource);
        GLES20.glCompileShader(vertexShader2);
        int fragmentShader2 = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        GLES20.glShaderSource(fragmentShader2, Shaders.createTwo());
        GLES20.glCompileShader(fragmentShader2);
        program2 = GLES20.glCreateProgram();
        GLES20.glAttachShader(program2, vertexShader2);
        GLES20.glAttachShader(program2, fragmentShader2);
        GLES20.glLinkProgram(program2);

        int vertexShader3 = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        GLES20.glShaderSource(vertexShader3, Shaders.sVertexShaderSource);
        GLES20.glCompileShader(vertexShader3);
        int fragmentShader3 = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        GLES20.glShaderSource(fragmentShader3, Shaders.createThree());
        GLES20.glCompileShader(fragmentShader3);
        program3 = GLES20.glCreateProgram();
        GLES20.glAttachShader(program3, vertexShader3);
        GLES20.glAttachShader(program3, fragmentShader3);
        GLES20.glLinkProgram(program3);
        GLES20.glEnable(GL_BLEND);
        GLES20.glBlendFunc(GL_ONE, GL_ONE);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        ShortBuffer indexBuffer = Utils.convert(inde());

        GLES20.glUseProgram(program1);
        FloatBuffer vertexBuffer1 = Utils.convert(vert(00, 435));
        float[] worldMatrix1 = new float[16];
        Matrix.setIdentityM(worldMatrix1, 0);
//        Matrix.rotateM(worldMatrix1, 0, (float) mFrameCount / 2.0f, 0, 0, 1);
        int attLoc1 = GLES20.glGetAttribLocation(program1, "position");
        int uniLoc1 = GLES20.glGetUniformLocation(program1, "vpMatrix");
        int uniLoc1_2 = GLES20.glGetUniformLocation(program1, "wMatrix");
        GLES20.glEnableVertexAttribArray(attLoc1);
        GLES20.glVertexAttribPointer(attLoc1, 3, GLES20.GL_FLOAT, false, 0, vertexBuffer1);
        GLES20.glUniformMatrix4fv(uniLoc1, 1, false, mViewAndProjectionMatrix, 0);
        GLES20.glUniformMatrix4fv(uniLoc1_2, 1, false, worldMatrix1, 0);
        GLES20.glDrawElements(GLES20.GL_TRIANGLE_FAN, indexBuffer.capacity(), GLES20.GL_UNSIGNED_SHORT, indexBuffer);
        GLES20.glDisableVertexAttribArray(attLoc1);

        GLES20.glUseProgram(program2);
        FloatBuffer vertexBuffer2 = Utils.convert(vert(-150, 200));
        float[] worldMatrix2 = new float[16];
        Matrix.setIdentityM(worldMatrix2, 0);
//        Matrix.rotateM(worldMatrix2, 0, (float) mFrameCount / 2.0f, 0, 0, 1);
        int attLoc2 = GLES20.glGetAttribLocation(program2, "position");
        int uniLoc2 = GLES20.glGetUniformLocation(program2, "vpMatrix");
        int uniLoc2_2 = GLES20.glGetUniformLocation(program2, "wMatrix");
        GLES20.glEnableVertexAttribArray(attLoc2);
        GLES20.glVertexAttribPointer(attLoc2, 3, GLES20.GL_FLOAT, false, 0, vertexBuffer2);
        GLES20.glUniformMatrix4fv(uniLoc2, 1, false, mViewAndProjectionMatrix, 0);
        GLES20.glUniformMatrix4fv(uniLoc2_2, 1, false, worldMatrix2, 0);
        GLES20.glDrawElements(GLES20.GL_TRIANGLE_FAN, indexBuffer.capacity(), GLES20.GL_UNSIGNED_SHORT, indexBuffer);
        GLES20.glDisableVertexAttribArray(attLoc2);

        GLES20.glUseProgram(program3);
        FloatBuffer vertexBuffer3 = Utils.convert(vert(150, 200));
        float[] worldMatrix3 = new float[16];
        Matrix.setIdentityM(worldMatrix3, 0);
//        Matrix.rotateM(worldMatrix3, 0, (float) mFrameCount / 2.0f, 0, 0, 1);
        int attLoc3 = GLES20.glGetAttribLocation(program3, "position");
        int uniLoc3 = GLES20.glGetUniformLocation(program3, "vpMatrix");
        int uniLoc3_2 = GLES20.glGetUniformLocation(program3, "wMatrix");
        GLES20.glEnableVertexAttribArray(attLoc3);
        GLES20.glVertexAttribPointer(attLoc3, 3, GLES20.GL_FLOAT, false, 0, vertexBuffer3);
        GLES20.glUniformMatrix4fv(uniLoc3, 1, false, mViewAndProjectionMatrix, 0);
        GLES20.glUniformMatrix4fv(uniLoc3_2, 1, false, worldMatrix3, 0);
        GLES20.glDrawElements(GLES20.GL_TRIANGLE_FAN, indexBuffer.capacity(), GLES20.GL_UNSIGNED_SHORT, indexBuffer);
        GLES20.glDisableVertexAttribArray(attLoc3);

        mFrameCount++;
        Log.d("FrameCount", String.valueOf(mFrameCount));
        GLES20.glUseProgram(0);
        GLES20.glDeleteProgram(program1);
        GLES20.glDeleteProgram(program2);
        GLES20.glDeleteProgram(program3);
    }
}