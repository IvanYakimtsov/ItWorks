package com.example.ivan.itworked.DeviceChooser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.ivan.itworked.Devices.Device;

/**
 * Created by Ivan on 18.11.2017.
 */

public class DeviceHolder extends RecyclerView.ViewHolder {

    private ImageView view;
    private Device device;

    public DeviceHolder(Context context, View itemView) {
        super(itemView);
        final WindowOfChoosingDevice activity = (WindowOfChoosingDevice) context;
        view = (ImageView) itemView;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.addDevice(device);
            }
        });
    }

    public void bind(Device device) {
        this.device = device;
        view.setImageResource(device.getResourcePicture());
    }
}
