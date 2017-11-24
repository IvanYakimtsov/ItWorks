package com.example.ivan.itworked.DeviceChooser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

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
        int levelNumber = intent.getIntExtra("level", 0);
      //  Log.d("check",levelNumber+" level");
        //TODO:pass class instead of parametrs

        LevelController levelController = new LevelController(getApplicationContext(),
                new Level(levelNumber));

        DeviceAdapter adapter = new DeviceAdapter(this);
     //   Log.d("check",levelController.getUseDevices().size()+"");
        adapter.addDevices(levelController.getUseDevices());
        recyclerView.setAdapter(adapter);
    }

    public void addDevice(Intent intent){
        setResult(RESULT_OK, intent);
        finish();
    }

}
