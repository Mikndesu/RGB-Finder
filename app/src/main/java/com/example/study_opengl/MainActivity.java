package com.example.study_opengl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static int c_red = 0;
    public static int c_green = 0;
    public static int c_blue = 0;

    private GLSurfaceView glSurfaceView;

    private TextView vred, vgreen, vblue;

    SeekBar red, green, blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //おまじない
        glSurfaceView = findViewById(R.id.glSurfaceView);
        glSurfaceView.setEGLContextClientVersion(2);
        glSurfaceView.setRenderer(new CustomRenderer(getApplicationContext()));
        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        vred = findViewById(R.id.vred);
        vgreen = findViewById(R.id.vgreen);
        vblue = findViewById(R.id.vblue);

        vred.setText(String.valueOf((int) (((float)(c_red))*2.55f)));
        vgreen.setText(String.valueOf((int)(((float)(c_green))*2.55f)));
        vblue.setText(String.valueOf((int)(((float)(c_blue))*2.55f)));

        vred.setOnClickListener((v)->
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("RGB Generater")
                    .setMessage("Copy the HexRGB to ClipBoard")
                    .setPositiveButton("Ok", ((d, w)->{ClipboardManager cm = (ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                        cm.setPrimaryClip(ClipData.newPlainText("", generateHexCode()));}))
                    .setNegativeButton("Cancel", ((d, w)->{}))
                    .show());
        vgreen.setOnClickListener((v)->
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("RGB Generater")
                    .setMessage("Copy the HexRGB to ClipBoard")
                    .setPositiveButton("Ok", ((d, w)->{ClipboardManager cm = (ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                        cm.setPrimaryClip(ClipData.newPlainText("", generateHexCode()));}))
                    .setNegativeButton("Cancel", ((d, w)->{}))
                    .show());
        vblue.setOnClickListener((v)-> new AlertDialog.Builder(MainActivity.this)
                .setTitle("RGB Generater")
                .setMessage("Copy the HexRGB to ClipBoard")
                .setPositiveButton("Ok", ((d, w)->{ClipboardManager cm = (ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                    cm.setPrimaryClip(ClipData.newPlainText("", generateHexCode()));}))
                .setNegativeButton("Cancel", ((d, w)->{}))
                .show());

        red = findViewById(R.id.red);
        red.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        c_red = progress;
                        vred.setText(String.valueOf((int) (((float)(c_red))*2.55f)));
                        Log.d("Color code = ", generateHexCode());
                    }

                    public void onStartTrackingTouch(SeekBar seekBar) {
                        // ツマミに触れたときに呼ばれる
                    }

                    public void onStopTrackingTouch(SeekBar seekBar) {
                        // ツマミを離したときに呼ばれる
                    }
                }
        );

        green = findViewById(R.id.green);
        green.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        c_green = progress;
                        vgreen.setText(String.valueOf((int)(((float)(c_green))*2.55f)));
                        Log.d("Color code = ", generateHexCode());
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        blue = findViewById(R.id.blue);
        blue.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        c_blue = progress;
                        vblue.setText(String.valueOf((int)(((float)(c_blue))*2.55f)));
                        Log.d("Color code = ", generateHexCode());
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );


    }

    String generateHexCode() {
        String s_red, s_green, s_blue;
        if(c_red <= 15) {
            s_red = "0"+Integer.toHexString(c_red);
        } else {
            s_red = Integer.toHexString(c_red);
        }
        if(c_green <= 15) {
            s_green = "0"+Integer.toHexString(c_green);
        } else {
            s_green = Integer.toHexString(c_green);
        }
        if(c_blue <= 15) {
            s_blue = "0"+Integer.toHexString(c_blue);
        } else {
            s_blue = Integer.toHexString(c_blue);
        }

        return "#"+s_red+s_green+s_blue;
    }
}
