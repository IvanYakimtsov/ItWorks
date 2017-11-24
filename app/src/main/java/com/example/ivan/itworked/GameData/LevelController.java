package com.example.ivan.itworked.GameData;

import android.content.Context;

import com.example.ivan.itworked.Devices.ConnectionDevice;
import com.example.ivan.itworked.Devices.Device;
import com.example.ivan.itworked.Devices.NodeDevice;
import com.example.ivan.itworked.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 18.11.2017.
 */

public class LevelController {
    private List<Device> deviceOnField;
    private List<Device> useDevices;

    private List<ConnectionDevice> correctDescription;

    public LevelController(Context context, Level level){
        deviceOnField = new ArrayList<>();
        useDevices = new ArrayList<>();
        correctDescription = new ArrayList<>();

        switch (level.getLevel()){
            case 1:
                deviceOnField.add(new NodeDevice(context, "lamp", R.drawable.lamp, 300, 550));
                useDevices.add(new ConnectionDevice("cord",R.drawable.line));
                useDevices.add(new NodeDevice(context, "battary", R.drawable.battary, 0, 0));

                ConnectionDevice connectionDevice = new ConnectionDevice("cord", R.drawable.line);
                connectionDevice.setFirstDevice(new NodeDevice("lamp"));
                connectionDevice.setSecondDevice(new NodeDevice("battary"));
                correctDescription.add(connectionDevice);
                break;
            case 2: break;
            case 3: break;
            case 4: break;
        }
    }

    public void addDevice(Device device){
        deviceOnField.add(device);
    }

    public List<ConnectionDevice> getCorrectDescription() {
        return correctDescription;
    }

    public List<Device> getDevicesOnField(){
        return deviceOnField;
    }

    public List<NodeDevice> getNodeDeviceOnField(){
        List<NodeDevice> devices = new ArrayList<>();
        for (Device device : deviceOnField)
            if (device instanceof NodeDevice)
                devices.add((NodeDevice)device);
        return devices;
    }

    public List<ConnectionDevice> getConnectionDeviceOnField(){
        List<ConnectionDevice> devices = new ArrayList<>();
        for (Device device : deviceOnField)
            if (device instanceof ConnectionDevice)
                devices.add((ConnectionDevice)device);
        return devices;
    }

    public List<Device> getUseDevices(){
        return useDevices;
    }
}
