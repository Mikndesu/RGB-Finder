package com.example.study_opengl;

public class Shaders {

    //Vertexシェーダー
    public static final String sVertexShaderSource =
            "uniform mat4 vpMatrix;" +
                    "uniform mat4 wMatrix;" +
                    "attribute vec3 position;" +
                    "void main() {" +
                    "  gl_Position = vpMatrix * wMatrix * vec4(position, 1.0);" +
                    "}";

    public static String createOne() {
        String s = String.valueOf((float)(MainActivity.c_red)/100.0f);
        String sFragmentShaderSource =
                "precision mediump float;" +
                        "void main() {" +
                        "  gl_FragColor = vec4(" + s + ", 0.0, 0.0, 1.0);" +
                        "}";
        return sFragmentShaderSource;
    }

    public static String createTwo() {
        String s = String.valueOf((float)(MainActivity.c_green)/100.0f);
        String sFragmentShaderSource =
                "precision mediump float;" +
                        "void main() {" +
                        "  gl_FragColor = vec4(0.0, " + s + ", 0.0, 1.0);" +
                        "}";
        return sFragmentShaderSource;
    }

    public static String createThree() {
        String s = String.valueOf((float)(MainActivity.c_blue)/100.0f);
        String sFragmentShaderSource =
                "precision mediump float;" +
                        "void main() {" +
                        "  gl_FragColor = vec4(0.0, 0.0, " + s + ", 1.0);" +
                        "}";
        return sFragmentShaderSource;
    }
}
