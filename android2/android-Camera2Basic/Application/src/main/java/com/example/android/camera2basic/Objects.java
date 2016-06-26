package com.example.android.camera2basic;

import android.location.Location;

import java.util.ArrayList;

/**
 * Created by daviddong on 2016-06-26.
 */
public class Objects {
    private static Objects ourInstance = new Objects();

    public static Objects getInstance() {
        return ourInstance;
    }

    ArrayList<Vector3> objects;

    private Objects() {
        objects = new ArrayList<>();
    }

    public void addObject(Location location, double yaw, double pitch, double roll, Vector2 phoneCenter, Vector2 objCenter) {
        double radYaw = Math.toRadians(yaw);
        double radPitch = Math.toRadians(pitch);
        double radRoll = Math.toRadians(roll);
        Vector3 phoneXYZ = CoordinateHelper.latLongToXYZ(location);
        Vector3 phoneCenterToObject = CoordinateHelper.phoneCenterToObjectVector(phoneCenter, objCenter);
        Vector3 phoneCenterToWorld = CoordinateHelper.phoneCenterAngleToVector(radYaw, radPitch, radRoll);
        Vector3 objectVector = CoordinateHelper.getObjectVectorFromPhoneVector(phoneCenterToWorld, phoneCenterToObject);
        Vector3 objectWorldVector = CoordinateHelper.getObjectWorldSpace(phoneXYZ, objectVector);
        this.objects.add(objectWorldVector);
    }

    public ArrayList<Vector2> getObjectLocations(Location location, double yaw, double pitch, double roll) {
        double radYaw = Math.toRadians(yaw);
        double radPitch = Math.toRadians(pitch);
        double radRoll = Math.toRadians(roll);
        ArrayList<Vector2> arrayList = new ArrayList<>();
        for (int i=0; i<this.objects.size(); i++) {
            Vector3 v = this.objects.get(i);
            Vector3 worldOriginToPhone = CoordinateHelper.latLongToXYZ(location);
            Vector3 objectToPhone = CoordinateHelper.getObjectToPhoneCenter(v, worldOriginToPhone);
            Vector2 objectXY = CoordinateHelper.getObjectXYFromObjectVector(objectToPhone, radYaw, radPitch, radRoll);
            arrayList.add(objectXY);
        }
        return arrayList;
    }
}
