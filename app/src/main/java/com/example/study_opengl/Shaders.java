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
        String s = String.valueOf((float)(MainActivity.c_red)/10.0f);
        String sFragmentShaderSource =
                "precision mediump float;" +
                        "void main() {" +
                        "  gl_FragColor = vec4(0.4, " + s + ", 0.0, 0.6);" +
                        "}";
        return sFragmentShaderSource;
    }

    public static String createTwo() {
        String s = String.valueOf((float)(MainActivity.c_green)/10.0f);
        String sFragmentShaderSource =
                "precision mediump float;" +
                        "void main() {" +
                        "  gl_FragColor = vec4(0.0, 0.4, " + s + ", 0.6);" +
                        "}";
        return sFragmentShaderSource;
    }

    public static String createThree() {
        String s = String.valueOf((float)(MainActivity.c_blue)/10.0f);
        String sFragmentShaderSource =
                "precision mediump float;" +
                        "void main() {" +
                        "  gl_FragColor = vec4(" + s + ", 0.0, 0.4, 0.6);" +
                        "}";
        System.out.println(sFragmentShaderSource);
        return sFragmentShaderSource;
    }
}
