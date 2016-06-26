package com.example.android.camera2basic;

/**
 * Created by daviddong on 2016-06-26.
 */
public class Vector2 {
    public double x;
    public double y;
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2 subtract(Vector2 v1, Vector2 v2) {
        return new Vector2(v2.x-v1.x, v2.y-v1.y);
    }
}
