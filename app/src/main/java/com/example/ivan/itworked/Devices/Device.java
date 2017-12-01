package com.example.ivan.itworked.Devices;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Ivan on 18.11.2017.
 */

public interface Device extends Serializable {
    public int getResourcePicture();
    public String getName();


}
