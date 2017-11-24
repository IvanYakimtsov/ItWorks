package com.example.ivan.itworked.DeviceChooser;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Ivan on 18.11.2017.
 */

public class DeviceHolder extends RecyclerView.ViewHolder {

    private ImageView mView;
    private int mResource;
    private boolean mIsNodeDevice;
    private String mName;

    public DeviceHolder(Context context, View itemView) {
        super(itemView);
        final WindowOfChoosingDevice activity = (WindowOfChoosingDevice) context;
        mView = (ImageView) itemView;
        mResource = 0;
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("device", mResource);
                intent.putExtra("isNode", mIsNodeDevice);
                intent.putExtra("name", mName);
              //  activity.addDevice(intent);
                activity.addDevice(intent);
            }
        });
    }

    public void bind(String name,int resource, boolean isNodeDevice) {
        mView.setImageResource(resource);
        mResource = resource;
        mIsNodeDevice = isNodeDevice;
        mName = name;

    }
}
