package com.example.study_opengl;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.List;

import static java.lang.reflect.Array.newInstance;

public class Utils {
    //よくわかんないからおまじない

    public static FloatBuffer convert(float[] data) {
        //使用するバッファの容量を確保する
        ByteBuffer bf = ByteBuffer.allocateDirect(data.length * 4);
        bf.order(ByteOrder.nativeOrder());

        FloatBuffer floatBuffer = bf.asFloatBuffer();
        floatBuffer.put(data);
        floatBuffer.position(0);

        return floatBuffer;
    }

    public static ShortBuffer convert(short[] data) {
        //使用するバッファの容量を確保する
        ByteBuffer bf = ByteBuffer.allocateDirect(data.length * 2);
        bf.order(ByteOrder.nativeOrder());

        ShortBuffer shortBuffer = bf.asShortBuffer();
        shortBuffer.put(data);
        shortBuffer.position(0);

        return shortBuffer;
    }

    public static short[] toArray_s(List<Short> target) {
        final short[] result = new short[target.size()];
        for(int i = 0; i < result.length; i++) {
            result[i] = target.get(i);
        }
        return result;
     }

    public static float[] toArray_f(List<Float> target) {
        final float[] result = new float[target.size()];
        for(int i = 0; i < result.length; i++) {
            result[i] = target.get(i);
        }
        return result;
    }

//    public static <?>[] toArray(List target) {
//        String classinfo = target.get(0).getClass().getTypeName();
//        if(classinfo.equals("short")) {
//            final short[] result = new short[target.size()];
//            for(int i = 0; i < result.length; i++) {
//                result[i] = target.get(i);
//            }
//            return result;
//        } else if(classinfo.equals("float")) {
//            final float[] result = new float[target.size()];
//            for(int i = 0; i < result.length; i++) {
//                result[i] = target.get(i);
//            }
//            return result;
//        }
//    }

}
