package com.example.android.camera2basic;

import android.location.Location;

/**
 * Created by daviddong on 2016-06-25.
 */
public class CoordinateHelper {
    public static double EARTH_RAD = 6371; // km
    public static int UNIT_CONSTANT = 1000;

    public static Vector3 latLongToXYZ(Location location) {
        double lat = location.getLatitude();
        double lon = location.getLongitude();

        double x = EARTH_RAD * Math.cos(lat) * Math.cos(lon);
        double y = EARTH_RAD * Math.cos(lat) * Math.sin(lon);
        double z = EARTH_RAD * Math.sin(lat);
        return new Vector3(x,y,z);
    }

    public static Vector3 phoneCenterAngleToVector(double yaw, double pitch, double roll) {
        double x = Math.cos(yaw) * Math.cos(pitch);
        double y = Math.sin(yaw) * Math.cos(pitch);
        double z = Math.sin(pitch);

        return new Vector3(x*1000,y*1000,z*1000);
    }

    public static Vector3 phoneCenterToObjectVector(Vector2 phoneV, Vector2 objV) {
        Vector2 result = Vector2.subtract(phoneV, objV);
        return new Vector3(result.x, result.y, UNIT_CONSTANT);
    }

    public static Vector3 getObjectVectorFromPhoneVector(Vector3 phoneV, Vector3 objV) {
        return Vector3.add(phoneV, objV);
    }

    public static Vector3 getObjectWorldSpace(Vector3 worldOriginToPhone, Vector3 phoneOriginToObject) {
        return Vector3.add(worldOriginToPhone, phoneOriginToObject);
    }

    public static Vector3 getObjectToPhoneCenter(Vector3 objectWorldSpace, Vector3 worldOriginToPhone) {
        return Vector3.subtract(worldOriginToPhone, objectWorldSpace);
    }

    public static Vector2 getObjectXYFromObjectVector(Vector3 objectVector, double yaw, double pitch, double roll) {
        Vector3 objToPhoneCenter = Vector3.subtract(CoordinateHelper.phoneCenterAngleToVector(yaw, pitch, roll), objectVector);
        return new Vector2(objToPhoneCenter.x, objToPhoneCenter.y);
    }
}
