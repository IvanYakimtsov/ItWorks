package com.example.ivan.itworked.Devices;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.os.Parcel;
import android.util.Log;

/**
 * Created by Ivan on 18.11.2017.
 */

public class NodeDevice implements Device {
    private float x;
    private float y;
    private int resourcePicture;
    private transient Context context;
    private String name;

    public NodeDevice(Context context, String name, int bitmap, float x, float y) {
        this.context = context;
        this.x = x;
        this.y = y;
        resourcePicture = bitmap;
        this.name = name;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public NodeDevice(String name) {
        this.name = name;
    }

    @Override
    public int getResourcePicture() {
        return resourcePicture;
    }

    @Override
    public String getName() {
        return name;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getCenterX() {
        Bitmap view = BitmapFactory.decodeResource(context.getResources(), resourcePicture);
        return x - view.getWidth() / 2;
    }

    public float getCenterY() {
        Bitmap view = BitmapFactory.decodeResource(context.getResources(), resourcePicture);
        return y - view.getHeight() / 2;
    }

    public boolean isIntersect(NodeDevice device) {
        Bitmap firstDevice = BitmapFactory.decodeResource(context.getResources(), resourcePicture);
        Bitmap secondDevice = BitmapFactory.decodeResource(context.getResources(), device.resourcePicture);

        RectF firstDeviceRect = new RectF(x, y, x + firstDevice.getWidth(), y + firstDevice.getHeight());
        RectF secondDeviceRect = new RectF(device.getX(), device.getY(), device.getX() + secondDevice.getWidth(),
                device.getY() + secondDevice.getHeight());

        return RectF.intersects(firstDeviceRect, secondDeviceRect);
    }

    public boolean isContain(float x, float y) {
        Bitmap device = BitmapFactory.decodeResource(context.getResources(), resourcePicture);
        RectF deviceRect = new RectF(this.x - device.getWidth() / 2, this.y - device.getHeight() / 2,
                this.x + device.getWidth(), this.y + device.getHeight());
//        Log.d("check", x + " " + y);
//        Log.d("check", this.x + " " + this.y);
        return deviceRect.contains(x, y);
        //  return true;
    }


    public void setContext(Context context) {
        this.context = context;
    }
}
