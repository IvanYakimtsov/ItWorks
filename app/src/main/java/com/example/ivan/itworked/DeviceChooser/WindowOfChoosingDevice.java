package com.example.ivan.itworked.DeviceChooser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ivan.itworked.Devices.Device;
import com.example.ivan.itworked.Devices.NodeDevice;
import com.example.ivan.itworked.GameData.Level;
import com.example.ivan.itworked.GameData.LevelController;
import com.example.ivan.itworked.R;

public class WindowOfChoosingDevice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_of_choosing_device);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.deviceList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        LevelController levelController = (LevelController) intent.getSerializableExtra("LevelController");


        DeviceAdapter adapter = new DeviceAdapter(this);
        adapter.addDevices(levelController.getUseDevices());
        recyclerView.setAdapter(adapter);
    }

    public void addDevice(Device device){
        Intent intent = new Intent();
        intent.putExtra("Device",device);
        setResult(RESULT_OK, intent);
        finish();
    }

}
