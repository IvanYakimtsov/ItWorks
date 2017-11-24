package com.example.ivan.itworked.DeviceChooser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ivan.itworked.Devices.Device;
import com.example.ivan.itworked.Devices.NodeDevice;
import com.example.ivan.itworked.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 18.11.2017.
 */

public class DeviceAdapter extends RecyclerView.Adapter<DeviceHolder> {

    private Context mContext;
    //private List<Container> mDevices = new ArrayList<>();
    List<Device> devices = new ArrayList<>();

    public DeviceAdapter(Context context) {
        mContext = context;
    }

    void addDevices(List<Device> devices) {
        this.devices = devices;
    }

    @Override
    public DeviceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.device_item, parent, false);
        return new DeviceHolder(mContext, view);
    }

    @Override
    public void onBindViewHolder(DeviceHolder holder, int position) {
        Device device = devices.get(position);
        if(device instanceof NodeDevice) holder.bind(device.getName(), device.getResourcePicture(), true);
        else holder.bind(device.getName(), device.getResourcePicture(), false);
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

}
