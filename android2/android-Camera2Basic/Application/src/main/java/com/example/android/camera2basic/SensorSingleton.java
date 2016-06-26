package com.example.android.camera2basic;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * Created by daviddong on 2016-06-26.
 */
public class SensorSingleton implements SensorEventListener {
    private static SensorSingleton ourInstance = new SensorSingleton();

    public static SensorSingleton getInstance() {
        return ourInstance;
    }

    private SensorManager mSensorManager;
    private Sensor mOrientationSensor;

    public double azimuth;
    public double pitch;
    public double roll;

    private SensorSingleton() {
        mSensorManager = (SensorManager)CameraActivity.getMainApplicationContext().getSystemService(Context.SENSOR_SERVICE);
        mOrientationSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mSensorManager.registerListener(this, mOrientationSensor, SensorManager.SENSOR_DELAY_FASTEST);
        this.azimuth = 0;
        this.pitch = 0;
        this.roll = 0;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            float[] values = event.values;
            azimuth = values[0];
            pitch = values[1];
            roll = values[2];
            Log.i("Yaw Pitch Roll", String.format("%f, %f, %f", azimuth, pitch, roll));
        }
    }
}
