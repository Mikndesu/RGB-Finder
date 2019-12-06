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

    //Fragmentシェーダー
    public static final String sFragmentShaderSource =
            "precision mediump float;" +
                    "void main() {" +
                    "  gl_FragColor = vec4(0.4, 0.3, 0.9, 1.0);" +
                    "}";

    public static final String sFragmentShaderSourc =
            "precision mediump float;" +
                    "void main() {" +
                    "  gl_FragColor = vec4(0.0, 0.0, 1.0, 1.0);" +
                    "}";

}
