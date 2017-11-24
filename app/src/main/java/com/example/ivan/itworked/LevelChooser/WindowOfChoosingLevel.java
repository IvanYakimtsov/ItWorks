package com.example.ivan.itworked.LevelChooser;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;

import com.example.ivan.itworked.R;

import java.util.ArrayList;
import java.util.List;

public class WindowOfChoosingLevel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.window_of_choosing_level);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.levelList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(layoutManager);

        List<Integer> levels = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            levels.add(i, i+1);
        LevelAdapter adapter = new LevelAdapter(this);
        adapter.addLevels(levels);

        recyclerView.setAdapter(adapter);
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
}
