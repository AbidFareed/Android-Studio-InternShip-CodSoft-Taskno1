package com.example.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView IV;
    private TextView TV;
    private CameraManager CM;
    String Cameraid,result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        IV.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    change();
                }
                return false;
            }
        });
    }


    void init(){
            IV=findViewById(R.id.IV);
            TV=findViewById(R.id.TV);
        CM= (CameraManager) getSystemService(CAMERA_SERVICE);
    }
    void change(){
        result= getResources().getString(R.string.tap_to_on_flashlight);
        if(TV.getText().toString().trim().equals(result)){
            try {
                Cameraid=CM.getCameraIdList()[0];
                CM.setTorchMode(Cameraid,false);
                TV.setText(R.string.tap_to_off_flashlight);
                IV.setImageResource(R.drawable.off_light);
            } catch (CameraAccessException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            try {
                Cameraid=CM.getCameraIdList()[0];
                CM.setTorchMode(Cameraid,true);
                TV.setText(R.string.tap_to_on_flashlight);
                IV.setImageResource(R.drawable.on_light);
            } catch (CameraAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}