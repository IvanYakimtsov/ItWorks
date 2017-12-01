package com.example.ivan.itworked;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.ivan.itworked.DeviceChooser.WindowOfChoosingDevice;
import com.example.ivan.itworked.Devices.Device;
import com.example.ivan.itworked.Devices.NodeDevice;
import com.example.ivan.itworked.GameData.Level;
import com.example.ivan.itworked.GameData.LevelController;

public class GameActivity extends AppCompatActivity {
    GameField gameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        final Level level = new Level(intent.getIntExtra("level", 0));
        gameField = new GameField(this);
        gameField.setLevel(level);

        setContentView(R.layout.activity_game);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relative);
        relativeLayout.addView(gameField);


        findViewById(R.id.settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = createDialog();
                alert.show();
            }
        });

        final GameActivity context = this;
        findViewById(R.id.pack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("LevelController", gameField.getLevelController());
                intent.setClass(context, WindowOfChoosingDevice.class);
                context.startActivityForResult(intent, 0);
            }
        });

        findViewById(R.id.check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gameField.isCorrect()) {
                    finish();
                }
            }
        });
    }

    private AlertDialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Warning!")
                .setMessage("Do you want to exit?")
                .setCancelable(false)
                .setNegativeButton("Continue",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .setPositiveButton("exit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });
        return builder.create();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            AlertDialog alertDialog = createDialog();
            alertDialog.show();
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Device device = (Device) data.getSerializableExtra("Device");
            if(device instanceof NodeDevice) ((NodeDevice) device).setContext(this);
            gameField.setCurrentDevice(device);

        }
    }
}
