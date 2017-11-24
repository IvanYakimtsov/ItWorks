package com.example.ivan.itworked.LevelChooser;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.ivan.itworked.GameActivity;

/**
 * Created by Ivan on 17.11.2017.
 */

public class LevelHolder extends RecyclerView.ViewHolder  {

    private TextView level;
    LevelHolder(View itemView, final Context context) {
        super(itemView);
        level = (TextView) itemView;
        level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GameActivity.class);
                intent.putExtra("level", Integer.valueOf(level.getText().toString()));
                context.startActivity(intent);
            }
        });
    }

    void bind(int number){
        level.setText(String.valueOf(number));
    }
}
