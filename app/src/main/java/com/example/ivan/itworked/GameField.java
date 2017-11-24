package com.example.ivan.itworked;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.ivan.itworked.Devices.ConnectionDevice;
import com.example.ivan.itworked.Devices.Device;
import com.example.ivan.itworked.Devices.NodeDevice;
import com.example.ivan.itworked.GameData.Level;
import com.example.ivan.itworked.GameData.LevelController;

import java.util.List;

/**
 * Created by Ivan on 18.11.2017.
 */

public class GameField extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    private Context context;
    private SurfaceHolder holder;
    private LevelController levelController;

    public final static int NODE_DEVICE = 1;
    public final static int CONNECTION_DEVICE = -1;
    public final static int NO_DEVICE = 0;

    private int resource = -1;
    private String name;
    private int currentDevice = 0;
    private float curStartX = -1;
    private float curStartY = -1;

    public GameField(Context context) {
        super(context);
        this.context = context;
        getHolder().addCallback(this);
        this.holder = getHolder();
        setOnTouchListener(this);
    }


    public void setLevel(Level level) {
        levelController = new LevelController(context, level);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        paint();
    }

    private void paint() {
        Canvas canvas = holder.lockCanvas();
        drawAllDevices(canvas);
        holder.unlockCanvasAndPost(canvas);
    }

    private void drawAllDevices(Canvas canvas) {
        List<Device> devices = levelController.getDevicesOnField();

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);

        canvas.drawRect(0, 0,
                context.getResources().getDisplayMetrics().widthPixels,
                context.getResources().getDisplayMetrics().heightPixels, paint);
        for (Device device : devices) {
            if (device instanceof NodeDevice) {
                float x = ((NodeDevice) device).getCenterX();
                float y = ((NodeDevice) device).getCenterY();
                Bitmap view = BitmapFactory.decodeResource(context.getResources(), device.getResourcePicture());
                canvas.drawBitmap(view, x, y, null);
            } else if (device instanceof ConnectionDevice) {
                ConnectionDevice connectionDevice = ((ConnectionDevice) device);
                paint.setStrokeWidth(10);
                paint.setColor(Color.BLUE);
                canvas.drawLine(connectionDevice.getFirstDevice().getX(), connectionDevice.getFirstDevice().getY(),
                        connectionDevice.getSecondDevice().getX(), connectionDevice.getSecondDevice().getY(), paint);
            }
        }
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setCurrentDeviceNode(int currentDeviceNode) {
        this.currentDevice = currentDeviceNode;
    }

    public void paint(String name, int resource, float x, float y) {
        Canvas canvas = holder.lockCanvas();
        List<Device> devices = levelController.getDevicesOnField();
        if (resource != 0) {
            NodeDevice newNode = new NodeDevice(context, name, resource, x, y);
            for (Device device : devices)
                if (device instanceof NodeDevice) {
                    NodeDevice nodeDevice = (NodeDevice) device;
                    if (nodeDevice.isIntersect(newNode)) {
                        drawAllDevices(canvas);
                        holder.unlockCanvasAndPost(canvas);
                        return;
                    }
                }
            levelController.addDevice(newNode);
        }
        drawAllDevices(canvas);
        holder.unlockCanvasAndPost(canvas);
    }


    public void paint(float firstX, float firstY, float secondX, float secondY) {
       // Log.d("check","paint connection device");
        Canvas canvas = holder.lockCanvas();
        List<NodeDevice> devices = levelController.getNodeDeviceOnField();
        for (NodeDevice firstDevice : devices)
            if (firstDevice.isContain(firstX, firstY)){
              //  Log.d("check", firstDevice.getName());
                for (NodeDevice secondDevice : devices)
                    if (secondDevice.isContain(secondX, secondY)) {
                        ConnectionDevice connectionDevice = new ConnectionDevice("cord", resource);
                        connectionDevice.setFirstDevice(firstDevice);
                        connectionDevice.setSecondDevice(secondDevice);
                        levelController.addDevice(connectionDevice);
                      //  Log.d("check", firstDevice.getName() + " " + secondDevice.getName());
                    }
            }

        drawAllDevices(canvas);
        holder.unlockCanvasAndPost(canvas);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
          //  Log.d("check",currentDevice + " current device");
            if (currentDevice == NODE_DEVICE) {
                if (resource != -1) {
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();
                    paint(name, resource, x, y);
                    resource = -1;
                }
                currentDevice = NO_DEVICE;
            } else if (currentDevice == CONNECTION_DEVICE) {
                curStartX = motionEvent.getX();
                curStartY = motionEvent.getY();
             //   Log.d("check",curStartX + " start point");
            }
        }
     //   Log.d("check","check");
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
           // Log.d("check","check2");
            if (curStartX != -1) {
                paint(curStartX, curStartY, motionEvent.getX(), motionEvent.getY());
                curStartX = -1;

            }
            currentDevice = NO_DEVICE;
        }
        return true;
    }

    public boolean isCorrect(){
        List<ConnectionDevice> correctDevices = levelController.getCorrectDescription();
        List<ConnectionDevice> checkableDevices = levelController.getConnectionDeviceOnField();
        boolean flag = false;
        for (ConnectionDevice correct : correctDevices) {
            flag = false;
            for (ConnectionDevice check : checkableDevices)
                if (correct.equals(check)) {
                    flag = true;
                    break;
                }
        }
        return flag;
    }

}
