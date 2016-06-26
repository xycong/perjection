package com.example.android.camera2basic;

/**
 * Created by daviddong on 2016-06-25.
 */
class Vector3 {
    public double x;
    public double y;
    public double z;
    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3 add(Vector3 v1, Vector3 v2) {
        return new Vector3(v1.x+v2.x, v1.y+v2.y, v1.z+v2.z);
    }

    public static Vector3 subtract(Vector3 v1, Vector3 v2) {
        return new Vector3(v2.x-v1.x, v2.y-v1.y, v2.z-v1.z);
    }
}

