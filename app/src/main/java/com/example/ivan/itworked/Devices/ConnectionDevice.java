package com.example.ivan.itworked.Devices;

/**
 * Created by Ivan on 18.11.2017.
 */

public class ConnectionDevice implements Device {

    private NodeDevice firstDevice;
    private NodeDevice secondDevice;
    private int resourcePicture;
    private String name;

    public ConnectionDevice(String name, int resourcePicture) {
        this.resourcePicture = resourcePicture;
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

    public void setFirstDevice(NodeDevice firstDevice) {
        this.firstDevice = firstDevice;
    }

    public void setSecondDevice(NodeDevice secondDevice) {
        this.secondDevice = secondDevice;
    }

    public NodeDevice getFirstDevice() {
        return firstDevice;
    }

    public NodeDevice getSecondDevice() {
        return secondDevice;
    }

    @Override
    public boolean equals(Object obj) {
        boolean firstFlag = firstDevice.getName().equals(((ConnectionDevice) obj).firstDevice.getName())
                && secondDevice.getName().equals(((ConnectionDevice) obj).secondDevice.getName());
        boolean secondFlag = firstDevice.getName().equals(((ConnectionDevice) obj).secondDevice.getName())
                && secondDevice.getName().equals(((ConnectionDevice) obj).firstDevice.getName());
        return firstFlag | secondFlag;
    }

}
